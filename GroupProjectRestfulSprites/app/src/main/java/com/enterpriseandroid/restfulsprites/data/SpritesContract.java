package com.enterpriseandroid.restfulsprites.data;
/* File: SpritesContract.java
 * Course Name: CST8277_300
 * Student Name: Zhe Huang, Shunbiao Lin, Yingmei Zhu, Fasheng Zhang
 * Professor: Stanley Pieda
 * Reference: revised based on the starter code provided by Stanley Pieda
 * Description: Contract of sprites
 * Date: April 15, 2017
 */
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract of sprites
 *
 * @author Modified by Zhe Huang created on 4/15/2017.
 * @version 1.0.0
 * @see android.content.ContentResolver
 * @see android.net.Uri
 * @see android.provider.BaseColumns
 * @since 1.8.0_73
 */
public final class SpritesContract {
    private SpritesContract() {}

    public static final String TABLE = "sprites";
    public static final String AUTHORITY = "com.enterpriseandroid.restfulsprites.SPRITES";

    public static final Uri URI
        = new Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(AUTHORITY)
            .appendPath(TABLE)
            .build();

    /** sprites DIR type */
    public static final String CONTENT_TYPE_DIR
        = ContentResolver.CURSOR_DIR_BASE_TYPE
            + " + /vnd.com.enterpriseandroid.restfulsprites.sprites";

    /** sprites ITEM type */
    public static final String CONTENT_TYPE_ITEM
        = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.com.enterpriseandroid.restfulsprites.sprites";

    public static final int STATUS_OK = 0;
    public static final int STATUS_SYNC = 1;
    public static final int STATUS_DIRTY = 2;

    public static final class Columns implements BaseColumns {
        private Columns() {}

        public static final String ID = BaseColumns._ID;  // long
        public static final String COLOR = "color"; // string
        public static final String DX = "dx"; // string
        public static final String DY = "dy"; // string
        public static final String PANEL_HEIGHT = "panelheight"; // string
        public static final String PANEL_WIDTH = "panelwidth"; // string
        public static final String X = "x"; // string
        public static final String Y = "y"; // string
        public static final String STATUS = "status";     // int

        // !!!
        // These columns really should not be exposed.
        public static final String SYNC = "sync";         // string!
        public static final String DIRTY = "dirty";       // int!
        public static final String REMOTE_ID = "rem";     // int!
    }
}
