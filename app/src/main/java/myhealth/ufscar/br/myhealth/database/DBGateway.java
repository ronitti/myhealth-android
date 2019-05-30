package myhealth.ufscar.br.myhealth.database;


import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;

public class DBGateway {

    private static DBGateway instance;
    private SQLiteDatabase db;


    private DBGateway(Context context) {
        SQLiteDatabase.loadLibs(context);
        DbHelper helper = new DbHelper(context);
        db = helper.getWritableDatabase("myhealthkey");
    }

    public static DBGateway getInstance(Context context) {
        if (instance == null) {
            instance = new DBGateway(context);
        }
        return instance;
    }

    public SQLiteDatabase getDb() {
        return this.db;
    }


}
