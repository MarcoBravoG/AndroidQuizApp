package pars.androidquizapp.splashscreen;


public interface SplashScreenContract {

    interface View {

        void showMainScreen();
    }

    interface UserActionsListener {

        void takeQuiz();
    }

}
