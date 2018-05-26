package pars.androidquizapp.playquiz;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Question;

public class PlayQuizAdapter extends RecyclerView.Adapter<PlayQuizAdapter.PlayQuizViewHolder> {

    private Context context;
    private List<Question> questionList;
    public RadioButton radioButton;

    private int firstCorrectScore = 0;
    private int incorrectScore = 0;
    private int secondCorrectScore = 0;


    public PlayQuizAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public PlayQuizAdapter.PlayQuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.play_quiz_layout, parent, false);

        return new PlayQuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayQuizAdapter.PlayQuizViewHolder holder, int position) {
        Question data = questionList.get(position);
        holder.quizQuestion.setText(data.getQuestion());
        holder.radioButtonA.setText(data.getOptionA());
        holder.radioButtonB.setText(data.getOptionB());
        holder.radioButtonC.setText(data.getOptionC());
        holder.radioButtonD.setText(data.getOptionD());

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton)group.findViewById(checkedId);
                String radioValue = radioButton.getText().toString().toLowerCase();

                if(radioValue.equals(data.getAnswerOoption())){
                    firstCorrectScore += 1;
                } else if(!radioValue.equals(data.getAnswerOoption())){
                    secondCorrectScore += 1;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public int getCorrectScore(){
        return firstCorrectScore;
    }
    public int getIncorrectScore(){
        incorrectScore = questionList.size() - firstCorrectScore;
        return incorrectScore;
    }
    public int getBlankOption(){
        return firstCorrectScore + incorrectScore;
    }

    void setValues(List<Question> values){
        questionList = values;
        notifyDataSetChanged();
    }


    public class PlayQuizViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quiz_question)
        TextView quizQuestion;
        @BindView(R.id.radio_button_a)
        RadioButton radioButtonA;
        @BindView(R.id.radio_button_b)
        RadioButton radioButtonB;
        @BindView(R.id.radio_button_c)
        RadioButton radioButtonC;
        @BindView(R.id.radio_button_d)
        RadioButton radioButtonD;

        public RadioGroup radioGroup;

        public PlayQuizViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            radioGroup = itemView.findViewById(R.id.radio_group);
        }
        public void clearRadioButton(){
            radioGroup.clearCheck();
        }

    }

}
