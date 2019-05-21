package myhealth.ufscar.br.myhealth.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myhealth.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_REGISTER = "Register";
    public static final String TABLE_PATIENT = "Patient";


    private static final String QUERY_CREATE_TABLE_REGISTER = "CREATE TABLE " + TABLE_REGISTER +
            "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "dcnt_type INTEGER NOT NULL," +
            "datetime TEXT NOT NULL," +
            "observation TEXT," +
            "systolic INTEGER," +
            "diastolic INTEGER," +
            "heart_beats INTEGER," +
            "weight REAL," +
            "glycemic_rate REAL," +
            "bodyfat REAL);";

    private static final String QUERY_CREATE_TABLE_PATIENT = "CREATE TABLE " + TABLE_PATIENT +
            "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "email TEXT NOT NULL," +
            "password TEXT NOT NULL," +
            "susNumber TEXT NOT NULL,"+
            "name TEXT NOT NULL" +
            ")";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE_REGISTER);
        db.execSQL(QUERY_CREATE_TABLE_PATIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
