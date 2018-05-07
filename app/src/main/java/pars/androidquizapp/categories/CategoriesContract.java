package pars.androidquizapp.categories;

import java.util.List;

import pars.androidquizapp.data.Category;

/**
 * Created by Princess on 07/05/2018.
 */

public interface CategoriesContract {

    interface View {

        void showEmptyContentMessage();

        void showCategories(List<Category> category);

        void showAddCategory();

    }

    interface UserActionsListener {

        void addNewCategory();

        void playQuiz();

        void openCategoryDetails(Category requestedCategory);

        void updateCategory();

        void deleteCategory();
    }
}
