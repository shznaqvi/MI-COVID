package edu.aku.hassannaqvi.mi_covid.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.mi_covid.contracts.BLRandomContract.BLRandomTable;
import edu.aku.hassannaqvi.mi_covid.contracts.ChildContract.ChildTable;
import edu.aku.hassannaqvi.mi_covid.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.mi_covid.contracts.UCsContract;
import edu.aku.hassannaqvi.mi_covid.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.mi_covid.contracts.VersionAppContract;
import edu.aku.hassannaqvi.mi_covid.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.mi_covid.models.BLRandom;
import edu.aku.hassannaqvi.mi_covid.models.Child;
import edu.aku.hassannaqvi.mi_covid.models.Form;
import edu.aku.hassannaqvi.mi_covid.models.Users;
import edu.aku.hassannaqvi.mi_covid.models.VersionApp;

import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.SQL_ALTER_CHILD_TABLE;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.SQL_ALTER_FORMS;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.SQL_CREATE_BL_RANDOM;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.SQL_CREATE_CHILD_TABLE;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.SQL_CREATE_FORMS;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.mi_covid.utils.CreateTable.SQL_CREATE_VERSIONAPP;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String SQL_DELETE_UCS = "DROP TABLE IF EXISTS " + UCsContract.UCsTable.TABLE_NAME;

    private final String TAG = "DatabaseHelper";
    private String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_PSU_TABLE);
        db.execSQL(SQL_CREATE_BL_RANDOM);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_FAMILY_MEMBERS);
        db.execSQL(SQL_CREATE_CHILD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(SQL_ALTER_FORMS);
                db.execSQL(SQL_ALTER_CHILD_TABLE);
            default:
                break;

        }
    }

    public int syncBLRandom(JSONArray blList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BLRandomTable.TABLE_NAME, null, null);

        JSONArray jsonArray = blList;
        int insertCount = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObjectCC = null;
            try {
                jsonObjectCC = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            BLRandom Vc = new BLRandom();
            Vc.Sync(jsonObjectCC);
            Log.d(TAG, "syncBLRandom: " + Vc.get_ID());
            ContentValues values = new ContentValues();

            values.put(BLRandomTable.COLUMN_ID, Vc.get_ID());
            values.put(BLRandomTable.COLUMN_LUID, Vc.getLUID());
            values.put(BLRandomTable.COLUMN_STRUCTURE_NO, Vc.getStructure());
            values.put(BLRandomTable.COLUMN_FAMILY_EXT_CODE, Vc.getExtension());
            values.put(BLRandomTable.COLUMN_HH, Vc.getHh());
            values.put(BLRandomTable.COLUMN_EB_CODE, Vc.getEbcode());
            values.put(BLRandomTable.COLUMN_P_CODE, Vc.getpCode());
            values.put(BLRandomTable.COLUMN_RANDOMDT, Vc.getRandomDT());
            values.put(BLRandomTable.COLUMN_HH_HEAD, Vc.getHhhead());
            values.put(BLRandomTable.COLUMN_CONTACT, Vc.getContact());
            values.put(BLRandomTable.COLUMN_HH_SELECTED_STRUCT, Vc.getSelStructure());
            values.put(BLRandomTable.COLUMN_SNO_HH, Vc.getSno());

            long row = db.insert(BLRandomTable.TABLE_NAME, null, values);
            if (row != -1) insertCount++;
        }
        return insertCount;
    }

    public void syncVersionApp(JSONArray VersionList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppContract.VersionAppTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = VersionList;
            JSONObject jsonObjectCC = jsonArray.getJSONObject(0);

            VersionApp Vc = new VersionApp();
            Vc.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            db.insert(VersionAppContract.VersionAppTable.TABLE_NAME, null, values);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public VersionApp getVersionApp() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VersionAppTable._ID,
                VersionAppTable.COLUMN_VERSION_CODE,
                VersionAppTable.COLUMN_VERSION_NAME,
                VersionAppTable.COLUMN_PATH_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionApp allVC = new VersionApp();
        try {
            c = db.query(
                    VersionAppTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allVC.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVC;
    }

    public int syncUser(JSONArray userList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            JSONArray jsonArray = userList;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                Users user = new Users();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
                values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
                values.put(UsersTable.DIST_ID, user.getDistId());
                long rowID = db.insert(UsersTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE " + UsersTable.COLUMN_USERNAME + "=? AND " + UsersTable.COLUMN_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {

                if (mCursor.moveToFirst()) {
//                    MainApp.DIST_ID = mCursor.getString(mCursor.getColumnIndex(Users.UsersTable.ROW_USERNAME));
                }
                return true;
            }
        }
        return false;
    }

    public Long addForm(Form form) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, form.getProjectName());
        values.put(FormsTable.COLUMN_UID, form.get_UID());
        values.put(FormsTable.COLUMN_FORMDATE, form.getFormDate());
        values.put(FormsTable.COLUMN_SYSDATE, form.getSysDate());
        values.put(FormsTable.COLUMN_LUID, form.getLuid());
        values.put(FormsTable.COLUMN_USER, form.getUser());
        values.put(FormsTable.COLUMN_ISTATUS, form.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, form.getIstatus88x());
        values.put(FormsTable.COLUMN_FSTATUS, form.getfStatus());
        values.put(FormsTable.COLUMN_FSTATUS88x, form.getFstatus88x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, form.getEndingdatetime());
        values.put(FormsTable.COLUMN_SINFO, form.getsInfo());
        values.put(FormsTable.COLUMN_SE, form.getsE());
        values.put(FormsTable.COLUMN_SM, form.getsM());
        values.put(FormsTable.COLUMN_SN, form.getsN());
        values.put(FormsTable.COLUMN_SO, form.getsO());
        values.put(FormsTable.COLUMN_GPSLAT, form.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, form.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDATE, form.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, form.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICETAGID, form.getDevicetagID());
        values.put(FormsTable.COLUMN_DEVICEID, form.getDeviceID());
        values.put(FormsTable.COLUMN_APPVERSION, form.getAppversion());
        values.put(FormsTable.COLUMN_CLUSTERCODE, form.getClusterCode());
        values.put(FormsTable.COLUMN_HHNO, form.getHhno());
        values.put(FormsTable.COLUMN_FORMTYPE, form.getFormType());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addChild(Child child) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
//        values.put(MWRATable._ID, mwra.get_ID());
        values.put(ChildTable.COLUMN_UUID, child.get_UUID());
        values.put(ChildTable.COLUMN_DEVICEID, child.getDeviceId());
        values.put(ChildTable.COLUMN_FORMDATE, child.getFormDate());
        values.put(ChildTable.COLUMN_SYSDATE, child.getSysDate());
        values.put(ChildTable.COLUMN_USER, child.getUser());
        values.put(ChildTable.COLUMN_SCA, child.getsCA());
        values.put(ChildTable.COLUMN_SCB, child.getsCB());
        values.put(ChildTable.COLUMN_SCC, child.getsCC());
        values.put(ChildTable.COLUMN_DEVICETAGID, child.getDevicetagID());

        values.put(ChildTable.COLUMN_CHILDNAME, child.getChildName());
        values.put(ChildTable.COLUMN_CHILDSERIAL, child.getChildSerial());
        values.put(ChildTable.COLUMN_GENDER, child.getgender());
        values.put(ChildTable.COLUMN_AGEY, child.getagey());
        values.put(ChildTable.COLUMN_AGEM, child.getagem());
        values.put(ChildTable.COLUMN_CLUSTERCODE, child.getcluster());
        values.put(ChildTable.COLUMN_HHNO, child.gethhno());
        values.put(ChildTable.COLUMN_CSTATUS, child.getCstatus());
        values.put(ChildTable.COLUMN_CSTATUS88x, child.getCstatus88x());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                ChildTable.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public Form isDataExists(String studyId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;

// New value for one column
        String[] columns = {
                FormsTable.COLUMN_LUID,
                FormsTable.COLUMN_ISTATUS,

        };

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_LUID + " = ? AND "
                + FormsTable.COLUMN_ISTATUS + " = ?";
        String[] selectionArgs = new String[]{studyId, "1"};

        Form allForms = new Form();
        try {
            c = db.query(FormsTable.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                   // The sort order

            while (c.moveToNext()) {
                allForms.setLuid(c.getString(c.getColumnIndex(FormsTable.COLUMN_LUID)));
                allForms.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;


    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.form.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<Form> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SINFO,
                FormsTable.COLUMN_FSTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_FORMTYPE,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<Form>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                allForms.add(form.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> checkFormExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SINFO,
                FormsTable.COLUMN_FSTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_FORMTYPE,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<Form>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                allForms.add(form.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_FSTATUS,
                FormsTable.COLUMN_FSTATUS88x,
                FormsTable.COLUMN_LUID,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_SINFO,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SM,
                FormsTable.COLUMN_SN,
                FormsTable.COLUMN_SO,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_FORMTYPE
        };


        String whereClause = FormsTable.COLUMN_SYNCED + " is null AND " + FormsTable.COLUMN_ISTATUS + " != '' ";
        //String whereClause = FormsTable.COLUMN_ISTATUS +" != '' ";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<Form>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                allForms.add(form.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Child> getUnsyncedChildForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable._ID,
                ChildTable.COLUMN_UID,
                ChildTable.COLUMN_UUID,
                ChildTable.COLUMN_DEVICEID,
                ChildTable.COLUMN_FORMDATE,
                ChildTable.COLUMN_SYSDATE,
                ChildTable.COLUMN_USER,
                ChildTable.COLUMN_SCA,
                ChildTable.COLUMN_SCB,
                ChildTable.COLUMN_SCC,
                ChildTable.COLUMN_DEVICETAGID,
                ChildTable.COLUMN_CHILDNAME,
                ChildTable.COLUMN_CHILDSERIAL,
                ChildTable.COLUMN_GENDER,
                ChildTable.COLUMN_AGEY,
                ChildTable.COLUMN_AGEM,
                ChildTable.COLUMN_CLUSTERCODE,
                ChildTable.COLUMN_HHNO,
                ChildTable.COLUMN_CSTATUS,
                ChildTable.COLUMN_CSTATUS88x,

        };


        String whereClause = ChildTable.COLUMN_SYNCED + " is null";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy =
                ChildTable._ID + " ASC";

        Collection<Child> allChildren = new ArrayList<Child>();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Child child = new Child();
                allChildren.add(child.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allChildren;
    }

    public Collection<Form> getTodayForms(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FSTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                form.setSysDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setClusterCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_CLUSTERCODE)));
                form.setHhno(c.getString(c.getColumnIndex(FormsTable.COLUMN_HHNO)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setfStatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_FSTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> getFormsByCluster(String cluster) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FSTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_CLUSTERCODE + " = ? ";
        String[] whereArgs = new String[]{cluster};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                form.setSysDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setClusterCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_CLUSTERCODE)));
                form.setHhno(c.getString(c.getColumnIndex(FormsTable.COLUMN_HHNO)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setfStatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_FSTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public ArrayList<Form> getUnclosedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FSTATUS,
                FormsTable.COLUMN_SYNCED,
        };
        String whereClause = FormsTable.COLUMN_ISTATUS + " = ''";
        String[] whereArgs = null;
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";
        ArrayList<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                form.setSysDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setClusterCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_CLUSTERCODE)));
                form.setHhno(c.getString(c.getColumnIndex(FormsTable.COLUMN_HHNO)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setfStatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_FSTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public int getChildrenByUUID(String UUID) {
        String countQuery = "SELECT  * FROM " + ChildTable.TABLE_NAME + " WHERE " + ChildTable.COLUMN_UUID + " = '" + UUID + "' AND " + ChildTable.COLUMN_CSTATUS + " = '1'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getChildrenPhotoCheck(String UID) {
        String countQuery = "SELECT  * FROM " + ChildTable.TABLE_NAME +
                " WHERE " + ChildTable.COLUMN_UUID + " = '" + UID +
                "' AND " + ChildTable.COLUMN_CSTATUS + " = '1' " +
                " AND (" + ChildTable.COLUMN_SCC + " NOT LIKE '%\"frontFileName\":\"\"%' " +
                " OR " + ChildTable.COLUMN_SCC + " NOT LIKE '%\"backFileName\":\"\"%') ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getChildrenCardCheck(String UID) {
        String countQuery = "SELECT  * FROM " + ChildTable.TABLE_NAME +
                " WHERE " + ChildTable.COLUMN_UUID + " = '" + UID +
                "' AND " + ChildTable.COLUMN_CSTATUS + " = '1' " +
                " AND " + ChildTable.COLUMN_SCC + " LIKE '%\"im01\":\"1\"%' ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public int updateEnding(boolean flag) {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        if (flag) {
            values.put(FormsTable.COLUMN_ISTATUS, MainApp.form.getIstatus());
            values.put(FormsTable.COLUMN_ISTATUS88x, MainApp.form.getIstatus88x());
            values.put(FormsTable.COLUMN_SINFO, MainApp.form.getsInfo());
        } else {
            values.put(FormsTable.COLUMN_FSTATUS, MainApp.form.getfStatus());
            values.put(FormsTable.COLUMN_FSTATUS88x, MainApp.form.getFstatus88x());
            values.put(FormsTable.COLUMN_ENDINGDATETIME, MainApp.form.getEndingdatetime());
        }

        // Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateChildEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_CSTATUS, MainApp.child.getCstatus());
        values.put(ChildTable.COLUMN_CSTATUS88x, MainApp.child.getCstatus88x());

        // Which row to update, based on the ID
        String selection = ChildTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.child.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    //Get BLRandom data
    public BLRandom getHHFromBLRandom(String subAreaCode, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;

        String[] columns = {
                BLRandomTable.COLUMN_ID,
                BLRandomTable.COLUMN_LUID,
                BLRandomTable.COLUMN_STRUCTURE_NO,
                BLRandomTable.COLUMN_FAMILY_EXT_CODE,
                BLRandomTable.COLUMN_HH,
                BLRandomTable.COLUMN_P_CODE,
                BLRandomTable.COLUMN_EB_CODE,
                BLRandomTable.COLUMN_RANDOMDT,
                BLRandomTable.COLUMN_HH_SELECTED_STRUCT,
                BLRandomTable.COLUMN_CONTACT,
                BLRandomTable.COLUMN_HH_HEAD,
                BLRandomTable.COLUMN_SNO_HH
        };

        String whereClause = BLRandomTable.COLUMN_P_CODE + "=? AND " + BLRandomTable.COLUMN_HH + "=?";
        String[] whereArgs = new String[]{subAreaCode, hh};
        String groupBy = null;
        String having = null;

        String orderBy =
                BLRandomTable.COLUMN_ID + " ASC";

        BLRandom allBL = null;
        try {
            c = db.query(
                    BLRandomTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allBL = new BLRandom().hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }


    //Get All Child of HH
    public List<Child> getFilledChildForms(String clusterCode, String hhNo, String uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable._ID,
                ChildTable.COLUMN_UID,
                ChildTable.COLUMN_UUID,
                ChildTable.COLUMN_DEVICEID,
                ChildTable.COLUMN_FORMDATE,
                ChildTable.COLUMN_SYSDATE,
                ChildTable.COLUMN_USER,
                ChildTable.COLUMN_SCA,
                ChildTable.COLUMN_SCB,
                ChildTable.COLUMN_SCC,
                ChildTable.COLUMN_DEVICETAGID,
                ChildTable.COLUMN_CHILDNAME,
                ChildTable.COLUMN_CHILDSERIAL,
                ChildTable.COLUMN_GENDER,
                ChildTable.COLUMN_AGEY,
                ChildTable.COLUMN_AGEM,
                ChildTable.COLUMN_CLUSTERCODE,
                ChildTable.COLUMN_HHNO,
                ChildTable.COLUMN_CSTATUS,
                ChildTable.COLUMN_CSTATUS88x,
        };

        String whereClause = ChildTable.COLUMN_CLUSTERCODE + "=? AND " + ChildTable.COLUMN_HHNO + "=? AND " + ChildTable.COLUMN_UUID + "=? AND (" + ChildTable.COLUMN_CSTATUS + " is not null OR " + ChildTable.COLUMN_CSTATUS + " !='')";
        String[] whereArgs = {clusterCode, hhNo, uuid};
        String groupBy = null;
        String having = null;
        String orderBy = ChildTable.COLUMN_CHILDSERIAL + " ASC";
        List<Child> allChildren = new ArrayList<>();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allChildren.add(new Child().hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allChildren;
    }

    //Get Form already exist
    public Form getFilledForm(String clusterCode, String hhNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_FSTATUS,
                FormsTable.COLUMN_FSTATUS88x,
                FormsTable.COLUMN_LUID,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_SINFO,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SM,
                FormsTable.COLUMN_SN,
                FormsTable.COLUMN_SO,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_FORMTYPE
        };

//        String whereClause = "(" + FormsTable.COLUMN_ISTATUS + " is null OR " + FormsTable.COLUMN_ISTATUS + "='') AND " + FormsTable.COLUMN_CLUSTERCODE + "=? AND " + FormsTable.COLUMN_HHNO + "=?";
        String whereClause = FormsTable.COLUMN_CLUSTERCODE + "=? AND " + FormsTable.COLUMN_HHNO + "=?";
        String[] whereArgs = {clusterCode, hhNo};
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable._ID + " ASC";
        Form allForms = null;
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allForms = new Form().Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    //Generic update FormColumn
    public int updatesFormColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    //Generic update ChildColumn
    public int updatesChildColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = ChildTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.child.get_ID())};

        return db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    //Generic Un-Synced Children
    public void updateSyncedChildForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SYNCED, true);
        values.put(ChildTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = ChildTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                ChildTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    //Generic Un-Synced Forms
    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }
}