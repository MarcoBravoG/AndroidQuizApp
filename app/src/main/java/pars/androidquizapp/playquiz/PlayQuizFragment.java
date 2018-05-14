package pars.androidquizapp.playquiz;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pars.androidquizapp.R;
import pars.androidquizapp.data.Question;

public class PlayQuizFragment extends Fragment implements PlayQuizContract.View{



    public PlayQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_quiz, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showQuestions(List<Question> questions) {

    }

    @Override
    public void showScore() {

    }

}
