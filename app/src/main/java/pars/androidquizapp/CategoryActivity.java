package pars.androidquizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pars.androidquizapp.database.Category;
import pars.androidquizapp.database.CategoryDatabase;

public class CategoryActivity extends AppCompatActivity {

    public static List<String> data = new ArrayList<String>();
    public static List<Long> catId = new ArrayList<Long>();
    public static ArrayAdapter<String> adapter;
    public List<Category> category;
    private CategoryDatabase categoryDatabase;
    public LinearLayout linearLayout;
    public LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        categoryDatabase = CategoryDatabase.getInstance(this);

        linearLayout = findViewById(R.id.mainLinearLayout);

        linearLayout2 = new LinearLayout(this);
        LinearLayout.LayoutParams linear2layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout2.setLayoutParams(linear2layoutParams);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(linearLayout2);

        category = categoryDatabase.categoryDao().getAllCategories();
        Log.e("CATEGORY", "These are: " + category);
        for (Category cat : category) {
            data.add(cat.getCategory());
            catId.add(cat.getId());
        }

        for (int i=0; i<data.size(); i++) {

        final Button category1 = new Button(this);
        category1.setTextSize(18);
        category1.setTextColor(Color.parseColor("#ffffff"));
        category1.setText(data.get(i));
        Log.e("NEW DATA", "These are: " + data.get(i));
        category1.setId((int) Long.parseLong(String.valueOf(catId.get(i))));
        category1.setPadding(0,25,0,0);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        category1.setBackgroundColor(color);

        LinearLayout.LayoutParams buttonlayoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, 500);
        buttonlayoutParams.weight = 1;
        category1.setLayoutParams(buttonlayoutParams);

        linearLayout2.addView(category1);

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(CategoryActivity.this, MainGameActivity.class);
                Bundle extras2 = new Bundle();
                extras2.putString("cat", String.valueOf(category1.getText()));
                //extras2.putString("catId",Integer.toString(category1.getId()));
                intent2.putExtras(extras2);
                startActivity(intent2);
            }
        });

        }

    }
}
