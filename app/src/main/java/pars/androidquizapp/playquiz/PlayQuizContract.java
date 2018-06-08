package pars.androidquizapp.playquiz;

import java.util.List;

import pars.androidquizapp.BasePresenter;
import pars.androidquizapp.BaseView;
import pars.androidquizapp.data.Question;


public interface PlayQuizContract {

    interface View extends BaseView<PlayQuizContract.Presenter> {

        void showQuestions(List<Question> questions);

        void showScore();

        void showEmptyMessage();
    }

    interface Presenter extends BasePresenter {

        void fetchQuestions(long categoryId);

        void calculateScore();

    }
}
