package pars.androidquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAMET AYDIN on 1.04.2018.
 */

public class Database extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "quiz";
    private static final int DATABASE_VERSION = 2;

    public static final String CATEGORIES_TABLE = "categories";
    public static final String ROW_CATEGORY_ID = "category_id";
    public static final String ROW_CATEGORY_NAME = "category_name";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CATEGORIES_TABLE + "(" +
                ROW_CATEGORY_ID + " INTEGER PRIMARY KEY," +
                ROW_CATEGORY_NAME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE);
        onCreate(db);
    }

    public void AddCategory (String category_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ROW_CATEGORY_NAME,category_name.trim());

        db.insert(CATEGORIES_TABLE, null, cv);
        db.close();
    }

    public List<String> CategoryDataList()
    {
        List<String> datas = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] rows = {ROW_CATEGORY_ID,ROW_CATEGORY_NAME};
        Cursor cursor = db.query(CATEGORIES_TABLE,rows,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            datas.add(cursor.getInt(0) + " - " + cursor.getString(1));
        }

        return  datas;
    }


}
