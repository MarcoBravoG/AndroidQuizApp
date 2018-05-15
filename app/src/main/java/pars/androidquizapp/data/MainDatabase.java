package pars.androidquizapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Category.class,
                        Question.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDao();
    public abstract QuestionDao questionDao();

    private static MainDatabase categoryDB;

    // synchronized is use to avoid concurrent access in multi thread environment
    public static /*synchronized*/ MainDatabase getInstance(Context context) {
        if (null == categoryDB) {
            categoryDB = buildDatabaseInstance(context);
        }
        return categoryDB;
    }

    private static MainDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                MainDatabase.class,
                "maindb.db").allowMainThreadQueries().build();
    }

    public  void cleanUp(){
        categoryDB = null;
    }

}
