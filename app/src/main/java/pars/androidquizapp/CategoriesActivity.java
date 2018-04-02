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

public class CategoriesActivity extends AppCompatActivity {
    public static List<String> Datas = new ArrayList<>();
    public static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ListView listView = (ListView) findViewById(R.id.CategoryListView);

        Database database = new Database(CategoriesActivity.this);
        Datas = database.CategoryDataList();
        TextView categoryEmptyTextView = (TextView)findViewById(R.id.categoryEmptyTextView);
        Button categoryEmptyButton = (Button)findViewById(R.id.categoryEmptyButton);

        categoryEmptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(CategoriesActivity.this, AddCategoryActivity.class);
                Bundle extras2 = new Bundle();
                extras2.putString("categoryName","Category Name");
                intent2.putExtras(extras2);
                startActivity(intent2);
            }
        });

        if(Datas.isEmpty()) {
            listView.setVisibility(View.INVISIBLE);
            categoryEmptyTextView.setVisibility(View.VISIBLE);
            categoryEmptyButton.setVisibility(View.VISIBLE);
        }

        adapter = new ArrayAdapter<String>(CategoriesActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,Datas);
        listView.setAdapter(adapter);

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if(v.getId() == R.id.CategoryListView) {
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                    menu.setHeaderTitle(Datas.get(info.position));

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
                Intent intent1 = new Intent(CategoriesActivity.this, UpdateCategoryActivity.class);
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
                Intent intent2 = new Intent(CategoriesActivity.this, AddCategoryActivity.class);
                Bundle extras2 = new Bundle();
                extras2.putString("categoryName","Category Name");
                intent2.putExtras(extras2);
                startActivity(intent2);
                re=true;
                break;

            case 2:
                Intent intent3 = new Intent(CategoriesActivity.this, DeleteCategoryActivity.class);
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

