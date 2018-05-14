package pars.androidquizapp.categories;

import java.util.List;

import pars.androidquizapp.BasePresenter;
import pars.androidquizapp.BaseView;
import pars.androidquizapp.data.Category;


public interface CategoriesContract {

    interface View extends BaseView<CategoriesContract.Presenter> {

        void showCategories(List<Category> category);

        void showAddCategory();

        void showEmptyMessage();

        void showQuestionCount();
    }

    interface Presenter extends BasePresenter {

        void addNewCategory();

        void saveNewCategory(String category);

        void fetchCategories();

        void getQuestionCount();

        void playQuiz();

        void openCategoryDetails(Category requestedCategory);

        void updateCategory();

        void deleteCategory();
    }
}
