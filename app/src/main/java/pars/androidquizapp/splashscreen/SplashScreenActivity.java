package pars.androidquizapp.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.categories.CategoriesActivity;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.View {

    private SplashScreenContract.UserActionsListener mActionListener;

    @BindView(R.id.take_quiz)
    ImageView takeQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        mActionListener = new SplashScreenPresenter(this);

        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionListener.takeQuiz();
            }
        });
    }

    @Override
    public void showMainScreen() {
        startActivity(new Intent(SplashScreenActivity.this, CategoriesActivity.class));
    }
}
