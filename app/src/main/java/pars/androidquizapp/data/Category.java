package pars.androidquizapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;


@Entity(tableName = "categories_table")
public class Category implements Serializable{

    @PrimaryKey(autoGenerate = true)
    long id;

    @NonNull
    @ColumnInfo(name = "category")
    String category;

    @ColumnInfo(name = "number_of_question_in_this_category")
    int numberOfQuestionInThisCategory;

    public Category(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfQuestionInThisCategory() {
        return numberOfQuestionInThisCategory;
    }

    public void setNumberOfQuestionInThisCategory(int numberOfQuestionInThisCategory) {
        this.numberOfQuestionInThisCategory = numberOfQuestionInThisCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", numberOfQuestionInThisCategory=" + numberOfQuestionInThisCategory +
                '}';
    }

}
