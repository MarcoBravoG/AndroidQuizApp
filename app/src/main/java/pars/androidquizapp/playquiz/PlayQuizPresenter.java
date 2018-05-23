package pars.androidquizapp.playquiz;

import java.util.List;

import pars.androidquizapp.data.Question;
import pars.androidquizapp.data.QuestionDao;



public class PlayQuizPresenter implements PlayQuizContract.Presenter {

    private PlayQuizContract.View mView;
    private QuestionDao questionDao;

    public PlayQuizPresenter(PlayQuizContract.View mView, QuestionDao questionDao) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.questionDao = questionDao;
    }


    @Override
    public void fetchQuestions(String category) {
        List<Question> questionList = questionDao.queryQuestion(category);
        mView.showQuestions(questionList);
    }

    @Override
    public void backToMenu() {

    }

    @Override
    public void playAgain() {

    }

    @Override
    public void start() {

    }
}
