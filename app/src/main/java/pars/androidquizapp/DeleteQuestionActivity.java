package pars.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteQuestionActivity extends AppCompatActivity {

    public static List<String> Datas = new ArrayList<>();

    String questionId1;
    String question_category1;
    String question1;
    String opta1;
    String optb1;
    String optc1;
    String optd1;
    String ans1;

    TextView question_category_id;
    TextView question;
    TextView opta;
    TextView optb;
    TextView optc;
    TextView optd;
    TextView ans;
    Button delete_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_question);

        question_category_id = (TextView) findViewById(R.id.question_category_id);
        question = (TextView) findViewById(R.id.question);
        opta = (TextView) findViewById(R.id.opta);
        optb = (TextView) findViewById(R.id.optb);
        optc = (TextView) findViewById(R.id.optc);
        optd = (TextView) findViewById(R.id.optd);
        ans = (TextView) findViewById(R.id.ans);
        delete_question = (Button) findViewById(R.id.delete_question);

        questionId1 = getIntent().getExtras().getString("questionId");
        question_category1 = getIntent().getExtras().getString("categoryId");
        question1 = getIntent().getExtras().getString("question");
        opta1 = getIntent().getExtras().getString("opta");
        optb1 = getIntent().getExtras().getString("optb");
        optc1 = getIntent().getExtras().getString("optc");
        optd1 = getIntent().getExtras().getString("optd");
        ans1 = getIntent().getExtras().getString("ans");

        question_category_id.setText(question_category1);
        question.setText(question1);
        opta.setText(opta1);
        optb.setText(optb1);
        optc.setText(optc1);
        optd.setText(optd1);
        ans.setText(ans1);

        delete_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = new Database(DeleteQuestionActivity.this);
                database.DeleteQuestion(questionId1);

                Toast.makeText(DeleteQuestionActivity.this, "Question Successfully Deleted",Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(DeleteQuestionActivity.this, QuestionsActivity.class);
                startActivity(intent1);
            }
        });
    }
}
