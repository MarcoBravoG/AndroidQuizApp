package pars.androidquizapp.addquestion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.categories.CategoriesActivity;
import pars.androidquizapp.data.MainDatabase;
import pars.androidquizapp.data.Question;


public class AddQuestionFragment extends Fragment implements AddQuestionContract.view {


    private AddQuestionContract.Presenter mPresenter;
    private MainDatabase database;

    public static List<String> data = new ArrayList<String>();
    long categoryId;
    long questionId;

    @BindView(R.id.question)
    EditText question;
    @BindView(R.id.opta)
    EditText optA;
    @BindView(R.id.optb)
    EditText optB;
    @BindView(R.id.optc)
    EditText optC;
    @BindView(R.id.optd)
    EditText optD;
    @BindView(R.id.ans)
    EditText ans;
    @BindView(R.id.add_question)
    Button btnQuestion;


    public AddQuestionFragment() {
        // Required empty public constructor
    }

    public static AddQuestionFragment newInstance() {
        return new AddQuestionFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = MainDatabase.getInstance(getActivity());
        mPresenter = new AddQuestionPresenter(this, database.questionDao());
    }

    @Override
    public void setPresenter(AddQuestionContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_question, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Receive category and question ids as intents to be able to insert and query the database
        categoryId = getActivity().getIntent().getExtras().getLong("categoryId");
        questionId = getActivity().getIntent().getExtras().getLong("question_id");

        //Since the above ids are received at different intents, a check was made to avoid conflict
        if(categoryId != 0 && questionId == 0){
            addQuestion();
        } else if(questionId != 0 && categoryId == 0){
            mPresenter.fetchQuestionToUpdate(questionId);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    //This adds question to the database
    public void addQuestion(){
        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuestion = question.getText().toString();
                String optionA = optA.getText().toString();
                String optionB = optB.getText().toString();
                String optionC = optC.getText().toString();
                String optionD = optD.getText().toString();
                String answer = ans.getText().toString().toLowerCase();

                mPresenter.saveQuestion(categoryId, newQuestion, optionA, optionB,
                        optionC, optionD, answer);
                Intent intent = new Intent(getActivity(), CategoriesActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    //This shows the question parameters to be updated
    @Override
    public void showQuestionToUpdate(Question questions) {

        long categoryIdQuestion = questions.getCategoryId();
        question.setText(questions.getQuestion());
        optA.setText(questions.getOptionA());
        optB.setText(questions.getOptionB());
        optC.setText(questions.getOptionC());
        optD.setText(questions.getOptionD());
        ans.setText(questions.getAnswerOoption());

        btnQuestion.setText("Update");

        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuestion = question.getText().toString();
                String optionA = optA.getText().toString();
                String optionB = optB.getText().toString();
                String optionC = optC.getText().toString();
                String optionD = optD.getText().toString();
                String answer = ans.getText().toString().toLowerCase();

                questions.setCategoryId(categoryIdQuestion);
                questions.setQuestion(newQuestion);
                questions.setOptionA(optionA);
                questions.setOptionB(optionB);
                questions.setOptionC(optionC);
                questions.setOptionD(optionD);
                questions.setAnswerOoption(answer);

                //Insert to database
                mPresenter.updateQuestion(questions);
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
    }
}
