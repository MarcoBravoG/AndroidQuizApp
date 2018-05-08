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

/**
 * Created by Princess on 24/04/2018.
 */

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
       /* Question data = questionList.get(position);
        holder.questionId.setText(String.valueOf(data.getId())+".");
        holder.questionCategoryName.setText("Category: "+ data.getQuestionCategory());
        holder.questionView.setText(data.getQuestion());
        holder.optionsView.setText("A) "+ data.getOptionA()+
                " B) "+ data.getOptionB()+
                " C) "+ data.getOptionC()+
                " D) "+ data.getOptionD());
        holder.questionAnswer.setText("Ans: "+ data.getAnswerOoption());
    */}

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /*@BindView(R.id.question_id)
        TextView questionId;
        @BindView(R.id.question_category_name)
        TextView questionCategoryName;
        @BindView(R.id.question)
        TextView questionView;
        @BindView(R.id.options)
        TextView optionsView;
        @BindView(R.id.answer)
        TextView questionAnswer;*/

        public QuestionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onQuestionClicked.onClick(getAdapterPosition());
        }
    }

    public interface OnQuestionClicked {
        //void onClick(Category data);
        void onClick(int position);
    }
}
