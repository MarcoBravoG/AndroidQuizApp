package pars.androidquizapp.categories;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Category;
import pars.androidquizapp.data.MainDatabase;
import pars.androidquizapp.categories.CategoriesAdapter.OnCategoryClicked;
import pars.androidquizapp.categories.CategoriesAdapter.OnCategoryOnLongClicked;
import pars.androidquizapp.playquiz.PlayQuizActivity;
import pars.androidquizapp.questions.QuestionsActivity;


public class CategoriesFragment extends Fragment implements CategoriesContract.View{


    private CategoriesContract.Presenter mPresenter;
    private CategoriesAdapter categoriesAdapter;
    private List<Category> categories = new ArrayList<>();
    private MainDatabase database;
    private AlertDialog alertDialog = null;

    @BindView(R.id.tv_empty)
    TextView emptyTextView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = MainDatabase.getInstance(getActivity());
        mPresenter = new CategoriesPresenter(this, database.categoryDao());
    }

    @Override
    public void setPresenter(CategoriesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, root);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        categoriesAdapter = new CategoriesAdapter(getActivity(), categories, mItemListener, mItemLongListener);
        recyclerView.setAdapter(categoriesAdapter);
        recyclerView.invalidate();

        return root;
    }

    /**
     * Listener for onClick on a category in the RecyclerView.
     */
    OnCategoryClicked mItemListener = new OnCategoryClicked() {
        @Override
        public void onCategoryClick(long categoryId) {
            Intent intent = new Intent(getContext(), QuestionsActivity.class);
            intent.putExtra("categoryId", categoryId);
            startActivity(intent);
        }

        @Override
        public void onPlayButtonClicked(long categoryId) {
            Intent intent = new Intent(getContext(), PlayQuizActivity.class);
            intent.putExtra("categoryId", categoryId);
            startActivity(intent);
        }
    };


    /**
     * Listener for onLongclick on a category in the RecyclerView.
     * This enables to select options to Update and Delete category
     */
    OnCategoryOnLongClicked mItemLongListener = new OnCategoryOnLongClicked() {
        @Override
        public void onCategoryLongClick(Category onClickCategory) {

            String categoryName = onClickCategory.getCategory();
            long id = onClickCategory.getId();
            //Create an alert dialog builder
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Set title value
            builder.setTitle("Select Options")
                    .setItems(new String[]{"Update", "Delete"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case 0:
                                    mPresenter.getCategoryToUpdate(onClickCategory);
                                    dialog.dismiss();
                                    break;
                                case 1:
                                    mPresenter.deleteCategory(id);
                                    Toast.makeText(getContext(), categoryName + " has been deleted", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    Intent intent = new Intent(getActivity(), CategoriesActivity.class);
                                    startActivity(intent);
                                    break;
                            }
                        }
                    });

            alertDialog = builder.create();
            alertDialog.show();
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addNewCategory();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.fetchCategories();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.fetchCategories();
    }

    @Override
    public void showEmptyMessage() {
        emptyTextView.setVisibility(View.VISIBLE);
    }

    //This shows the category to update when an Update item is selected from the alert dialog
    @Override
    public void showCategoryToUpdate(Category category) {

        long id = category.getId();

        //Create an alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set title value
        builder.setTitle("Update Category");

        //Get custom view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_category_dialog, null);
        builder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.category_name);
        final Button addCategory = dialogView.findViewById(R.id.add_category);

        editText.setText(category.getCategory());
        addCategory.setText("Update");

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.updateCategory(id, editText.getText().toString());
                Toast.makeText(getActivity(), editText.getText().toString() + " has been added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CategoriesActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void showCategories(List<Category> category) {
        if(category.isEmpty()){
            showEmptyMessage();
        } else {
            emptyTextView.setVisibility(View.GONE);
            categoriesAdapter.setValues(category);
        }
    }

    @Override
    public void showAddCategory() {

        //Create an alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set title value
        builder.setTitle("Add Category");

        //Get custom view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_category_dialog, null);
        builder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.category_name);
        final Button addCategory = dialogView.findViewById(R.id.add_category);

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveNewCategory(editText.getText().toString());
                Toast.makeText(getActivity(), editText.getText().toString() + " has been added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CategoriesActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

}
