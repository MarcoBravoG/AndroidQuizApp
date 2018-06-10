package pars.androidquizapp.addquestion;


import pars.androidquizapp.BasePresenter;
import pars.androidquizapp.BaseView;
import pars.androidquizapp.data.Question;


public interface AddQuestionContract {

    interface view extends BaseView<AddQuestionContract.Presenter>{

        void showQuestionToUpdate(Question question);
    }

    interface Presenter extends BasePresenter{

        void saveQuestion(long categoryId, String question, String optionA,
                         String optionB, String optionC,String optionD,
                         String answer);

        void fetchQuestionToUpdate(long id);

        void updateQuestion(Question question);

    }
}
