package pars.androidquizapp.playquiz;

import java.util.List;

import pars.androidquizapp.data.Question;

/**
 * Created by Princess on 07/05/2018.
 */

public interface PlayQuizContract {

    interface  View {

        void showQuestions(List<Question> questions);

        void showScore();
    }

    interface UserActionsListener {

        void clickCorrectButton();

        void backToMenu();

        void playAgain();

    }
}
