package com.example.android.fragments;

/**
 * Created by ZHE on 3/28/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ArticleDbAdapter {

    public static final String KEY_ID = "_id";
    public static final String KEY_HEADLINE = "headline";
    public static final String KEY_ARTICLE = "article";

    private static final String TAG = "ArticleDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "News";
    private static final String SQLITE_TABLE = "Articles";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ID + " integer PRIMARY KEY autoincrement," +
                    KEY_HEADLINE + "," +
                    KEY_ARTICLE + "," +
                    " UNIQUE (" + KEY_HEADLINE + "));";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public ArticleDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    //should run background
    public ArticleDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createArticle(String headline, String article) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_HEADLINE, headline);
        initialValues.put(KEY_ARTICLE, article);

        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllArticle() {
        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null, null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public Cursor fetchArticleById(int inputID) throws SQLException {
        Log.w(TAG, "inputID");
        Cursor mCursor = null;
        if (inputID == 0) {
            mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_ID,
                            KEY_HEADLINE, KEY_ARTICLE},
                    null, null, null, null, null);
        } else {
            mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_ARTICLE},
                    "_id=?", new String[]{"inputID"},
                    null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchAllHeadline() {
        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_ID, KEY_HEADLINE},
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeArticle() {
        createArticle("Article One", "Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.");
        createArticle("Article Two", "Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future.");
    }
}
