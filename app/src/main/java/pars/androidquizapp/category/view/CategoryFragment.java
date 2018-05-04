package pars.androidquizapp.category.view;

/**
 * Created by Princess on 15/04/2018.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.category.adapter.CategoryAdapter;
import pars.androidquizapp.database.Category;
import pars.androidquizapp.database.CategoryDatabase;


public class CategoryFragment extends Fragment
implements CategoryAdapter.OnCategoryClicked {


    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.tv_empty)
    public TextView emptyView;

    public CategoryDatabase categoryDatabase;
    private CategoryAdapter mAdapter;
    public List<Category> categoryList;
    int position;


    public CategoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryList = new ArrayList<>();
        mAdapter = new CategoryAdapter(getActivity(), categoryList, this);

        displayCategory();
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

    private void displayCategory(){
        categoryDatabase = CategoryDatabase.getInstance(getContext());
        new RetrieveTask(this).execute();
    }

    @Override
    public void onClick(int position) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Select Options")
                .setItems(new String[]{"Update", "Delete"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                CategoryFragment.this.position = position;
                                startActivityForResult(
                                        new Intent(getActivity(),
                                                AddCategoryActivity.class).putExtra("category",categoryList.get(position)),
                                        100);
                                break;
                            case 1:
                                categoryDatabase.categoryDao().deleteCategory(categoryList.get(position));
                                categoryList.remove(position);
                                listVisibility();
                                break;
                        }
                    }
                }).show();
    }


    public static class RetrieveTask extends AsyncTask<Void,Void,List<Category>> {

        private WeakReference<CategoryFragment> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(CategoryFragment context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            if (activityReference.get() != null) {
                Log.e("RESULT", "categories" +
                        activityReference.get().categoryDatabase.categoryDao().getAllCategories());
                return activityReference.get().categoryDatabase.categoryDao().getAllCategories();
            }else{
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            if (categories != null && categories.size() > 0) {
                activityReference.get().categoryList.clear();
                activityReference.get().categoryList.addAll(categories);
                 //hides empty text view
                activityReference.get().emptyView.setVisibility(View.GONE);
                activityReference.get().mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(categoryList.isEmpty()){
            displayCategory();
        } else{
            displayCategory();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode > 0 ){
            if( resultCode == 1){
                categoryList.add((Category) data.getSerializableExtra("category"));
            }else if( resultCode == 2){
                categoryList.set(position,(Category) data.getSerializableExtra("category"));
            }
            listVisibility();
        }
    }

    private void listVisibility(){
        int emptyMsgVisibility = View.GONE;
        if (categoryList.size() == 0){ // no item to display
            if (emptyView.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        emptyView.setVisibility(emptyMsgVisibility);
        mAdapter.notifyDataSetChanged();
        onResume();
    }

    @Override
    public void onDestroy() {
        categoryDatabase.cleanUp();
        super.onDestroy();
    }
}
