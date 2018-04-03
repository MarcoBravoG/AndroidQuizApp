package pars.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    public static List<String> Datas = new ArrayList<>();
    public static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ListView listView = (ListView) findViewById(R.id.questionListView);

        Database database = new Database(QuestionsActivity.this);
        Datas = database.QuestionDataList();
        TextView questionEmptyTextView = (TextView)findViewById(R.id.questionEmptyTextView);
        Button questionEmptyButton = (Button)findViewById(R.id.questionEmptyButton);

        questionEmptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(QuestionsActivity.this, AddQuestionActivity.class);
                Bundle extras2 = new Bundle();
                extras2.putString("question","Question");
                intent2.putExtras(extras2);
                startActivity(intent2);
            }
        });

        if(Datas.isEmpty()) {
            listView.setVisibility(View.INVISIBLE);
            questionEmptyTextView.setVisibility(View.VISIBLE);
            questionEmptyButton.setVisibility(View.VISIBLE);
        }

        adapter = new ArrayAdapter<String>(QuestionsActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,Datas);
        listView.setAdapter(adapter);

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if(v.getId() == R.id.questionListView) {
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

                    menu.add(0,0,0,"Update");
                    menu.add(0,1,0,"Add");
                    menu.add(0,2,0,"Delete");
                }
            }
        });
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        boolean re;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case 0:
                Intent intent1 = new Intent(QuestionsActivity.this, UpdateQuestionActivity.class);
                Bundle extras1 = new Bundle();
                String Id = Datas.get(info.position).substring(0,1);
                Database database = new Database(QuestionsActivity.this);
                String[] questiondata = new String[8];
                questiondata = database.getquestion(Id);
                extras1.putString("questionId",questiondata[0]);
                extras1.putString("categoryId",questiondata[1]);
                extras1.putString("question",questiondata[2]);
                extras1.putString("opta",questiondata[3]);
                extras1.putString("optb",questiondata[4]);
                extras1.putString("optc",questiondata[5]);
                extras1.putString("optd",questiondata[6]);
                extras1.putString("ans",questiondata[7]);
                intent1.putExtras(extras1);
                startActivity(intent1);
                re=true;
                break;

            case 1:
                Intent intent2 = new Intent(QuestionsActivity.this, AddQuestionActivity.class);
                Bundle extras2 = new Bundle();
                extras2.putString("question","Question");
                intent2.putExtras(extras2);
                startActivity(intent2);
                re=true;
                break;

            case 2:
                Intent intent3 = new Intent(QuestionsActivity.this, DeleteCategoryActivity.class);
                Bundle extras3 = new Bundle();
                String Id2 = Datas.get(info.position).substring(0,1);
                Database database2 = new Database(QuestionsActivity.this);
                String[] questiondata2 = new String[8];
                questiondata = database2.getquestion(Id2);
                extras3.putString("questionId",questiondata2[0]);
                extras3.putString("categoryId",questiondata2[1]);
                extras3.putString("question",questiondata2[2]);
                extras3.putString("opta",questiondata2[3]);
                extras3.putString("optb",questiondata2[4]);
                extras3.putString("optc",questiondata2[5]);
                extras3.putString("optd",questiondata2[6]);
                extras3.putString("ans",questiondata2[7]);
                intent3.putExtras(extras3);
                startActivity(intent3);
                re=true;
                break;

            default:
                re=false;
                break;
        }
        return re;
    }
}
