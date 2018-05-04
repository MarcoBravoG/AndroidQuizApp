package pars.androidquizapp.question.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.category.adapter.CategoryAdapter;
import pars.androidquizapp.category.view.AddCategoryActivity;
import pars.androidquizapp.category.view.CategoryFragment;
import pars.androidquizapp.database.Category;
import pars.androidquizapp.database.CategoryDatabase;
import pars.androidquizapp.database.Question;
import pars.androidquizapp.question.adapter.QuestionAdapter;


public class QuestionFragment extends Fragment implements QuestionAdapter.OnQuestionClicked {

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.tv_empty)
    public TextView emptyView;

    public CategoryDatabase categoryDatabase;
    private QuestionAdapter questionAdapter;
    public List<Question> questionList;
    int position;

    public QuestionFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        questionList = new ArrayList<>();
        questionAdapter = new QuestionAdapter(getActivity(), questionList, this);

        displayQuestion();
        recyclerView.setAdapter(questionAdapter);
        return rootView;
    }

    private void displayQuestion(){
        categoryDatabase = CategoryDatabase.getInstance(getContext());
        new RetrieveTask(this).execute();
    }

    @Override
    public void onClick(int position) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Select Options")
                .setItems(new String[]{"Update", "Delete"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                QuestionFragment.this.position = position;
                                startActivityForResult(
                                        new Intent(getActivity(),
                                                AddQuestionActivity.class).putExtra("question", questionList.get(position)),
                                        100);
                                break;
                            case 1:
                                categoryDatabase.questionDao().deleteQuestion(questionList.get(position));
                                questionList.remove(position);
                                listVisibility();
                                break;
                        }
                    }
                }).show();
    }


    public static class RetrieveTask extends AsyncTask<Void,Void,List<Question>> {

        private WeakReference<QuestionFragment> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(QuestionFragment context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Question> doInBackground(Void... voids) {
            if (activityReference.get() != null) {
                Log.e("RESULT", "categories" +
                        activityReference.get().categoryDatabase.questionDao().getAllQuestions());
                return activityReference.get().categoryDatabase.questionDao().getAllQuestions();
            }else{
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            if (questions != null && questions.size() > 0) {
                activityReference.get().questionList.clear();
                activityReference.get().questionList.addAll(questions);
                //hides empty text view
                activityReference.get().emptyView.setVisibility(View.GONE);
                activityReference.get().questionAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(questionList.isEmpty()){
            displayQuestion();
        } else{
            displayQuestion();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode > 0 ){
            if( resultCode == 1){
                questionList.add((Question) data.getSerializableExtra("question"));
            }else if( resultCode == 2){
                questionList.set(position,(Question) data.getSerializableExtra("question"));
            }
            listVisibility();
        }
    }

    private void listVisibility(){
        int emptyMsgVisibility = View.GONE;
        if (questionList.size() == 0){ // no item to display
            if (emptyView.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        emptyView.setVisibility(emptyMsgVisibility);
        questionAdapter.notifyDataSetChanged();
        onResume();
    }

    @Override
    public void onDestroy() {
        categoryDatabase.cleanUp();
        super.onDestroy();
    }

}
