package pars.androidquizapp.categories;

import pars.androidquizapp.data.Category;


public class CategoriesPresenter implements CategoriesContract.UserActionsListener {

    private CategoriesContract.View mCatgeoriesView;


    public CategoriesPresenter(CategoriesContract.View mCatgeoriesView) {
        this.mCatgeoriesView = mCatgeoriesView;
    }

    @Override
    public void addNewCategory() {

        mCatgeoriesView.showAddCategory();

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
