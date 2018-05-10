package pars.androidquizapp.categories;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Category;
import pars.androidquizapp.util.EmptyRecyclerView;


public class CategoriesFragment extends Fragment implements CategoriesContract.View{


    private CategoriesContract.UserActionsListener mActionListener;
    private CategoriesAdapter categoriesAdapter;
    private AlertDialog alertDialog = null;
    //private EmptyRecyclerView emptyRecyclerView;

    @BindView(R.id.tv_empty)
    TextView emptyTextView;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recyclerView;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionListener = new CategoriesPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        ButterKnife.bind(this, root);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setEmptyView(root.findViewById(R.id.tv_empty));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mActionListener.addNewCategory();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showCategories(List<Category> category) {

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
                Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }


    //    public interface OnCategoryItemListener {
//        void onCategoryClicked(Category clickedCategory);
//    }

}
