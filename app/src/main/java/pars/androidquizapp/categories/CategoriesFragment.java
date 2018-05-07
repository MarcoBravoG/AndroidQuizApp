package pars.androidquizapp.categories;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pars.androidquizapp.R;
import pars.androidquizapp.data.Category;


public class CategoriesFragment extends Fragment implements CategoriesContract.View{


    private OnCategoryItemListener mListener;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        mListener = null;
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
        // TODO: Update argument type and name
        void onCategoryClicked(Category clickedCategory);
    }
}
