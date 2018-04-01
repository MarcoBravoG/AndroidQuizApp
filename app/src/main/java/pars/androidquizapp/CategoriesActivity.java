package pars.androidquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ListView listView = (ListView) findViewById(R.id.CategoryListView);

        Database database = new Database(CategoriesActivity.this);
        List<String> Datas = database.CategoryDataList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CategoriesActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,Datas);
        listView.setAdapter(adapter);
    }
}
