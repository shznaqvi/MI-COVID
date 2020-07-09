package edu.aku.hassannaqvi.mi_covid.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ChildContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.mi_covid";

    public static abstract class ChildTable implements BaseColumns {

        public static final String TABLE_NAME = "child";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_SCA = "sca";
        public static final String COLUMN_SCB = "scb";
        public static final String COLUMN_SCC = "scc";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_CHILDSERIAL = "ec13";
        public static final String COLUMN_CHILDNAME = "ec14";
        public static final String COLUMN_GENDER = "ec15";
        public static final String COLUMN_AGEY = "agey";
        public static final String COLUMN_AGEM = "agem";
        public static final String COLUMN_CLUSTERCODE = "cluster_code";
        public static final String COLUMN_HHNO = "hhno";
        public static final String COLUMN_CSTATUS = "cstatus";
        public static final String COLUMN_CSTATUS88x = "cstatus88x";
        public static String PATH = "child";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();

        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


}
