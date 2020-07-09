package edu.aku.hassannaqvi.mi_covid.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.mi_covid";

    public static abstract class FormsTable implements BaseColumns {
        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88x = "istatus88x";
        public static final String COLUMN_FSTATUS = "fStatus";
        public static final String COLUMN_FSTATUS88x = "fstatus88x";
        public static final String COLUMN_LUID = "_luid";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDATE = "gpsdate";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_CLUSTERCODE = "cluster_code";
        public static final String COLUMN_HHNO = "hhno";
        public static final String COLUMN_SINFO = "sInfo";
        public static final String COLUMN_SE = "sE";
        public static final String COLUMN_SM = "sM";
        public static final String COLUMN_SN = "sN";
        public static final String COLUMN_SO = "sO";
        public static String PATH = "forms";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();
        public static String SERVER_URL = "sync.php";


        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
