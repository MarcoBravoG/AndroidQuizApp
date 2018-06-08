package pars.androidquizapp.categories;

import android.util.Log;

import java.util.List;

import pars.androidquizapp.data.Category;
import pars.androidquizapp.data.CategoryDao;


public class CategoriesPresenter implements CategoriesContract.Presenter {

    private CategoriesContract.View mCategoriesView;
    private CategoryDao categoryDao;


    public CategoriesPresenter(CategoriesContract.View mCategoriesView, CategoryDao categoryDao) {
        this.mCategoriesView = mCategoriesView;
        this.mCategoriesView.setPresenter(this);
        this.categoryDao = categoryDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void addNewCategory() {
        mCategoriesView.showAddCategory();
    }

    @Override
    public void saveNewCategory(String category) {
        Category newCategory = new Category(category);
        categoryDao.insert(newCategory);
    }

    @Override
    public void fetchCategories() {
        List<Category> categoryList =  categoryDao.getAllCategories();
        mCategoriesView.showCategories(categoryList);
    }

    @Override
    public void updateCategory(long id, String category) {
        Log.e("UPDATE ID", Long.toString(id));
        Log.e("UPDATE CATEGORY", category);
        categoryDao.updateCategory(id, category);
        Log.e("NEWLY UPDATED ID", Long.toString(id));
        Log.e("NEWLY UPDATED CATEGORY", category);
    }

    @Override
    public void getCategoryToUpdate(Category category) {
       mCategoriesView.showCategoryToUpdate(category);
    }


    @Override
    public void deleteCategory() {

    }


}
