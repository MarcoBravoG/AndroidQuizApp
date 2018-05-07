package pars.androidquizapp.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Princess on 16/04/2018.
 */

@android.arch.persistence.room.Database(entities = {Category.class,
                        Question.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract CategoryDao categoryDao();
    public abstract QuestionDao questionDao();

    private static Database categoryDB;

    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ Database getInstance(Context context) {
        if (null == categoryDB) {
            categoryDB = buildDatabaseInstance(context);
        }
        return categoryDB;
    }

    private static Database buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                Database.class,
                "categorydb.db").allowMainThreadQueries().build();
    }

    public  void cleanUp(){
        categoryDB = null;
    }

}
