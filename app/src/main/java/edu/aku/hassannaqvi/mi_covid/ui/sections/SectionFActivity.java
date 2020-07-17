package edu.aku.hassannaqvi.mi_covid.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.mi_covid.R;
import edu.aku.hassannaqvi.mi_covid.contracts.FormsContract;
import edu.aku.hassannaqvi.mi_covid.core.DatabaseHelper;
import edu.aku.hassannaqvi.mi_covid.core.MainApp;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionFBinding;
import edu.aku.hassannaqvi.mi_covid.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.mi_covid.utils.AppUtilsKt.contextBackActivity;

public class SectionFActivity extends AppCompatActivity {


    ActivitySectionFBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f);
        bi.setCallback(this);
        setupSkips();
    }


    private void setupSkips() {

        bi.f01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.f0102.getId()) {
                Clear.clearAllFields(bi.fldGrpSecf01);
            }
        }));

        bi.f07.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.f0702.getId()) {
                Clear.clearAllFields(bi.fldGrpCVf08);
            }
        }));

        bi.f10.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.f1003.getId()) {
                Clear.clearAllFields(bi.fldGrpTVf11);
            }
        }));

        /*bi.ah2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah2b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVah3);
            }
        }));*/

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionHActivity.class));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SF, MainApp.form.getsF());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();
        json.put("f01", bi.f0101.isChecked() ? "1"
                : bi.f0102.isChecked() ? "2"
                : "-1");

        json.put("f02", bi.f02.getText().toString());

        json.put("f03", bi.f0301.isChecked() ? "1"
                : bi.f0302.isChecked() ? "2"
                : "-1");

        json.put("f04", bi.f0401.isChecked() ? "1"
                : bi.f0402.isChecked() ? "2"
                : bi.f0403.isChecked() ? "3"
                : bi.f0404.isChecked() ? "4"
                : bi.f0405.isChecked() ? "5"
                : bi.f0406.isChecked() ? "6"
                : bi.f0407.isChecked() ? "7"
                : bi.f0408.isChecked() ? "8"
                : bi.f0409.isChecked() ? "9"
                : bi.f04096.isChecked() ? "96"
                : "-1");

        //    json.put("f05", bi.f05.getText().toString());

        //    json.put("f06", bi.f06.getText().toString());

        json.put("f07", bi.f0701.isChecked() ? "1"
                : bi.f0702.isChecked() ? "2"
                : "-1");

        json.put("f08", bi.f0801.isChecked() ? "1"
                : bi.f0802.isChecked() ? "2"
                : bi.f0803.isChecked() ? "3"
                : bi.f0804.isChecked() ? "4"
                : bi.f0805.isChecked() ? "5"
                : bi.f0806.isChecked() ? "6"
                : bi.f08096.isChecked() ? "96"
                : "-1");

        json.put("f09dd", bi.f09dd.getText().toString());

        json.put("f09mm", bi.f09mm.getText().toString());

        json.put("f09yy", bi.f09yy.getText().toString());

        json.put("f10", bi.f1001.isChecked() ? "1"
                : bi.f1002.isChecked() ? "2"
                : bi.f1003.isChecked() ? "3"
                : "-1");

        json.put("f11dd", bi.f11dd.getText().toString());

        json.put("f11mm", bi.f11mm.getText().toString());

        json.put("f11yy", bi.f11yy.getText().toString());

        json.put("f12", bi.f1201.isChecked() ? "1"
                : bi.f1202.isChecked() ? "2"
                : bi.f1203.isChecked() ? "3"
                : "-1");

        json.put("f13", bi.f1301.isChecked() ? "1"
                : bi.f1302.isChecked() ? "2"
                : bi.f1303.isChecked() ? "3"
                : bi.f1304.isChecked() ? "4"
                : bi.f1305.isChecked() ? "5"
                : bi.f1306.isChecked() ? "6"
                : bi.f130196.isChecked() ? "7"
                : bi.f1308.isChecked() ? "8"
                : bi.f1309.isChecked() ? "9"
                : bi.f131096.isChecked() ? "10"
                : bi.f130296.isChecked() ? "96"
                : "-1");

        json.put("f14", bi.f1401.isChecked() ? "1"
                : bi.f1402.isChecked() ? "2"
                : bi.f1403.isChecked() ? "3"
                : bi.f1404.isChecked() ? "4"
                : bi.f1405.isChecked() ? "5"
                : bi.f1406.isChecked() ? "6"
                : bi.f1407.isChecked() ? "7"
                : bi.f1408.isChecked() ? "8"
                : bi.f1409.isChecked() ? "9"
                : bi.f1410.isChecked() ? "10"
                : bi.f14096.isChecked() ? "96"
                : "-1");

        json.put("f15", bi.f1501.isChecked() ? "1"
                : bi.f1502.isChecked() ? "2"
                : bi.f1503.isChecked() ? "3"
                : "-1");

        json.put("f16", bi.f1601.isChecked() ? "1"
                : bi.f1602.isChecked() ? "2"
                : "-1");

        json.put("f17", bi.f1701.isChecked() ? "1"
                : bi.f1702.isChecked() ? "2"
                : "-1");

        MainApp.form.setsF(json.toString());

    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }

    @Override
    public void onBackPressed() {
        contextBackActivity(this);
    }
}