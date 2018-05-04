package pars.androidquizapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Princess on 16/04/2018.
 */

@Database(entities = {Category.class,
                        Question.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDao();
    public abstract QuestionDao questionDao();

    private static CategoryDatabase categoryDB;

    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ CategoryDatabase getInstance(Context context) {
        if (null == categoryDB) {
            categoryDB = buildDatabaseInstance(context);
        }
        return categoryDB;
    }

    private static CategoryDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                CategoryDatabase.class,
                "categorydb.db").allowMainThreadQueries().build();
    }

    public  void cleanUp(){
        categoryDB = null;
    }

}
