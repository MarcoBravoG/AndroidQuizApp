package pars.androidquizapp.questions;


import android.util.Log;

import java.util.List;

import pars.androidquizapp.data.Question;
import pars.androidquizapp.data.QuestionDao;

public class QuestionsPresenter implements QuestionsContract.Presenter {

    private QuestionsContract.View mView;
    private QuestionDao questionDao;


    public QuestionsPresenter(QuestionsContract.View mView, QuestionDao questionDao) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.questionDao = questionDao;
    }

    @Override
    public void fetchQuestions(long categoryId) {
        List<Question> questionList = questionDao.fetchQuestion(categoryId);
        Log.e("QUESTION LIST", questionList.toString());
        mView.showAllQuestions(questionList);
    }

    @Override
    public void addQuestion() {
        mView.showAddQuestion();
    }

    @Override
    public void updateQuestion() {

    }

    @Override
    public void deleteQuestion() {

    }

    @Override
    public void start() {

    }
}
