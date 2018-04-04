package pars.androidquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAMET AYDIN on 1.04.2018.
 */

public class Database extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "quiz";
    private static final int DATABASE_VERSION = 3;

    public static final String CATEGORIES_TABLE = "categories";
    public static final String ROW_CATEGORY_ID = "category_id";
    public static final String ROW_CATEGORY_NAME = "category_name";

    public static final String QUESTIONS_TABLE = "questions";
    public static final String ROW_QUESTION_ID = "question_id";
    public static final String ROW_QUESTION_CATEGORY_ID = "question_category_id";
    public static final String ROW_QUESTION = "question";
    public static final String ROW_OPTA = "opta";
    public static final String ROW_OPTB = "optb";
    public static final String ROW_OPTC = "optc";
    public static final String ROW_OPTD = "optd";
    public static final String ROW_ANS = "ans";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CATEGORIES_TABLE + "(" +
                ROW_CATEGORY_ID + " INTEGER PRIMARY KEY," +
                ROW_CATEGORY_NAME + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + QUESTIONS_TABLE + "(" +
                ROW_QUESTION_ID + " INTEGER PRIMARY KEY," +
                ROW_QUESTION_CATEGORY_ID + " INTEGER NOT NULL," +
                ROW_QUESTION + " TEXT NOT NULL," +
                ROW_OPTA + " TEXT NOT NULL," +
                ROW_OPTB + " TEXT NOT NULL," +
                ROW_OPTC + " TEXT NOT NULL," +
                ROW_OPTD + " TEXT NOT NULL," +
                ROW_ANS + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
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

    public void AddQuestion (Integer question_category_id, String question, String opta, String optb,String optc,String optd,String ans)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ROW_QUESTION_CATEGORY_ID,question_category_id);
        cv.put(ROW_QUESTION,question);
        cv.put(ROW_OPTA,opta);
        cv.put(ROW_OPTB,optb);
        cv.put(ROW_OPTC,optc);
        cv.put(ROW_OPTD,optd);
        cv.put(ROW_ANS,ans);


        db.insert(QUESTIONS_TABLE, null, cv);
        db.close();
    }

    public void UpdateCategory (String category_name,String rowId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ROW_CATEGORY_NAME,category_name.trim());

        db.update(CATEGORIES_TABLE, cv, ROW_CATEGORY_ID + "=" + rowId, null);
        db.close();
    }

    public void UpdateQuestion (String questionId,Integer question_category_id, String question, String opta, String optb,String optc,String optd,String ans)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ROW_QUESTION_CATEGORY_ID,question_category_id);
        cv.put(ROW_QUESTION,question);
        cv.put(ROW_OPTA,opta);
        cv.put(ROW_OPTB,optb);
        cv.put(ROW_OPTC,optc);
        cv.put(ROW_OPTD,optd);
        cv.put(ROW_ANS,ans);

        db.update(QUESTIONS_TABLE, cv, ROW_QUESTION_ID + "=" + questionId, null);
        db.close();
    }

    public void DeleteCategory (String rowId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(CATEGORIES_TABLE, ROW_CATEGORY_ID + "=" + rowId, null);
        db.close();
    }

    public void DeleteQuestion (String questionId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(QUESTIONS_TABLE, ROW_QUESTION_ID + "=" + questionId, null);
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

    public List<String> QuestionDataList()
    {
        List<String> datas = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] rows = {ROW_QUESTION_ID,ROW_QUESTION_CATEGORY_ID,ROW_QUESTION,ROW_OPTA,ROW_OPTB,ROW_OPTC,ROW_OPTD,ROW_ANS};
        Cursor cursor = db.query(QUESTIONS_TABLE,rows,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            datas.add(cursor.getInt(0)+ "- " + " Category ID= " + cursor.getInt(1) + " Question= " + cursor.getString(2) + " Option A= " + cursor.getString(3)
            + " Option B= " + cursor.getString(4) + " Option C= " + cursor.getString(5) + " Option D= " + cursor.getString(6) + " Answer Option= " + cursor.getString(7));
        }

        return  datas;
    }

    public List<String> CategoryDataNames()
    {
        List<String> datas = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] rows = {ROW_CATEGORY_ID,ROW_CATEGORY_NAME};
        Cursor cursor = db.query(CATEGORIES_TABLE,rows,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            datas.add(cursor.getString(0)+ "-" +cursor.getString(1));
        }

        return  datas;
    }

    public String[] getquestion(String questionId)
    {
        String[] datas = new String[8];
        SQLiteDatabase db = this.getReadableDatabase();
        String[] rows = {ROW_QUESTION_ID,ROW_QUESTION_CATEGORY_ID,ROW_QUESTION,ROW_OPTA,ROW_OPTB,ROW_OPTC,ROW_OPTD,ROW_ANS};
        Cursor cursor = db.query(QUESTIONS_TABLE,rows,ROW_QUESTION_ID + "=?", new String[] {questionId}, null, null, null, null);
        if(cursor != null)
        {
            while(cursor.moveToNext()){
                datas[0] = Integer.toString(cursor.getInt(0)); // qid
                datas[1] = Integer.toString(cursor.getInt(1)); // cid
                datas[2] = cursor.getString(2); // q
                datas[3] = cursor.getString(3); // opta
                datas[4] = cursor.getString(4); // optb
                datas[5] = cursor.getString(5); // optc
                datas[6] = cursor.getString(6); // optd
                datas[7] = cursor.getString(7); // ans
            }
        }
        return datas;
    }

    public List<Integer>getQuestionsIDs(String catId)
    {
        List<Integer> datas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] rows = {ROW_QUESTION_ID};
        Cursor cursor = db.query(QUESTIONS_TABLE,rows,ROW_QUESTION_CATEGORY_ID + "=?", new String[] {catId}, null, null, null, null);
        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                datas.add(cursor.getInt(0));
            }
        }
        return datas;
    }




}
