package pars.androidquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button playButton;
    Button quitButton;
    Button categoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button)findViewById(R.id.playButton);
        quitButton = (Button) findViewById(R.id.quitButton);
        categoriesButton = (Button) findViewById(R.id.CategoriesButton);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        // category sayfasında herhangi bir kategoriye tıklayınca  10 rastgele soru gelecek soruların hepsi cevaplandığında
        // doğru yanlış sayısını gösteren bir ekran gelecek

        // Sorular tablosu veritabanına eklenecek, soru ekle güncelle ve sil sayfaları eklenecek
    }
}
