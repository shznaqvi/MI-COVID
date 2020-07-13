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
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionLBinding;
import edu.aku.hassannaqvi.mi_covid.ui.other.EndingActivity;

public class SectionLActivity extends AppCompatActivity {

    ActivitySectionLBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_l);
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
                startActivity(new Intent(this, EndingActivity.class));
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

        json.put("l01", bi.l0101.isChecked() ? "1"
                : bi.l0102.isChecked() ? "2"
                : "-1");

        json.put("l02", bi.l0201.isChecked() ? "1"
                : bi.l0202.isChecked() ? "2"
                : bi.l0203.isChecked() ? "3"
                : bi.l0204.isChecked() ? "4"
                : bi.l0205.isChecked() ? "5"
                : bi.l0206.isChecked() ? "6"
                : bi.l02096.isChecked() ? "96"
                : "-1");

        json.put("l03", bi.l0301.isChecked() ? "1"
                : bi.l0302.isChecked() ? "2"
                : bi.l0303.isChecked() ? "3"
                : bi.l0304.isChecked() ? "4"
                : bi.l0305.isChecked() ? "5"
                : "-1");

        json.put("l04", bi.l0401.isChecked() ? "1"
                : bi.l0402.isChecked() ? "2"
                : bi.l0403.isChecked() ? "3"
                : bi.l0404.isChecked() ? "4"
                : bi.l0405.isChecked() ? "5"
                : "-1");

        json.put("l05", bi.l0501.isChecked() ? "1"
                : bi.l0502.isChecked() ? "2"
                : "-1");

        json.put("l06", bi.l0601.isChecked() ? "1"
                : bi.l0602.isChecked() ? "2"
                : "-1");

        json.put("l07", bi.l0701.isChecked() ? "1"
                : bi.l0702.isChecked() ? "2"
                : "-1");

        json.put("l08", bi.l0801.isChecked() ? "1"
                : bi.l0802.isChecked() ? "2"
                : "-1");

        json.put("l09", bi.l0901.isChecked() ? "1"
                : bi.l0902.isChecked() ? "2"
                : "-1");

        json.put("l010", bi.l01001.isChecked() ? "1"
                : bi.l01002.isChecked() ? "2"
                : bi.l01003.isChecked() ? "3"
                : "-1");


        //    MainApp.adolscent.setsAH1(String.valueOf(json));

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionL);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}