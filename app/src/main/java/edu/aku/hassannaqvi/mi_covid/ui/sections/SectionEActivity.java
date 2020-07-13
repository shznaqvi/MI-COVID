package edu.aku.hassannaqvi.mi_covid.ui.sections;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionEBinding;

import edu.aku.hassannaqvi.mi_covid.R;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionFBinding;

public class SectionEActivity extends AppCompatActivity {



    ActivitySectionEBinding bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_e);
    }

    private void setupSkips() {

        /*bi.ah1.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah1a.getId()) {
                Clear.clearAllFields(bi.fldGrpSecAH101);
            }
        }));*/

        /*bi.ah2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah2b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVah3);
            }
        }));*/

    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionFActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {

//        Util.openEndActivity(this);
    }

    private boolean UpdateDB() {
        /*DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long rowID = db.addChild(MainApp.adolscent);
        if (rowID > 0) {
            MainApp.adolscent.set_ID(String.valueOf(rowID));
            MainApp.adolscent.setUID(MainApp.adolscent.getDeviceId() + MainApp.adolscent.get_ID());
            db.updatesAdolsColumn(AdolscentContract.SingleAdolscent.COLUMN_UID, MainApp.adolscent.getUID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;
    }



    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();
        json.put("e01", bi.e0101.isChecked() ? "1"
                : bi.e0102.isChecked() ? "2"
                : bi.e0103.isChecked() ? "3"
                : bi.e0104.isChecked() ? "4"
                :  "-1");

        json.put("e02",  "-1");

        json.put("e0201",  "-1");

        json.put("e0202",  "-1");

        json.put("e03", bi.e0301.isChecked() ? "1"
                : bi.e0302.isChecked() ? "2"
                : bi.e0303.isChecked() ? "3"
                : bi.e0304.isChecked() ? "4"
                : bi.e0305.isChecked() ? "5"
                : bi.e0306.isChecked() ? "6"
                : bi.e03096.isChecked() ? "96"
                :  "-1");

        json.put("e04", bi.e0401.isChecked() ? "1"
                : bi.e0402.isChecked() ? "2"
                : bi.e0701.isChecked() ? "1"
                : bi.e0702.isChecked() ? "2"
                :  "-1");

        json.put("e05", bi.e05.getText().toString());

        json.put("e06", bi.e0601.isChecked() ? "1"
                : bi.e0602.isChecked() ? "2"
                : bi.e0603.isChecked() ? "3"
                : bi.e0604.isChecked() ? "4"
                :  "-1");

        json.put("e07",  "-1");

        json.put("e08", bi.e0801_t.isChecked() ? ""
                : bi.e0801.isChecked() ? "1"
                : bi.e0802.isChecked() ? "2"
                : bi.e0803.isChecked() ? "3"
                : bi.e0804.isChecked() ? "4"
                : bi.e0805.isChecked() ? "5"
                : bi.e0806.isChecked() ? "6"
                : bi.e08096.isChecked() ? "96"
                :  "-1");

        json.put("e09", bi.e0901.isChecked() ? "1"
                : bi.e0902.isChecked() ? "2"
                :  "-1");

        json.put("e10", bi.e1001.isChecked() ? "1"
                : bi.e1002.isChecked() ? "2"
                :  "-1");

        json.put("e11", bi.e1101.isChecked() ? "1"
                : bi.e1102.isChecked() ? "2"
                : bi.e1103.isChecked() ? "3"
                : bi.e1104.isChecked() ? "4"
                : bi.e1105.isChecked() ? "5"
                : bi.e1106.isChecked() ? "6"
                :  "-1");

        json.put("e12", bi.e1201.isChecked() ? "1"
                : bi.e1202.isChecked() ? "2"
                : bi.e1203.isChecked() ? "3"
                : bi.e1204.isChecked() ? "4"
                : bi.e1205.isChecked() ? "5"
                : bi.e12096.isChecked() ? "96"
                :  "-1");

        json.put("e13", bi.e1301.isChecked() ? "1"
                : bi.e1302.isChecked() ? "2"
                : bi.e1303.isChecked() ? "3"
                : bi.e13096.isChecked() ? "96"
                :  "-1");

        json.put("e1401", bi.e1401.getText().toString());

        json.put("e1402", bi.e1402.getText().toString());

        json.put("e15", bi.e15.getText().toString());



    }


    private boolean formValidation() {

        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}