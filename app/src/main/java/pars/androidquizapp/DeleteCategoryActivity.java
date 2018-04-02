package pars.androidquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static pars.androidquizapp.CategoriesActivity.adapter;

public class DeleteCategoryActivity extends AppCompatActivity {

    String categoryName1;
    String position1;
    TextView categoryName;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        categoryName=(TextView) findViewById(R.id.deleteTextView);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        categoryName1= getIntent().getExtras().getString("categoryName");
        position1 = getIntent().getExtras().getString("categoryPosition");

        categoryName.setText(categoryName1);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryName1 = categoryName.getText().toString();
                if(!categoryName1.isEmpty()){
                    Database database = new Database(DeleteCategoryActivity.this);
                    database.DeleteCategory(position1);

                    Toast.makeText(DeleteCategoryActivity.this, "Category Successfully Deleted",Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();

                    Intent intent1 = new Intent(DeleteCategoryActivity.this, CategoriesActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
}
