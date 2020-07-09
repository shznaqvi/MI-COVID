package edu.aku.hassannaqvi.mi_covid.utils;

import edu.aku.hassannaqvi.mi_covid.contracts.BLRandomContract.BLRandomTable;
import edu.aku.hassannaqvi.mi_covid.contracts.ChildContract.ChildTable;
import edu.aku.hassannaqvi.mi_covid.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.mi_covid.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.mi_covid.contracts.VersionAppContract.VersionAppTable;

public final class CreateTable {

    public static final String DATABASE_NAME = "mi_covid.db";
    public static final String DB_NAME = "mi_covid_copy.db";
    public static final String PROJECT_NAME = "mi_covid";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_USER + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT,"
            + FormsTable.COLUMN_LUID + " TEXT,"
            + FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsTable.COLUMN_GPSDATE + " TEXT,"
            + FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsTable.COLUMN_FORMDATE + " TEXT,"
            + FormsTable.COLUMN_SYSDATE + " TEXT,"
            + FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsTable.COLUMN_CLUSTERCODE + " TEXT,"
            + FormsTable.COLUMN_HHNO + " TEXT,"
            + FormsTable.COLUMN_FORMTYPE + " TEXT,"
            + FormsTable.COLUMN_SINFO + " TEXT,"
            + FormsTable.COLUMN_SE + " TEXT,"
            + FormsTable.COLUMN_SM + " TEXT,"
            + FormsTable.COLUMN_SN + " TEXT,"
            + FormsTable.COLUMN_SO + " TEXT,"
            + FormsTable.COLUMN_FSTATUS + " TEXT,"
            + FormsTable.COLUMN_FSTATUS88x + " TEXT,"
            + FormsTable.COLUMN_ENDINGDATETIME + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_ISTATUS88x + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.DIST_ID + " TEXT"
            + " );";

    public static final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";

    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + BLRandomTable.TABLE_NAME + "("
            + BLRandomTable.COLUMN_ID + " TEXT,"
            + BLRandomTable.COLUMN_P_CODE + " TEXT,"
            + BLRandomTable.COLUMN_EB_CODE + " TEXT,"
            + BLRandomTable.COLUMN_LUID + " TEXT,"
            + BLRandomTable.COLUMN_HH + " TEXT,"
            + BLRandomTable.COLUMN_STRUCTURE_NO + " TEXT,"
            + BLRandomTable.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + BLRandomTable.COLUMN_HH_HEAD + " TEXT,"
            + BLRandomTable.COLUMN_CONTACT + " TEXT,"
            + BLRandomTable.COLUMN_HH_SELECTED_STRUCT + " TEXT,"
            + BLRandomTable.COLUMN_RANDOMDT + " TEXT,"
            + BLRandomTable.COLUMN_SNO_HH + " TEXT );";


    public static final String SQL_CREATE_CHILD_TABLE = "CREATE TABLE " + ChildTable.TABLE_NAME + "("
            + ChildTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ChildTable.COLUMN_DEVICEID + " TEXT,"
            + ChildTable.COLUMN_DEVICETAGID + " TEXT,"
            + ChildTable.COLUMN_USER + " TEXT,"
            + ChildTable.COLUMN_UID + " TEXT,"
            + ChildTable.COLUMN_UUID + " TEXT,"
            + ChildTable.COLUMN_FORMDATE + " TEXT,"
            + ChildTable.COLUMN_SYSDATE + " TEXT,"
            + ChildTable.COLUMN_SCA + " TEXT,"
            + ChildTable.COLUMN_SCB + " TEXT,"
            + ChildTable.COLUMN_SCC + " TEXT,"
            + ChildTable.COLUMN_SYNCED + " TEXT,"
            + ChildTable.COLUMN_SYNCED_DATE + " TEXT,"

            + ChildTable.COLUMN_CHILDNAME + " TEXT,"
            + ChildTable.COLUMN_CHILDSERIAL + " TEXT,"
            + ChildTable.COLUMN_GENDER + " TEXT,"
            + ChildTable.COLUMN_AGEY + " TEXT,"
            + ChildTable.COLUMN_AGEM + " TEXT,"
            + ChildTable.COLUMN_CLUSTERCODE + " TEXT,"
            + ChildTable.COLUMN_HHNO + " TEXT,"
            + ChildTable.COLUMN_CSTATUS + " TEXT,"
            + ChildTable.COLUMN_CSTATUS88x + " TEXT );";


    public static final String SQL_ALTER_FORMS = "ALTER TABLE " +
            FormsTable.TABLE_NAME + " ADD COLUMN " +
            FormsTable.COLUMN_SYSDATE + " TEXT";
    public static final String SQL_ALTER_CHILD_TABLE = "ALTER TABLE " +
            ChildTable.TABLE_NAME + " ADD COLUMN " +
            ChildTable.COLUMN_SYSDATE + " TEXT";
}
