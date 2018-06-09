package pars.androidquizapp.addquestion;


import pars.androidquizapp.BasePresenter;
import pars.androidquizapp.BaseView;


public interface AddQuestionContract {

    interface view extends BaseView<AddQuestionContract.Presenter>{

    }

    interface Presenter extends BasePresenter{

        void saveQuestion(long categoryId, String question, String optionA,
                         String optionB, String optionC,String optionD,
                         String answer);

    }
}
