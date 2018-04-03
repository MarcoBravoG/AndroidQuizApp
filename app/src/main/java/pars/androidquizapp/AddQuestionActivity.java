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

import static pars.androidquizapp.CategoriesActivity.adapter;

public class AddQuestionActivity extends AppCompatActivity {
    public static List<String> Datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        final Spinner spinner = (Spinner) findViewById(R.id.question_category);

        final Database database = new Database(AddQuestionActivity.this);
        Datas = database.CategoryDataList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Datas);
        spinner.setAdapter(adapter);

        final EditText question = (EditText)findViewById(R.id.question);
        final EditText opta = (EditText)findViewById(R.id.opta);
        final EditText optb = (EditText)findViewById(R.id.optb);
        final EditText optc = (EditText)findViewById(R.id.optc);
        final EditText optd = (EditText)findViewById(R.id.optd);
        final EditText ans = (EditText)findViewById(R.id.ans);

        Button add_question = (Button) findViewById(R.id.add_question);

        add_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer categoryIDValue = Integer.parseInt(spinner.getSelectedItem().toString().substring(0,1));
                String questionValue = question.getText().toString();
                String optaValue = opta.getText().toString();
                String optbValue = optb.getText().toString();
                String optcValue = optc.getText().toString();
                String optdValue = optd.getText().toString();
                String ansValue = ans.getText().toString();

                database.AddQuestion(categoryIDValue,questionValue,optaValue,optbValue,optcValue,optdValue,ansValue);

                Toast.makeText(AddQuestionActivity.this, "Question Successfully Added",Toast.LENGTH_LONG).show();

                //Intent intent1 = new Intent(AddQuestionActivity.this, CategoriesActivity.class);
                //startActivity(intent1);
            }
        });

    }
}
