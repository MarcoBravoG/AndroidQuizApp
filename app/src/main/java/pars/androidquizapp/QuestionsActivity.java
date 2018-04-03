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
                Intent intent1 = new Intent(QuestionsActivity.this, UpdateCategoryActivity.class);
                Bundle extras1 = new Bundle();
                String Catname =Datas.get(info.position).substring(4);
                String Id = Datas.get(info.position).substring(0,1);
                extras1.putString("categoryName", Catname);
                extras1.putString("categoryPosition",Id);
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
                String dCatname =Datas.get(info.position).substring(4);
                String dId = Datas.get(info.position).substring(0,1);
                extras3.putString("categoryName",dCatname);
                extras3.putString("categoryPosition",dId);
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
