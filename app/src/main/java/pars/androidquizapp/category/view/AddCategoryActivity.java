package pars.androidquizapp.category.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.HomeActivity;
import pars.androidquizapp.R;
import pars.androidquizapp.database.Category;
import pars.androidquizapp.database.CategoryDatabase;

public class AddCategoryActivity extends AppCompatActivity {

    @BindView(R.id.category_name)
    EditText categoryName;
    @BindView(R.id.add_category)
    Button add_category;
    public Category category;
    public CategoryDatabase categoryDatabase;
    private boolean update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        ButterKnife.bind(this);
        categoryDatabase = CategoryDatabase.getInstance(AddCategoryActivity.this);

        //Receive the intent data
        if ( (category = (Category) getIntent().getSerializableExtra("category"))!=null ){
            update = true;
            add_category.setText("Update");
            categoryName.setText(category.getCategory());
        }
        //click the save button
        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update){
                    category.setCategory(categoryName.getText().toString());
                    categoryDatabase.categoryDao().updateCategory(category);
                    setResult(category,2);
                } else {
                    category = new Category(categoryName.getText().toString());
                    new InsertTask(AddCategoryActivity.this, category).execute();
                }
            }
        });
    }

    private void setResult(Category category, int flag){
        setResult(flag,new Intent().putExtra("category",category));
        Toast.makeText(AddCategoryActivity.this, "Category Successfully Added",Toast.LENGTH_LONG).show();

        //This goes back to the HomeActivity
        startActivity(new Intent(AddCategoryActivity.this, HomeActivity.class));
    }

    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<AddCategoryActivity> activityReference;
        public Category category;

        // only retain a weak reference to the activity
        InsertTask(AddCategoryActivity context, Category category) {
            activityReference = new WeakReference<>(context);
            this.category = category;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            long j = activityReference.get().categoryDatabase.categoryDao().save(category);
            category.setId(j);
            return true;
        }

        // onPostExecute runs on main thread
        @Override protected void onPostExecute(Boolean bool) {
            if (bool){
                activityReference.get().setResult(category,1);
                //activityReference.get().finish();
            }
        }
    }
}
