package pars.androidquizapp.addquestion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.categories.CategoriesActivity;
import pars.androidquizapp.data.Category;
import pars.androidquizapp.data.MainDatabase;


public class AddQuestionFragment extends Fragment implements AddQuestionContract.view {


    private AddQuestionContract.Presenter mPresenter;
    private MainDatabase database;

    public static List<String> data = new ArrayList<String>();
    String category;

    @BindView(R.id.category_type)
    TextView categoryType;
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

        category = getActivity().getIntent().getExtras().getString("category");

        categoryType.setText("Category: " + category);
        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuestion = question.getText().toString();
                String optionA = optA.getText().toString();
                String optionB = optB.getText().toString();
                String optionC = optC.getText().toString();
                String optionD = optD.getText().toString();
                String answer = ans.getText().toString().toLowerCase();

                mPresenter.saveQuestion(category, newQuestion, optionA, optionB,
                        optionC, optionD, answer);
                startActivity(new Intent(getActivity(), CategoriesActivity.class));
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
