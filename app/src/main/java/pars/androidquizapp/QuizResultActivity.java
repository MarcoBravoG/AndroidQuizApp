package pars.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    Integer correct1,incorrect1,score1;
    TextView correct,incorrect,score;
    Button mainMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);



        correct1 = getIntent().getExtras().getInt("correct");
        incorrect1 = getIntent().getExtras().getInt("incorrect");
        score1 = 10 * correct1;
        correct = (TextView) findViewById(R.id.correct);
        incorrect = (TextView) findViewById(R.id.incorrect);
        score = (TextView) findViewById(R.id.score);

        correct.setText("  " + correct1);
        incorrect.setText("  " + incorrect1);
        score.setText("Score  :    " + score1);

        mainMenu = (Button) findViewById(R.id.mainMenu);

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
