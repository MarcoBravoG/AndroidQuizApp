package pars.androidquizapp.playquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.MainDatabase;
import pars.androidquizapp.data.Question;

public class PlayQuizFragment extends Fragment implements PlayQuizContract.View{

    private PlayQuizContract.Presenter mPresenter;
    private MainDatabase database;
    private List<Question> questions = new ArrayList<>();
    private PlayQuizAdapter playQuizAdapter;
    private String category;

    @BindView(R.id.tv_empty)
    TextView emptyTextView;
    @BindView(R.id.quiz_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.submit_button)
    Button submitButton;

    public PlayQuizFragment() {
        // Required empty public constructor
    }

    public static PlayQuizFragment newInstance(){
        return new PlayQuizFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = MainDatabase.getInstance(getActivity());
        mPresenter = new PlayQuizPresenter(this, database.questionDao());
    }

    @Override
    public void setPresenter(PlayQuizContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_play_quiz, container, false);
        ButterKnife.bind(this, root);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        playQuizAdapter = new PlayQuizAdapter(getActivity(), questions);
        recyclerView.setAdapter(playQuizAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        category = getActivity().getIntent().getExtras().getString("category");
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.fetchQuestions(category);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showQuestions(List<Question> questions) {
        if(questions.isEmpty()){
            //showEmptyMessage();
        } else {
            //emptyTextView.setVisibility(View.GONE);
            //submitButton.setVisibility(View.VISIBLE);
            playQuizAdapter.setValues(questions);
        }
    }

    @Override
    public void showScore() {

    }

    @Override
    public void showEmptyMessage() {
        emptyTextView.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.GONE);
    }

}
