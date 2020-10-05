package com.example.seminarski_troskovnik.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "trosakBaza2";
    private static final int DATABASE_VERSION = 2;
    private static final String TROSAK_TABLE = "trosak";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "ime";
    private static final String PRICE_KEY = "cijena";
    private static final String COLOR_KEY = "boja";
    private static final String TIME_KEY = "vrijeme";

    public DatabaseHandler(Context context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabaseSQL = "create table " + TROSAK_TABLE + "( " +
                ID_KEY + " integer primary key autoincrement" + ", " +
                NAME_KEY + " text" + ", " +
                PRICE_KEY + " real, "  +
                COLOR_KEY + " text, " +
                TIME_KEY + " text" +  "  ) ";

        db.execSQL(createDatabaseSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TROSAK_TABLE);
        onCreate(db);
    }

    public void dodajTrosak(Trosak trosakObject) {

        SQLiteDatabase database = getWritableDatabase();
        String dodajTrosakSQLCommand = "insert into " + TROSAK_TABLE + " values(" + "null ,'"  + trosakObject.getTrosakIme() +
                "', '" + trosakObject.getCijena() + "', '" + trosakObject.getTrosakBoja() + "' ,'" + trosakObject.getTrosakVrijeme() + "' )";
        database.execSQL(dodajTrosakSQLCommand);
        database.close();
    }

    public void izbrisiTrosakIzBazePomocuId(int Id) {
        SQLiteDatabase database = getWritableDatabase();
        String izbrisiTrosakSQLcommand = "delete from " + TROSAK_TABLE + " where " + ID_KEY + " = " + Id;
        database.execSQL(izbrisiTrosakSQLcommand);
        database.close();


    }

    public void promijeniTrosakObject(int trosakID, String trosakIme, double trosakCijena, String trosakBoja) {

        SQLiteDatabase database = getWritableDatabase();
        String promijeniTrosakSQLCommand = "update " + TROSAK_TABLE +
                " set " + NAME_KEY + " = '" + trosakIme +
                "', " + PRICE_KEY + " = '" + trosakCijena +
                "', " + COLOR_KEY + " = '" + trosakBoja
                + "' where " + ID_KEY + " = " + trosakID;
        database.execSQL(promijeniTrosakSQLCommand);
        database.close();


    }

    public ArrayList<Trosak> vratiTrosakObject() {

        SQLiteDatabase database = getWritableDatabase();
        String sqlQuerryCommand = "select * from " + TROSAK_TABLE  + " order by " +ID_KEY + " DESC ";
        Cursor cursor = database.rawQuery(sqlQuerryCommand, null);

        ArrayList<Trosak> trosak = new ArrayList<>();
        while (cursor.moveToNext()) {

            Trosak trenutniTrosak = new Trosak(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), Double.parseDouble(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
            trosak.add(trenutniTrosak);
        }
        database.close();
        return trosak;
    }

    public ArrayList<Trosak> vratiTrosakObjectByMjesec() {

        SQLiteDatabase database = getWritableDatabase();
        String sqlQuerryCommand = "select * from " + TROSAK_TABLE  +" WHERE "+ TIME_KEY +" >= date('now','localtime', '-30 day')";

        Cursor cursor = database.rawQuery(sqlQuerryCommand, null);

        ArrayList<Trosak> trosak = new ArrayList<>();
        while (cursor.moveToNext()) {

            Trosak trenutniTrosak = new Trosak(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), Double.parseDouble(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
            trosak.add(trenutniTrosak);
        }
        database.close();
        return trosak;
    }


}
