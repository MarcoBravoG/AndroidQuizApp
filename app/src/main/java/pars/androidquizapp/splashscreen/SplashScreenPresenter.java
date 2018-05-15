package pars.androidquizapp.splashscreen;


public class SplashScreenPresenter implements SplashScreenContract.UserActionsListener {

    private final SplashScreenContract.View mMainScreen;

    public SplashScreenPresenter(SplashScreenContract.View mMainScreen) {
        this.mMainScreen = mMainScreen;
    }

    @Override
    public void takeQuiz() {
        mMainScreen.showMainScreen();
    }
}
