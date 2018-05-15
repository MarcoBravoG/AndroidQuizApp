package pars.androidquizapp.questions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Question;



public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private Context context;
    private List<Question> questionList;
    private OnQuestionClicked onQuestionClicked;

    public QuestionsAdapter(Context context, List<Question> questionList, OnQuestionClicked onQuestionClicked) {
        this.context = context;
        this.questionList = questionList;
        this.onQuestionClicked = onQuestionClicked;
    }

    @Override
    public QuestionsAdapter.QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_layout, parent, false);

        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionsAdapter.QuestionViewHolder holder, int position) {
        Question data = questionList.get(position);
        holder.question.setText(data.getQuestion());
        holder.questionOptionA.setText("A) " + data.getOptionA());
        holder.questionOptionB.setText("B) " + data.getOptionB());
        holder.questionOptionC.setText("C) " + data.getOptionC());
        holder.questionOptionD.setText("D) " + data.getOptionD());

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    Question getItem(int position){
        return questionList.get(position);
    }

    void setValues(List<Question> values){
        questionList = values;
        notifyDataSetChanged();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.db_question)
        TextView question;
        @BindView(R.id.db_optionA)
        TextView questionOptionA;
        @BindView(R.id.db_optionB)
        TextView questionOptionB;
        @BindView(R.id.db_optionC)
        TextView questionOptionC;
        @BindView(R.id.db_optionD)
        TextView questionOptionD;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Question question = getItem(position);
            onQuestionClicked.onQuestionClick(question);
        }
    }

    public interface OnQuestionClicked {
        void onQuestionClick(Question question);
    }
}
