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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Question;

public class PlayQuizAdapter extends RecyclerView.Adapter<PlayQuizAdapter.PlayQuizViewHolder> {

    private Context context;
    private List<Question> questionList;
    public RadioButton radioButton;
    ArrayList<Integer> answersQuestions = new ArrayList<>();

    List<AnswerModel> selectedIds = new ArrayList<>();

    private int correctScore = 0;
    private int incorrectScore = 0;
    private int emptyScore = 0;


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
                radioButton = group.findViewById(checkedId);
                String radioValue = radioButton.getText().toString().toLowerCase();

                /* This sets each default value in the array e.g [0,0,0,0]
                to 1 when a radiobutton is clicked
                */
                answersQuestions.set(position, 1);

                if(selectedIds.get(position) != null && selectedIds.get(position).id == group.getId()){
                    if (radioValue.equals(data.getAnswerOoption())) {
                        //Do nothing
                        selectedIds.get(position).isCorrect = true;
                    } else if (!radioValue.equals(data.getAnswerOoption())) {
                        emptyScore += 1;
                    }
                }

                //This compares the answer from radiobutton with the answer in the database
                if (radioValue.equals(data.getAnswerOoption())) {
                    correctScore += 1;
                } else if (!radioValue.equals(data.getAnswerOoption())) {
                    emptyScore += 1;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public int getCorrectScore() {
        return correctScore;
    }

    public int getIncorrectScore() {
        incorrectScore = questionList.size() - correctScore;
        return incorrectScore;
    }

    void setValues(List<Question> values) {
        questionList = values;

        /**
         * This gets the questions list size
         * and generates a default array of integer
         */

        int[] defaultValues = new int[questionList.size()];
        for (int value : defaultValues) {
            answersQuestions.add(value);
        }
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
    }

}
