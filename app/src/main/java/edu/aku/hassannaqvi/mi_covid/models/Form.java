package edu.aku.hassannaqvi.mi_covid.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.mi_covid.contracts.FormsContract.FormsTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Form extends LiveData<Form> {

    private final String projectName = "mi_covid";
    private String _ID = "";
    private String _UID = "";
    private String a01 = ""; // Date
    private String a02 = ""; // Time
    private String a03 = ""; // Interviewer
    private String a04 = ""; // Province
    private String a05 = ""; // District
    private String refno = ""; // Reference Number
    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String sInfo = "";
    private String sB = "";
    private String sC = "";
    private String sD = "";
    private String sE = "";
    private String sF = "";
    private String sG = "";
    private String sH = "";
    private String sI = "";
    private String sJ = "";
    private String sK = "";
    private String sL = "";


    public Form() {
    }


    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }


    public String getA04() {
        return a04;
    }

    public void setA04(String a04) {
        this.a04 = a04;
    }


    public String getA05() {
        return a05;
    }

    public void setA05(String a05) {
        this.a05 = a05;
    }


    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }


    public String getsC() {
        return sC;
    }

    public void setsC(String sC) {
        this.sC = sC;
    }


    public String getsD() {
        return sD;
    }

    public void setsD(String sD) {
        this.sD = sD;
    }


    public String getsE() {
        return sE;
    }

    public void setsE(String sE) {
        this.sE = sE;
    }


    public String getsF() {
        return sF;
    }

    public void setsF(String sF) {
        this.sF = sF;
    }


    public String getsG() {
        return sG;
    }

    public void setsG(String sG) {
        this.sG = sG;
    }


    public String getsH() {
        return sH;
    }

    public void setsH(String sH) {
        this.sH = sH;
    }


    public String getsI() {
        return sI;
    }

    public void setsI(String sI) {
        this.sI = sI;
    }


    public String getsJ() {
        return sJ;
    }

    public void setsJ(String sJ) {
        this.sJ = sJ;
    }


    public String getsK() {
        return sK;
    }

    public void setsK(String sK) {
        this.sK = sK;
    }


    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }


    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public Form Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.a01 = jsonObject.getString(FormsTable.COLUMN_A01);
        this.a02 = jsonObject.getString(FormsTable.COLUMN_A02);
        this.a03 = jsonObject.getString(FormsTable.COLUMN_A03);
        this.a04 = jsonObject.getString(FormsTable.COLUMN_A04);
        this.a05 = jsonObject.getString(FormsTable.COLUMN_A05);
        this.refno = jsonObject.getString(FormsTable.COLUMN_REFNO);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsTable.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.sInfo = jsonObject.getString(FormsTable.COLUMN_SINFO);
        this.sB = jsonObject.getString(FormsTable.COLUMN_SB);
        this.sC = jsonObject.getString(FormsTable.COLUMN_SC);
        this.sD = jsonObject.getString(FormsTable.COLUMN_SD);
        this.sE = jsonObject.getString(FormsTable.COLUMN_SE);
        this.sF = jsonObject.getString(FormsTable.COLUMN_SF);
        this.sG = jsonObject.getString(FormsTable.COLUMN_SG);
        this.sH = jsonObject.getString(FormsTable.COLUMN_SH);
        this.sI = jsonObject.getString(FormsTable.COLUMN_SI);
        this.sJ = jsonObject.getString(FormsTable.COLUMN_SJ);
        this.sK = jsonObject.getString(FormsTable.COLUMN_SK);
        this.sL = jsonObject.getString(FormsTable.COLUMN_SL);

        return this;

    }

    public Form Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.a01 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A01));
        this.a02 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A02));
        this.a03 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A03));
        this.a04 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A04));
        this.a05 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A05));
        this.refno = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_REFNO));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
        this.sInfo = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SINFO));
        this.sB = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB));
        this.sC = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SC));
        this.sD = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SD));
        this.sE = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SE));
        this.sF = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SF));
        this.sG = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SG));
        this.sH = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SH));
        this.sI = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SI));
        this.sJ = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SJ));
        this.sK = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SK));
        this.sL = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SL));

        return this;
    }

    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Form.class);
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {
            json.put(FormsTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);

            json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
            json.put(FormsTable.COLUMN_A01, this.a01 == null ? JSONObject.NULL : this.a01);
            json.put(FormsTable.COLUMN_A02, this.a02 == null ? JSONObject.NULL : this.a02);
            json.put(FormsTable.COLUMN_A03, this.a03 == null ? JSONObject.NULL : this.a03);
            json.put(FormsTable.COLUMN_A04, this.a04 == null ? JSONObject.NULL : this.a04);
            json.put(FormsTable.COLUMN_A05, this.a05 == null ? JSONObject.NULL : this.a05);
            json.put(FormsTable.COLUMN_REFNO, this.refno == null ? JSONObject.NULL : this.refno);
            json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
            json.put(FormsTable.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
            json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);

            if (this.sInfo != null && !this.sInfo.equals("")) {
                json.put(FormsTable.COLUMN_SINFO, new JSONObject(this.sInfo));
            }

            if (this.sB != null && !this.sB.equals("")) {
                json.put(FormsTable.COLUMN_SB, new JSONObject(this.sB));
            }

            if (this.sC != null && !this.sC.equals("")) {
                json.put(FormsTable.COLUMN_SC, new JSONObject(this.sC));
            }

            if (this.sD != null && !this.sD.equals("")) {
                json.put(FormsTable.COLUMN_SD, new JSONObject(this.sD));
            }

            if (this.sE != null && !this.sE.equals("")) {
                json.put(FormsTable.COLUMN_SE, new JSONObject(this.sE));
            }

            if (this.sF != null && !this.sF.equals("")) {
                json.put(FormsTable.COLUMN_SF, new JSONObject(this.sF));
            }

            if (this.sG != null && !this.sG.equals("")) {
                json.put(FormsTable.COLUMN_SG, new JSONObject(this.sG));
            }

            if (this.sH != null && !this.sH.equals("")) {
                json.put(FormsTable.COLUMN_SH, new JSONObject(this.sH));
            }

            if (this.sI != null && !this.sI.equals("")) {
                json.put(FormsTable.COLUMN_SI, new JSONObject(this.sI));
            }

            if (this.sJ != null && !this.sJ.equals("")) {
                json.put(FormsTable.COLUMN_SJ, new JSONObject(this.sJ));
            }

            if (this.sK != null && !this.sK.equals("")) {
                json.put(FormsTable.COLUMN_SK, new JSONObject(this.sK));
            }

            if (this.sL != null && !this.sL.equals("")) {
                json.put(FormsTable.COLUMN_SL, new JSONObject(this.sL));
            }

            json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
            json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
            json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
            json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
            json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
            json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
            json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getA01() {
        return a01;
    }

    public void setA01(String a01) {
        this.a01 = a01;
    }

    public String getA02() {
        return a02;
    }

    public void setA02(String a02) {
        this.a02 = a02;
    }

    public String getA03() {
        return a03;
    }

    public void setA03(String a03) {
        this.a03 = a03;
    }


    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus96x() {
        return istatus96x;
    }

    public void setIstatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }



    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }


    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }


    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }


    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }


    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }


    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;

    }


    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

}
