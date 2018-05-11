package pars.androidquizapp.categories;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        categoriesAdapter = new CategoriesAdapter(getActivity(), categories, mItemListener);
        recyclerView.setAdapter(categoriesAdapter);
        return root;
    }

    /**
     * Listener for clicks on categories in the RecyclerView.
     */
    OnCategoryClicked mItemListener = new OnCategoryClicked() {
        @Override
        public void onCategoryClick(Category category) {

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

        if(categories == null){
            showEmptyMessage();
        }
        mPresenter.fetchCategories();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void showEmptyMessage() {
        emptyTextView.setVisibility(View.VISIBLE);
    }


    @Override
    public void showCategories(List<Category> category) {
        emptyTextView.setVisibility(View.GONE);
        categoriesAdapter.setValues(category);
    }

    @Override
    public void showAddCategory() {

        //Create an alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set title value
        builder.setTitle("Add Categories");

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
                startActivity(new Intent(getActivity(), CategoriesActivity.class));
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

}
