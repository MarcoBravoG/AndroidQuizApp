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
        Log.e("LIST SIZE", "This is the size: " + categoryList.toString());
        mCategoriesView.showCategories(categoryList);
    }

    @Override
    public void playQuiz() {

    }

    @Override
    public void openCategoryDetails(Category requestedCategory) {

    }

    @Override
    public void updateCategory() {

    }

    @Override
    public void deleteCategory() {

    }


}
