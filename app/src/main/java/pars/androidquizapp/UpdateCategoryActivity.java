package pars.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static pars.androidquizapp.CategoriesActivity.adapter;

public class UpdateCategoryActivity extends AppCompatActivity {

    String categoryName1;
    String position1;
    EditText categoryName;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);

        categoryName=(EditText) findViewById(R.id.updateEditText);
        updateButton = (Button) findViewById(R.id.updateButton);

        categoryName1= getIntent().getExtras().getString("categoryName");
        position1 = getIntent().getExtras().getString("categoryPosition");

        categoryName.setText(categoryName1);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryName1 = categoryName.getText().toString();
                if(!categoryName1.isEmpty()){
                    Database database = new Database(UpdateCategoryActivity.this);
                    database.UpdateCategory(categoryName1,position1);

                    Toast.makeText(UpdateCategoryActivity.this, "Category Successfully Updated",Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();

                    Intent intent1 = new Intent(UpdateCategoryActivity.this, CategoriesActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
}
