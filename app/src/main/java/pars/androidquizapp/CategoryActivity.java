package pars.androidquizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryActivity extends AppCompatActivity {
    public static List<String> Datas = new ArrayList<>();
    public static ArrayAdapter<String> adapter;
    String CatId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Database database = new Database(CategoryActivity.this);
        Datas = database.CategoryDataNames();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);

        LinearLayout linearLayout2 = new LinearLayout(this);
        LinearLayout.LayoutParams linear2layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout2.setLayoutParams(linear2layoutParams);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(linearLayout2);

        for (int i=0; i<Datas.size(); i++) {

        String[] Categoryname = new String[Datas.size()];
        Categoryname[i] = Datas.get(i).substring(2);
        CatId = Datas.get(i).substring(0,1);
            //ID.substring(0,1)

        final Button category1 = new Button(this);
        category1.setTextSize(18);
        category1.setTextColor(Color.parseColor("#ffffff"));
        category1.setText(Categoryname[i]);
        category1.setId(Integer.parseInt(CatId));
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
                extras2.putString("catId",Integer.toString(category1.getId()));
                intent2.putExtras(extras2);
                startActivity(intent2);
            }
        });

        }

    }
}
