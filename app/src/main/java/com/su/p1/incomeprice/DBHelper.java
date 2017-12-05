package com.su.p1.incomeprice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.su.p1.incomeprice.model.Amount;
import com.su.p1.incomeprice.model.Money;

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

    private final String ACC_TABLE_NAME = "account";
    private final String COL_NAME = "name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_PICTURE + " INTEGER, "
                + COL_TITLE + " TEXT, " + COL_MEMO + " TEXT, " + COL_MONEY + " DOUBLE, " + COL_TYPE + " TEXT);");

        db.execSQL("CREATE TABLE " + ACC_TABLE_NAME + "(" + COL_NAME + " TEXT);");

        Log.d("Create multi table ", "Success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addMoney(Money money) {
        sqLiteDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(COL_DATE, money.getDate());
        values.put(COL_PICTURE, money.getPictureList());
        values.put(COL_TITLE, money.getTitle());
        values.put(COL_MEMO, money.getMemo());
        values.put(COL_MONEY, money.getMoney());
        values.put(COL_TYPE, money.getType());

        sqLiteDB.insert(TABLE_NAME, null, values);
        sqLiteDB.close();
        values.clear();
    }

    public Money getMoney(String id) {
        sqLiteDB = this.getWritableDatabase();

        Cursor c = sqLiteDB.query(TABLE_NAME, new String[] {COL_DATE, COL_TITLE, COL_MEMO, COL_MONEY}, ID + " = ?", new String[]{id}, null, null, null, null);

        if(c != null)
            c.moveToFirst();

        Money money = new Money();

        money.setDate(c.getString(0));
        money.setTitle(c.getString(1));
        money.setMemo(c.getString(2));
        money.setMoney(c.getDouble(3));

        return money;
    }

    public void deleteMoney(String id) {
        sqLiteDB = this.getWritableDatabase();

        sqLiteDB.delete(TABLE_NAME, ID + " = " + id, null);

        sqLiteDB.close();
    }

    public ArrayList<Money> getList(String date) {
        ArrayList<Money> money = new ArrayList<>();

        sqLiteDB = this.getWritableDatabase();
        Cursor mCursor = sqLiteDB.query(TABLE_NAME, null, COL_DATE + " = ?", new String[] {date}, null, null, null, null);

        if(mCursor != null)
            mCursor.moveToFirst();

        while(!mCursor.isAfterLast()) {
            money.add(new Money(mCursor.getInt(0), mCursor.getString(1), mCursor.getInt(2), mCursor.getString(3), mCursor.getString(4), mCursor.getDouble(5), mCursor.getString(6)));
            mCursor.moveToNext();
        }
        sqLiteDB.close();

        return money;
    }

    public Amount getInEx(String date) {
        Amount am = new Amount();

        double in = 0, ex = 0;

        sqLiteDB = this.getWritableDatabase();

        Cursor c;
        if(date.length() <= 6)
            c = sqLiteDB.query(TABLE_NAME, new String[] {COL_MONEY, COL_TYPE}, COL_DATE + " like '%" + date + "'", null, null, null, null, null);
        else
            c = sqLiteDB.query(TABLE_NAME, new String[] {COL_MONEY, COL_TYPE}, COL_DATE + " = ?", new String[] {date}, null, null, null, null);

        if(c != null)
            c.moveToFirst();

        while (!c.isAfterLast()) {
            if(c.getString(1).equals("in"))
                in += c.getDouble(0);
            else
                ex += c.getDouble(0);

            c.moveToNext();
        }
        sqLiteDB.close();

        am.setIncome(in);
        am.setExpenditure(ex);

        return am;
    }

    public void addAccount(String name) {
        sqLiteDB = this.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put(COL_NAME, name);

        sqLiteDB.insert(ACC_TABLE_NAME, null, v);
        sqLiteDB.close();
    }

    public String getAccount() {
        String n = "";
        sqLiteDB = this.getWritableDatabase();

        Cursor c = sqLiteDB.query(ACC_TABLE_NAME, null, null, null, null, null, null, null);
        c.moveToFirst();

        while(!c.isAfterLast()) {
            n = c.getString(0);
            c.moveToNext();
        }
        sqLiteDB.close();

        return n;
    }

}
