package com.su.p1.incomeprice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.su.p1.incomeprice.model.List;

import java.util.ArrayList;

/**
 * Created by ~ { P_Slyp } ~ on 11/28/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDB;

    private static final String DATABASE_NAME = "incomelist";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "list";
    public static final String ID = BaseColumns._ID;
    public static final String COL_DATE = "date";
    public static final String COL_PICTURE = "picture";
    public static final String COL_TITLE = "title";
    public static final String COL_MEMO = "memo";
    public static final String COL_MONEY = "money";
    public static final String COL_TYPE = "type";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_PICTURE + " INTEGER, "
                + COL_TITLE + " TEXT, " + COL_MEMO + " TEXT, " + COL_MONEY + " DOUBLE, " + COL_TYPE + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addList(List list) {
        sqLiteDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(COL_DATE, list.getDate());
        values.put(COL_PICTURE, list.getPictureList());
        values.put(COL_TITLE, list.getTitle());
        values.put(COL_MEMO, list.getMemo());
        values.put(COL_MONEY, list.getMoney());
        values.put(COL_TYPE, list.getType());

        sqLiteDB.insert(TABLE_NAME, null, values);
        sqLiteDB.close();
    }

    public ArrayList<List> getList(String date) {
        ArrayList<List> list = new ArrayList<>();

        sqLiteDB = this.getWritableDatabase();
        Cursor mCursor = sqLiteDB.query(TABLE_NAME, null, COL_DATE + " = ?", new String[] {date}, null, null, null, null);

        if(mCursor != null)
            mCursor.moveToFirst();

        while(!mCursor.isAfterLast()) {
            list.add(new List(mCursor.getString(1), mCursor.getInt(2), mCursor.getString(3), mCursor.getString(4), mCursor.getDouble(5), mCursor.getString(6)));
            mCursor.moveToNext();
        }

        sqLiteDB.close();

        return list;
    }

}
