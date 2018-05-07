package pars.androidquizapp.addquestion;

/**
 * Created by Princess on 07/05/2018.
 */

public interface AddQuestionContract {

    interface UserActionsListener{

        void addQuestion(int id, String Category, String Question, String optionA,
                         String optionB, String optionC,String optionD,
                         String answer);
    }
}
