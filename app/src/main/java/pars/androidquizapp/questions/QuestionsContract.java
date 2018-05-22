package pars.androidquizapp.questions;

import java.util.List;

import pars.androidquizapp.BasePresenter;
import pars.androidquizapp.BaseView;
import pars.androidquizapp.data.Question;


public interface QuestionsContract {

    interface View extends BaseView<QuestionsContract.Presenter>{

        void showAllQuestions(List<Question> questions);

        void showEmptyMessage();

        void showAddQuestion();
    }

    interface Presenter extends BasePresenter {

        void fetchQuestions(String category);

        void addQuestion();

        void updateQuestion();

        void deleteQuestion();
    }
}
