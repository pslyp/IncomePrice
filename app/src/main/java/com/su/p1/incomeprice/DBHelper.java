package com.su.p1.incomeprice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.su.p1.incomeprice.model.List;

/**
 * Created by ~ { P_Slyp } ~ on 11/28/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Cursor mCursor;
    private SQLiteDatabase sqLiteDB;

    private static final String DATABASE_NAME = "incomelist";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "list";
    private static final String COL_DATE = "date";
    private static final String COL_PICTURE = "picture";
    private static final String COL_TITLE = "title";
    private static final String COL_MEMO = "memo";
    private static final String COL_MONEY = "money";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_DATE + " TEXT PRIMARY KEY, " + COL_PICTURE + " INTEGER, "
                + COL_TITLE + " TEXT, " + COL_MEMO + " TEXT, " + COL_MONEY + " INTEGER);");

        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_DATE + ", " + COL_PICTURE + ", " + COL_TITLE + ", "
                + COL_MEMO + ", " + COL_MONEY + ") VALUES ('28112017', " + R.drawable.pizzacompany + ", Food, firerice, 500);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addList(List list) {
        sqLiteDB = this.getWritableDatabase();

        sqLiteDB.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_DATE + ", " + COL_PICTURE + ", " + COL_TITLE + ", " + COL_MEMO + ", " + COL_MONEY + ")"
                + " VALUES (" + list.getDate() + ", " + list.getPictureList() + ", " + list.getTitle() + ", " + list.getMemo() + ", " + list.getMoney() + ");");

        sqLiteDB.close();
    }

}
