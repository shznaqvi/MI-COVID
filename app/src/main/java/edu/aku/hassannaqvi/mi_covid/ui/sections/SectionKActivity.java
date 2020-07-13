package edu.aku.hassannaqvi.mi_covid.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.mi_covid.R;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionKBinding;

public class SectionKActivity extends AppCompatActivity {

    ActivitySectionKBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_k);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        /*bi.i02.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.i0202.getId()) {
                Clear.clearAllFields(bi.fldGrpSecI01);
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
                startActivity(new Intent(this, SectionLActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {

        //    Util.openEndActivity(this);
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

        json.put("k01", bi.k0101.isChecked() ? "1"
                : bi.k0102.isChecked() ? "2"
                : bi.k0103.isChecked() ? "3"
                : bi.k0104.isChecked() ? "4"
                : "-1");

        json.put("k02", bi.k0201.isChecked() ? "1"
                : bi.k0202.isChecked() ? "2"
                : "-1");

        json.put("k03", bi.k0301.isChecked() ? "1"
                : bi.k0302.isChecked() ? "2"
                : bi.k0303.isChecked() ? "3"
                : bi.k0304.isChecked() ? "4"
                : "-1");

        json.put("k04", bi.k0401.isChecked() ? "1"
                : bi.k0402.isChecked() ? "2"
                : "-1");

        json.put("k05", bi.k0501.isChecked() ? "1"
                : bi.k0502.isChecked() ? "2"
                : bi.k0503.isChecked() ? "3"
                : bi.k0504.isChecked() ? "4"
                : bi.k0505.isChecked() ? "5"
                : bi.k0506.isChecked() ? "6"
                : bi.k05096.isChecked() ? "96"
                : "-1");

        json.put("k06", bi.k0601.isChecked() ? "1"
                : bi.k0602.isChecked() ? "2"
                : "-1");

        json.put("k07", bi.k0701.isChecked() ? "1"
                : bi.k0702.isChecked() ? "2"
                : bi.k0703.isChecked() ? "3"
                : bi.k0704.isChecked() ? "4"
                : "-1");

        json.put("k08", bi.k0801.isChecked() ? "1"
                : bi.k0802.isChecked() ? "2"
                : "-1");

        json.put("k09", bi.k0901.isChecked() ? "1"
                : bi.k0902.isChecked() ? "2"
                : "-1");

        json.put("k10", bi.k1001.isChecked() ? "1"
                : bi.k1002.isChecked() ? "2"
                : "-1");

        json.put("k11", bi.k1101.isChecked() ? "1"
                : bi.k1102.isChecked() ? "2"
                : "-1");

        json.put("k12", bi.k1201.isChecked() ? "1"
                : bi.k1202.isChecked() ? "2"
                : "-1");

        json.put("k13", bi.k1301.isChecked() ? "1"
                : bi.k1302.isChecked() ? "2"
                : "-1");

        json.put("k14", bi.k1401.isChecked() ? "1"
                : bi.k1402.isChecked() ? "2"
                : bi.k1403.isChecked() ? "3"
                : bi.k14096.isChecked() ? "96"
                : "-1");

        json.put("k15", bi.k1501.isChecked() ? "1"
                : bi.k1502.isChecked() ? "2"
                : "-1");

        json.put("k16", bi.k1601.isChecked() ? "1"
                : bi.k1602.isChecked() ? "2"
                : bi.k16096.isChecked() ? "96"
                : "-1");

        json.put("k17", bi.k1701.isChecked() ? "1"
                : bi.k1702.isChecked() ? "2"
                : bi.k1703.isChecked() ? "3"
                : "-1");


        //    MainApp.adolscent.setsAH1(String.valueOf(json));

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionK);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}