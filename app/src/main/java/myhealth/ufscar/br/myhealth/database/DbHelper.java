package myhealth.ufscar.br.myhealth.database;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myhealth.db";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_REGISTER = "Register";
    public static final String TABLE_PATIENT = "Patient";
    public static final String TABLE_DCNT_PATIENT = "DCNT_Patient";
    public static final String TABLE_FREQUENCY = "frequency";


    private static final String QUERY_CREATE_TABLE_REGISTER = "CREATE TABLE " + TABLE_REGISTER +
            "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_patient INTEGER NOT NULL," +
            "dcnt_type INTEGER NOT NULL," +
            "sync INTEGER NOT NULL," +
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

    private static final String QUERY_CREATE_TABLE_DCNT_PATIENT = "CREATE TABLE " + TABLE_DCNT_PATIENT + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_patient INTEGER NOT NULL," +
            "type_dcnt INTEGER NOT NULL" +
            ")";

    private static final String QUERY_CREATE_TABLE_FREQUENCY = "CREATE TABLE " + TABLE_FREQUENCY + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_dcnt INTEGER NOT NULL," +
            "frequency_type INTEGER NOT NULL," +
            "days_week TEXT NOT NULL," +
            "custon_every INTEGER NOT NULL," +
            "times_day INTEGER NOT NULL," +
            "start_date TEXT NOT NULL," +
            "hours_of_date TEXT NOT NULL" +
            ")";




    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE_REGISTER);
        db.execSQL(QUERY_CREATE_TABLE_PATIENT);
        db.execSQL(QUERY_CREATE_TABLE_DCNT_PATIENT);
        db.execSQL(QUERY_CREATE_TABLE_FREQUENCY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
