package pars.androidquizapp.questions;

import java.util.List;

import pars.androidquizapp.data.Question;

/**
 * Created by Princess on 07/05/2018.
 */

public interface QuestionsContract {

    interface View {

        void showQuestions(List<Question> questions);
    }

    interface UserActionsListener {

        void updateQuestion();

        void deleteQuestion();
    }
}
