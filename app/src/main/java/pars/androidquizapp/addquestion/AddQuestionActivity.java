package pars.androidquizapp.addquestion;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Category;
import pars.androidquizapp.data.Database;
import pars.androidquizapp.data.Question;

public class AddQuestionActivity extends AppCompatActivity {

    @BindView(R.id.question)
    EditText question;
    @BindView(R.id.opta)
    EditText opta;
    @BindView(R.id.optb)
    EditText optb;
    @BindView(R.id.optc)
    EditText optc;
    @BindView(R.id.optd)
    EditText optd;
    @BindView(R.id.ans)
    EditText ans;
    @BindView(R.id.add_question)
    Button add_question;
    @BindView(R.id.question_category)
    Spinner spinner;

    public static List<String> data = new ArrayList<>();
    public List<Category> categoryList;
    public Question questions;
    public Database database;
    public ArrayAdapter<String> adapter;
    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        ButterKnife.bind(this);
        database = Database.getInstance(AddQuestionActivity.this);

        //Receive the intent data
        if ((questions = (Question) getIntent().getSerializableExtra("question")) != null) {
            update = true;
            add_question.setText("Update");
            //Try get the id of the category passed
            //spinner.setSelection(0);
            question.setText(questions.getQuestion());
            opta.setText(questions.getOptionA());
            optb.setText(questions.getOptionB());
            optc.setText(questions.getOptionC());
            optd.setText(questions.getOptionD());
            ans.setText(questions.getAnswerOoption());
        }

        //Get all categories object and convert to array string
        categoryList = database.categoryDao().getAllCategories();
        if(data == null){
            for (Category cat : categoryList) {
                data.add(cat.getCategory());
            }
        } else {
            data.clear();
            for (Category cat : categoryList) {
                data.add(cat.getCategory());
            }
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        spinner.setAdapter(adapter);

        //click the save button
        add_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update) {
                    questions.setQuestionCategory(spinner.getSelectedItem().toString());
                    questions.setQuestion(question.getText().toString());
                    questions.setOptionA(opta.getText().toString());
                    questions.setOptionB(optb.getText().toString());
                    questions.setOptionC(optc.getText().toString());
                    questions.setOptionD(optd.getText().toString());
                    questions.setAnswerOoption(ans.getText().toString());

                    database.questionDao().updateQuestion(questions);
                    setResult(questions, 2);

                } else {
                    String categoryName = spinner.getSelectedItem().toString();
                    String questionValue = question.getText().toString();
                    String optaValue = opta.getText().toString();
                    String optbValue = optb.getText().toString();
                    String optcValue = optc.getText().toString();
                    String optdValue = optd.getText().toString();
                    String ansValue = ans.getText().toString();

                    questions = new Question(categoryName, questionValue, optaValue, optbValue, optcValue, optdValue, ansValue);
                    new InsertTask(AddQuestionActivity.this, questions).execute();
                }
            }
        });

    }

    private void setResult(Question question, int flag) {
        setResult(flag, new Intent().putExtra("question", question));
        Toast.makeText(AddQuestionActivity.this, "Question Successfully Added", Toast.LENGTH_LONG).show();

        //This goes back to the HomeActivity
        startActivity(new Intent(AddQuestionActivity.this, HomeActivity.class));
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<AddQuestionActivity> activityReference;
        public Question question;

        // only retain a weak reference to the activity
        InsertTask(AddQuestionActivity context, Question question) {
            activityReference = new WeakReference<>(context);
            this.question = question;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            long j = activityReference.get().database.questionDao().insert(question);
            question.setId(j);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool) {
                activityReference.get().setResult(question, 1);
            }
        }
    }
}
