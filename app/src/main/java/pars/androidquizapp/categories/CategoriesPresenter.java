package pars.androidquizapp.categories;

import org.jetbrains.annotations.NotNull;

import pars.androidquizapp.data.Category;

import static com.google.common.base.Preconditions.checkNotNull;


public class CategoriesPresenter implements CategoriesContract.UserActionsListener {

    private CategoriesContract.View categoriesView;


    public CategoriesPresenter(@NotNull CategoriesContract.View mCatgeoriesView) {
        categoriesView = checkNotNull(mCatgeoriesView, "categoryView cannot be null");
    }

    @Override
    public void addNewCategory() {

        categoriesView.showAddCategory();

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
