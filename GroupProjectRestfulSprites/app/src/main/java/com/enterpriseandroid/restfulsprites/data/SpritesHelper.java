package com.enterpriseandroid.restfulsprites.data;
/* File: SpritesHelper.java
 * Course Name: CST8277_300
 * Student Name: Zhe Huang, Shunbiao Lin, Yingmei Zhu, Fasheng Zhang
 * Professor: Stanley Pieda
 * Reference: revised based on the starter code provided by Stanley Pieda
 * Description: Android Database
 * Date: April 15, 2017
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Android Database
 *
 * @author Modified by Zhe Huang created on 4/15/2017.
 * @version 1.0.0
 * @see android.content.Context
 * @see android.database.sqlite.SQLiteDatabase
 * @see android.database.sqlite.SQLiteException
 * @see android.database.sqlite.SQLiteOpenHelper
 * @since 1.8.0_73
 */
public class SpritesHelper extends SQLiteOpenHelper {
    static final int VERSION = 2;

    static final String DB_FILE = "sprites.db";

    static final String TAB_SPRITES = "sprites";

    // pk
    public static final String COL_ID = "id";                   // long

    // sprite data
    public static final String COL_COLOR = "color"; // string
    public static final String COL_DX = "dx"; // string
    public static final String COL_DY = "dy"; // string
    public static final String COL_PANEL_HEIGHT = "panelheight"; // string
    public static final String COL_PANEL_WIDTH = "panelwidth"; // string
    public static final String COL_X = "x"; // string
    public static final String COL_Y = "y"; // string

    // meta-data
    static final String COL_REMOTE_ID = "remoteId";      // string
    static final String COL_DELETED = "deleted";         // boolean (null or MARK)
    static final String COL_DIRTY = "dirty";             // boolean (null or MARK)
    static final String COL_SYNC = "sync";               // string


    public SpritesHelper(Context context) {
        super(context, DB_FILE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + TAB_SPRITES + "("
                + COL_ID + " integer PRIMARY KEY AUTOINCREMENT,"
                + COL_COLOR + " text,"
                + COL_DX + " integer,"
                + COL_DY + " integer,"
                + COL_PANEL_HEIGHT + " integer,"
                + COL_PANEL_WIDTH + " integer,"
                + COL_X + " integer,"
                + COL_Y + " integer,"
                + COL_REMOTE_ID + " string UNIQUE,"
                + COL_DELETED + " integer,"
                + COL_DIRTY + " integer,"
                + COL_SYNC + " string UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try { db.execSQL("drop table " + TAB_SPRITES); }
        catch (SQLiteException e) { }
        onCreate(db);
    }
}
