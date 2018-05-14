package pars.androidquizapp.addquestion;


import android.util.Log;

import java.util.List;

import pars.androidquizapp.data.Question;
import pars.androidquizapp.data.QuestionDao;


public class AddQuestionPresenter implements AddQuestionContract.Presenter{

    private AddQuestionContract.view mView;
    private QuestionDao questionDao;

    public AddQuestionPresenter(AddQuestionContract.view mView, QuestionDao questionDao) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.questionDao = questionDao;
    }

    @Override
    public void saveQuestion(String category, String question, String optionA,
                            String optionB, String optionC, String optionD, String answer) {

        Question questionParams = new Question(category, question, optionA,
                optionB, optionC, optionD, answer);
        questionDao.insert(questionParams);
    }


    @Override
    public void start() {

    }
}
