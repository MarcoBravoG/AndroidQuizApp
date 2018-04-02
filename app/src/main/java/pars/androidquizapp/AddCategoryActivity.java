package pars.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static pars.androidquizapp.CategoriesActivity.adapter;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        final EditText category_name = (EditText) findViewById(R.id.category_name);
        Button add_category = (Button) findViewById(R.id.add_category);

        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = new Database(AddCategoryActivity.this);

                database.AddCategory(category_name.getText().toString());

                category_name.setText("");
                Toast.makeText(AddCategoryActivity.this, "Category Successfully Added",Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();

                Intent intent1 = new Intent(AddCategoryActivity.this, CategoriesActivity.class);
                startActivity(intent1);

            }
        });
    }
}
