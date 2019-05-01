package myhealth.ufscar.br.myhealth.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myhealth.db";
    private static final int DATABASE_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_table = "CREATE TABLE Register" +
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

        db.execSQL(query_create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
