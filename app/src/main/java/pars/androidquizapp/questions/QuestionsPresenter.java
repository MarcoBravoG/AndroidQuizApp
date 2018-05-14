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
    public void fetchQuestions(String category) {
        List<Question> questionList = questionDao.queryQuestion(category);
        Log.e("COUNT", "This is the number of questions "+ questionList.size());
        mView.showAllQuestions(questionList);
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
