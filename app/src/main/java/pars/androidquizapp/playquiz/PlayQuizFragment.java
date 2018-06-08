package pars.androidquizapp.playquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import pars.androidquizapp.R;
import pars.androidquizapp.addquestion.AddQuestionActivity;
import pars.androidquizapp.categories.CategoriesActivity;
import pars.androidquizapp.data.MainDatabase;
import pars.androidquizapp.data.Question;

public class PlayQuizFragment extends Fragment implements PlayQuizContract.View {

    private PlayQuizContract.Presenter mPresenter;
    private MainDatabase database;
    private List<Question> questions = new ArrayList<>();
    private PlayQuizAdapter playQuizAdapter;
    private LinearLayoutManager linearLayoutManager;
    private long categoryId;
    private AlertDialog alertDialog = null;
    private FloatingActionButton fab;

    /**
     * This makes button show at the end of a recylerview item
     */
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0 && linearLayoutManager.findLastVisibleItemPosition() == playQuizAdapter.getItemCount() - 1) {
                submitButton.setVisibility(View.VISIBLE);
            } else {
                submitButton.setVisibility(View.GONE);
            }
        }
    };

    @BindView(R.id.tv_empty)
    TextView emptyTextView;
    @BindView(R.id.quiz_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.submit_button)
    Button submitButton;


    public PlayQuizFragment() {
        // Required empty public constructor
    }

    public static PlayQuizFragment newInstance() {
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

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        playQuizAdapter = new PlayQuizAdapter(getActivity(), questions);
        recyclerView.setAdapter(playQuizAdapter);
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.invalidate();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        categoryId = getActivity().getIntent().getExtras().getLong("categoryId");

        fab = getActivity().findViewById(R.id.fab);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This checks the array of set integer if it contains 0
                // e.g 0 means radiobutton(option) not selected
                boolean isAllQuestionsAnswered = playQuizAdapter.answersQuestions.contains(0);

                if (isAllQuestionsAnswered) {
                    Toast.makeText(getContext(), "You cannot leave any blank question", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.calculateScore();

                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenter.fetchQuestions(categoryId);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showQuestions(List<Question> questions) {
        if (questions.isEmpty()) {
            showEmptyMessage();

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), AddQuestionActivity.class);
                    intent.putExtra("categoryId", categoryId);
                    startActivity(intent);
                }
            });
        } else {
            emptyTextView.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            playQuizAdapter.setValues(questions);
        }
    }

    @Override
    public void showScore() {

        //Create an alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set title value
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(getActivity().getIntent());
            }
        })
                .setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getContext(), CategoriesActivity.class));
                    }
                });

        //Get custom view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.score_dialog, null);
        builder.setView(dialogView);

        final TextView correctView = dialogView.findViewById(R.id.correct);
        final TextView incorrectView = dialogView.findViewById(R.id.incorrect);

        correctView.setText(String.format(Locale.ENGLISH, "%s: %d", "Correct", playQuizAdapter.getCorrectScore()));
        incorrectView.setText(String.format(Locale.ENGLISH, "%s: %d", "Incorrect", playQuizAdapter.getIncorrectScore()));

        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void showEmptyMessage() {
        emptyTextView.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.GONE);
    }

}
