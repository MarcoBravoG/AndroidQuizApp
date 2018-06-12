package pars.androidquizapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;


@Entity(tableName = "questions_table", foreignKeys =
@ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id"))
public class Question implements Serializable {

    @PrimaryKey(autoGenerate = true)
    long id;

    @NonNull
    @ColumnInfo(name = "category_id")
    long categoryId;

    @NonNull
    @ColumnInfo(name = "question")
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answerOoption;

    public Question(long categoryId, String question,
                    String optionA, String optionB, String optionC,
                    String optionD, String answerOoption) {
        this.categoryId = categoryId;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerOoption = answerOoption;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull long categoryId) {
        this.categoryId = categoryId;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswerOoption() {
        return answerOoption;
    }

    public void setAnswerOoption(String answerOoption) {
        this.answerOoption = answerOoption;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", categoryId='" + categoryId + '\'' +
                ", question='" + question + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answerOoption='" + answerOoption + '\'' +
                '}';
    }
}
