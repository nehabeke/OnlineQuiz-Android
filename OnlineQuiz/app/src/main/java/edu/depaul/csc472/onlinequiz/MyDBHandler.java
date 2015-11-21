package edu.depaul.csc472.onlinequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Neha on 11/5/2015.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "online.db";
    private static final String TABLE_QUESTION="questions";
    private static final String TABLE_USERS="users";
    private static final String TABLE_USER_QUIZ="userquiz";


    private static final String USER_ID ="_id";
    private static final String USER_FIRST_NAME="_fname";
    private static final String USER_LAST_NAME="_lname";
    private static final String USER_EMAIL_ID="_emailid";
    private static final String USER_PASSWORD="_password";
    /************************************************************************************************************/
    // tasks table name

    // tasks Table Columns names
    private static final String QUESTION_ID = "_id";
    private static final String QUIZ_QUESTION = "_question";
    private static final String KEY_ANSWER = "_answer"; //correct option
    private static final String KEY_OPTA= "_opt1"; //option a
    private static final String KEY_OPTB= "_opt2"; //option b
    private static final String KEY_OPTC= "_opt3"; //option c
    private static final String KEY_OPTD= "_opt4"; //option d

    /************************************************************************************************************/

    private static final String USER_QUIZ_ID = "_userId";
    private static final String USER_QUIZ_QUESTION_ID = "_questionId";
    private static final String IS_ANSWER_CORRECT = "_isAnswerCorrect";

/*******************************************************************************************************************/
    String CREATE_USER_TABLE = "CREATE TABLE " +
            TABLE_USERS + "("
            + USER_ID + " INTEGER PRIMARY KEY," + USER_FIRST_NAME
            + " TEXT," + USER_LAST_NAME + " TEXT," +USER_EMAIL_ID + " TEXT," +USER_PASSWORD+ " TEXT"+ ")";

/********************************************************************************************************************/
    String CREATE_QUESTION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + " ( "
            + QUESTION_ID + " INTEGER PRIMARY KEY, " + QUIZ_QUESTION
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+ KEY_OPTC +" TEXT, " + KEY_OPTD+" TEXT)";
/********************************************************************************************************************/

String CREATE_USER_QUIZ_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_QUIZ + " ( "
        + USER_ID + " TEXT , " + USER_QUIZ_QUESTION_ID
        + " INTEGER, " + IS_ANSWER_CORRECT+ " INTEGER)";

    /********************************************************************************************************************/

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUESTION_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        onCreate(db);
    }

    public void addUser(User user) {

        ContentValues values = new ContentValues();
        values.put(USER_FIRST_NAME, user.getFname());
        values.put(USER_LAST_NAME, user.getLname());
        values.put(USER_EMAIL_ID, user.getEmailid());
        values.put(USER_PASSWORD, user.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }



    public void addQuestion(Question question) {

        ContentValues values = new ContentValues();
        values.put(QUIZ_QUESTION, question.getQuestion());
        values.put(KEY_ANSWER,question.getAnswer());
        values.put(KEY_OPTA, question.getOp1());
        values.put(KEY_OPTB, question.getOp2());
        values.put(KEY_OPTC, question.getOp3());
        values.put(KEY_OPTD, question.getOp4());
        SQLiteDatabase db = this.getWritableDatabase();
       db.insert(TABLE_QUESTION, null, values);
        db.close();
    }

    public void addUserQuiz(UserQuiz userQuiz) {

        ContentValues values = new ContentValues();
        values.put(USER_QUIZ_ID, userQuiz.get_userId());
        values.put(USER_QUIZ_QUESTION_ID, userQuiz.get_questionId());
        values.put(IS_ANSWER_CORRECT, userQuiz.getIsAnswerCorrect());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USER_QUIZ, null, values);
        db.close();
    }

    public ArrayList<UserQuiz> GetUserScore(String userId){
        try{
            String query = "Select _userId, _questionId, _isAnswerCorrect FROM " + CREATE_USER_QUIZ_TABLE + " WHERE " + USER_QUIZ_ID + " = " + userId;

            SQLiteDatabase db = this.getWritableDatabase();
            ArrayList<UserQuiz> userQuizList = new ArrayList<>();
            Cursor cursor = db.rawQuery(query, null);
            UserQuiz userQuiz = new UserQuiz();
            if (cursor.moveToFirst()) {
                for(int i=0;i<cursor.getCount();i++){
                    userQuiz.setIsCorrect(cursor.getInt(0));
                    userQuiz.set_questionId(cursor.getInt(1));
                    userQuiz.setIsCorrect(cursor.getInt(2));
                    userQuizList.add(userQuiz);
                }
                cursor.close();
                db.close();

                return userQuizList;
            } else {
                return  null;
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public boolean CheckIfValidUser(String emailId, String password){
        try{
            String query = "Select * FROM " + TABLE_USERS + " WHERE " + USER_EMAIL_ID + " =  \"" + emailId+ "\"";

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            User user = new User();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                user.setFname(cursor.getString(1));
                user.setLname(cursor.getString(2));
                user.setEmailid(cursor.getString(3));
                user.setPassword(cursor.getString(4));
                cursor.close();
                db.close();

                if(user.getPassword().trim().toString().equals(password.trim()))
                    return true;
                else
                    return  false;
            } else {
                return  false;
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public Question GetQuestion(int questionId){
        try{
            String query = "Select _id, _question, _opt1, _opt2, _opt3, _opt4, _answer FROM " + TABLE_QUESTION + " WHERE " + QUESTION_ID + " = " + questionId;

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            Question question = new Question();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                question.setID(cursor.getInt(0));
                question.setQuestion(cursor.getString(1));
                question.setOp1(cursor.getString(2));
                question.setOp2(cursor.getString(3));
                question.setOp3(cursor.getString(4));
                question.setOp4(cursor.getString(5));
                question.setAnswer(cursor.getInt(6));
                cursor.close();
                db.close();

                return question;
            } else {
                return  null;
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }


  /*  public User findUser(String emailid) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + USER_EMAIL_ID + " =  \"" + emailid+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setFname(cursor.getString(1));
            user.setLname(cursor.getString(2));
            user.setEmailid(cursor.getString(3));
            user.setPassword(cursor.getString(4));

            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public boolean deleteUser(String emailid) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_USERS + " WHERE " + USER_EMAIL_ID + " =  \"" + emailid + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USERS, USER_ID + " = ?",
                    new String[] { String.valueOf(user.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }*/
}