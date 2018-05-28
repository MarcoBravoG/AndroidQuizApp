package pars.androidquizapp.playquiz;


public class AnswerModel {
    public boolean isCorrect;
    public int id;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AnswerModel)){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (((AnswerModel)obj).id == id){
            return true;
        }
        return false;
    }
}
