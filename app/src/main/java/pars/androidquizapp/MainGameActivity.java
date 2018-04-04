package pars.androidquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

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
    String CatId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        question = (TextView) findViewById(R.id.question);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        CatId = getIntent().getExtras().getString("catId");
        Datas = database.getQuestionsIDs(CatId);

        Collections.shuffle(Datas);

        questiondata = database.getquestion(Integer.toString(Datas.get(index)));

        updateQuestion();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(questiondata[7].equals("a") || questiondata[7].equals("A"))
            {
                buttonA.setBackgroundColor(Color.parseColor("#008000"));
                correct++;

                if(index < Datas.size() - 1)
                {
                    disableButton();
                    correctDialog("#008000","Correct");
                }
                else{
                    Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                    Bundle extras2 = new Bundle();
                    extras2.putInt("correct",correct);
                    extras2.putInt("incorrect",incorrect);
                    intent2.putExtras(extras2);
                    startActivity(intent2);
                }

            }

            else
            {
                buttonA.setBackgroundColor(Color.parseColor("#FF0000"));
                incorrect++;
                if(index < Datas.size() - 1)
                {
                    disableButton();
                    correctDialog("#FF0000","Incorrect");
                }
                else{
                    Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                    Bundle extras2 = new Bundle();
                    extras2.putInt("correct",correct);
                    extras2.putInt("incorrect",incorrect);
                    intent2.putExtras(extras2);
                    startActivity(intent2);
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
                    correct++;
                    if(index < Datas.size() - 1)
                    {
                        disableButton();
                        correctDialog("#008000","Correct");
                    }
                    else{
                        Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                        Bundle extras2 = new Bundle();
                        extras2.putInt("correct",correct);
                        extras2.putInt("incorrect",incorrect);
                        intent2.putExtras(extras2);
                        startActivity(intent2);
                    }

                }

                else
                {
                    buttonB.setBackgroundColor(Color.parseColor("#FF0000"));
                    incorrect++;
                    if(index < Datas.size() - 1)
                    {
                        disableButton();
                        correctDialog("#FF0000","Incorrect");
                    }
                    else{
                        Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                        Bundle extras2 = new Bundle();
                        extras2.putInt("correct",correct);
                        extras2.putInt("incorrect",incorrect);
                        intent2.putExtras(extras2);
                        startActivity(intent2);
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
                    correct++;
                    if(index < Datas.size() - 1)
                    {
                        disableButton();
                        correctDialog("#008000","Correct");
                    }
                    else{
                        Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                        Bundle extras2 = new Bundle();
                        extras2.putInt("correct",correct);
                        extras2.putInt("incorrect",incorrect);
                        intent2.putExtras(extras2);
                        startActivity(intent2);
                    }

                }

                else
                {
                    buttonC.setBackgroundColor(Color.parseColor("#FF0000"));
                    incorrect++;
                    if(index < Datas.size() - 1)
                    {
                        disableButton();
                        correctDialog("#FF0000","Incorrect");
                    }
                    else{
                        Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                        Bundle extras2 = new Bundle();
                        extras2.putInt("correct",correct);
                        extras2.putInt("incorrect",incorrect);
                        intent2.putExtras(extras2);
                        startActivity(intent2);
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
                    correct++;
                    if(index < Datas.size() - 1)
                    {
                        disableButton();
                        correctDialog("#008000","Correct");
                    }
                    else{
                        Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                        Bundle extras2 = new Bundle();
                        extras2.putInt("correct",correct);
                        extras2.putInt("incorrect",incorrect);
                        intent2.putExtras(extras2);
                        startActivity(intent2);
                    }

                }

                else
                {
                    buttonD.setBackgroundColor(Color.parseColor("#FF0000"));
                    incorrect++;
                    if(index < Datas.size() - 1)
                    {
                        disableButton();
                        correctDialog("#FF0000","Incorrect");
                    }
                    else{
                        Intent intent2 = new Intent(MainGameActivity.this, QuizResultActivity.class);
                        Bundle extras2 = new Bundle();
                        extras2.putInt("correct",correct);
                        extras2.putInt("incorrect",incorrect);
                        intent2.putExtras(extras2);
                        startActivity(intent2);
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
