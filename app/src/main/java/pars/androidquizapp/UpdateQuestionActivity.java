package pars.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateQuestionActivity extends AppCompatActivity {

    public static List<String> Datas = new ArrayList<>();

    String questionId1;
    String question_category1;
    String question1;
    String opta1;
    String optb1;
    String optc1;
    String optd1;
    String ans1;

    Spinner question_category;
    EditText question;
    EditText opta;
    EditText optb;
    EditText optc;
    EditText optd;
    EditText ans;
    Button update_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

        question_category = (Spinner) findViewById(R.id.question_category);
        question = (EditText) findViewById(R.id.question);
        opta = (EditText) findViewById(R.id.opta);
        optb = (EditText) findViewById(R.id.optb);
        optc = (EditText) findViewById(R.id.optc);
        optd = (EditText) findViewById(R.id.optd);
        ans = (EditText) findViewById(R.id.ans);
        update_question = (Button) findViewById(R.id.update_question);

        questionId1 = getIntent().getExtras().getString("questionId");
        question_category1 = getIntent().getExtras().getString("categoryId");
        question1 = getIntent().getExtras().getString("question");
        opta1 = getIntent().getExtras().getString("opta");
        optb1 = getIntent().getExtras().getString("optb");
        optc1 = getIntent().getExtras().getString("optc");
        optd1 = getIntent().getExtras().getString("optd");
        ans1 = getIntent().getExtras().getString("ans");

        question.setText(question1);
        opta.setText(opta1);
        optb.setText(optb1);
        optc.setText(optc1);
        optd.setText(optd1);
        ans.setText(ans1);

        final Database database = new Database(UpdateQuestionActivity.this);
        Datas = database.CategoryDataList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Datas);
        question_category.setAdapter(adapter);

        update_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer categoryIDValue = Integer.parseInt(question_category.getSelectedItem().toString().substring(0,1));
                question1 = question.getText().toString();
                opta1 = opta.getText().toString();
                optb1 = optb.getText().toString();
                optc1 = optc.getText().toString();
                optd1 = optd.getText().toString();
                ans1 = ans.getText().toString();

                if(!question1.isEmpty()){
                    Database database = new Database(UpdateQuestionActivity.this);
                    database.UpdateQuestion(questionId1,categoryIDValue,question1,opta1,optb1,optc1,optd1,ans1);

                    Toast.makeText(UpdateQuestionActivity.this, "Question Successfully Updated",Toast.LENGTH_LONG).show();

                    Intent intent1 = new Intent(UpdateQuestionActivity.this, QuestionsActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
}
