package pars.androidquizapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 10 soru limiti ve kategori where konulacak -> getQuestionsIDs metoduna

public class MainGameActivity extends AppCompatActivity {
    public static List<Integer> Datas = new ArrayList<>();
    Button buttonA, buttonB, buttonC, buttonD;
    TextView question;
    Integer index = 0 , correct = 0, incorrect = 0;
    String[] questiondata = new String[8];
    Database database = new Database(MainGameActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        question = (TextView) findViewById(R.id.question);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        Datas = database.getQuestionsIDs();

        Collections.shuffle(Datas);

        questiondata = database.getquestion(Integer.toString(Datas.get(index)));

        updateQuestion();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(questiondata[7].equals("a") || questiondata[7].equals("A"))
            {
                    buttonA.setBackgroundColor(Color.parseColor("#008000"));

                if(index < Datas.size() - 1)
                {
                    correct++;
                    disableButton();
                    correctDialog("#008000","Correct");
                }
                else{
                    Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                }

            }

            else
            {
                buttonA.setBackgroundColor(Color.parseColor("#FF0000"));
                if(index < Datas.size() - 1)
                {
                    incorrect++;
                    disableButton();
                    correctDialog("#FF0000","Incorrect");
                }
                else{
                    Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questiondata[7].equals("b") || questiondata[7].equals("B"))
                {
                    buttonB.setBackgroundColor(Color.parseColor("#008000"));

                    if(index < Datas.size() - 1)
                    {
                        correct++;
                        disableButton();
                        correctDialog("#008000","Correct");
                    }
                    else{
                        Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                    }

                }

                else
                {
                    buttonB.setBackgroundColor(Color.parseColor("#FF0000"));
                    if(index < Datas.size() - 1)
                    {
                        incorrect++;
                        disableButton();
                        correctDialog("#FF0000","Incorrect");
                    }
                    else{
                        Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questiondata[7].equals("C") || questiondata[7].equals("C"))
                {
                    buttonC.setBackgroundColor(Color.parseColor("#008000"));

                    if(index < Datas.size() - 1)
                    {
                        correct++;
                        disableButton();
                        correctDialog("#008000","Correct");
                    }
                    else{
                        Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                    }

                }

                else
                {
                    buttonC.setBackgroundColor(Color.parseColor("#FF0000"));
                    if(index < Datas.size() - 1)
                    {
                        incorrect++;
                        disableButton();
                        correctDialog("#FF0000","Incorrect");
                    }
                    else{
                        Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questiondata[7].equals("d") || questiondata[7].equals("D"))
                {
                    buttonD.setBackgroundColor(Color.parseColor("#008000"));

                    if(index < Datas.size() - 1)
                    {
                        correct++;
                        disableButton();
                        correctDialog("#008000","Correct");
                    }
                    else{
                        Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                    }

                }

                else
                {
                    buttonD.setBackgroundColor(Color.parseColor("#FF0000"));
                    if(index < Datas.size() - 1)
                    {
                        incorrect++;
                        disableButton();
                        correctDialog("#FF0000","Incorrect");
                    }
                    else{
                        Toast.makeText(MainGameActivity.this, "RESULT PAGE", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void updateQuestion() {
        question.setText(questiondata[2]);
        buttonA.setText(questiondata[3]);
        buttonB.setText(questiondata[4]);
        buttonC.setText(questiondata[5]);
        buttonD.setText(questiondata[6]);
    }

    public void resetColor() {
        buttonA.setBackgroundColor(Color.parseColor("#FFFFFF"));
        buttonB.setBackgroundColor(Color.parseColor("#FFFFFF"));
        buttonC.setBackgroundColor(Color.parseColor("#FFFFFF"));
        buttonD.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

    public void correctDialog(String color,String text) {
        final Dialog dialogCorrect = new Dialog(MainGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.activity_dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        Button buttonNext = (Button) dialogCorrect.findViewById(R.id.dialogNext);

        correctText.setText(text);
        correctText.setTextColor(Color.parseColor(color));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCorrect.dismiss();

                index++;

                questiondata = database.getquestion(Integer.toString(Datas.get(index)));

                updateQuestion();

                resetColor();

                enableButton();
            }
        });
    }
}
