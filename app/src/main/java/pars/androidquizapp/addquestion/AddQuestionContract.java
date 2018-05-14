package pars.androidquizapp.addquestion;


import java.util.List;

import pars.androidquizapp.BasePresenter;
import pars.androidquizapp.BaseView;
import pars.androidquizapp.data.Question;

public interface AddQuestionContract {

    interface view extends BaseView<AddQuestionContract.Presenter>{

    }

    interface Presenter extends BasePresenter{

        void saveQuestion(String Category, String Question, String optionA,
                         String optionB, String optionC,String optionD,
                         String answer);

    }
}
