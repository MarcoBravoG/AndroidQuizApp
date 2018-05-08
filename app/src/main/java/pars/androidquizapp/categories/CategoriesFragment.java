package pars.androidquizapp.categories;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pars.androidquizapp.R;
import pars.androidquizapp.data.Category;


public class CategoriesFragment extends Fragment implements CategoriesContract.View{


    private CategoriesContract.UserActionsListener mActionListener;

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

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        mActionListener = null;
    }

    @Override
    public void showEmptyContentMessage() {

    }

    @Override
    public void showCategories(List<Category> category) {

    }

    @Override
    public void showAddCategory() {

    }


    public interface OnCategoryItemListener {
        void onCategoryClicked(Category clickedCategory);
    }
}
