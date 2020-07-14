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
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionBBinding;
import edu.aku.hassannaqvi.mi_covid.ui.other.EndingActivity;

public class SectionBActivity extends AppCompatActivity {

    ActivitySectionBBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {
        //b01
        bi.b01.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.llb0203);
        });

        //b14
        bi.b14.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.llb1517);
        });
    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionCActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SB, MainApp.form.getsB());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("b01", bi.b0101.isChecked() ? "1"
                : bi.b0102.isChecked() ? "2"
                : "-1");

        json.put("b02", bi.b02.getText().toString());

        json.put("b03", bi.b0301.isChecked() ? "1"
                : bi.b0302.isChecked() ? "2"
                : bi.b0303.isChecked() ? "3"
                : bi.b0304.isChecked() ? "4"
                : bi.b0305.isChecked() ? "5"
                : bi.b03096.isChecked() ? "96"
                : "-1");

        json.put("b04", bi.b0401.isChecked() ? "1"
                : bi.b0402.isChecked() ? "2"
                : bi.b0403.isChecked() ? "3"
                : bi.b04096.isChecked() ? "96"
                : "-1");

        json.put("b05", bi.b0501.isChecked() ? "1"
                : bi.b0502.isChecked() ? "2"
                : bi.b0503.isChecked() ? "3"
                : bi.b0504.isChecked() ? "4"
                : bi.b05096.isChecked() ? "96"
                : "-1");

        json.put("b06", "-1");

        json.put("b06ft", "-1");

        json.put("b06yrd", "-1");

        json.put("b06a", bi.b06a.getText().toString());

        json.put("b07", bi.b07.getText().toString());

        json.put("b0801", bi.b0801.getText().toString());

        json.put("b0802", bi.b0802.getText().toString());

        json.put("b0803", bi.b0803.getText().toString());

        json.put("b0804", bi.b0804.getText().toString());

        json.put("b0805", bi.b0805.getText().toString());

        json.put("b0806", bi.b0806.getText().toString());

        json.put("b0807", bi.b0807.getText().toString());

        json.put("b9", bi.b9.getText().toString());

        json.put("b10", bi.b10.getText().toString());

        json.put("b11096", bi.b11096.getText().toString());

        json.put("b11mc", bi.b011t01.isChecked() ? "1"
                : bi.b011t02.isChecked() ? "2"
                : "-1");

        json.put("b11ws", "-1");

        json.put("b12", bi.b1201.isChecked() ? "1"
                : bi.b1202.isChecked() ? "2"
                : bi.b1203.isChecked() ? "3"
                : bi.b1204.isChecked() ? "4"
                : bi.b1205.isChecked() ? "5"
                : bi.b1206.isChecked() ? "6"
                : bi.b1207.isChecked() ? "7"
                : bi.b1208.isChecked() ? "8"
                : bi.b1209.isChecked() ? "9"
                : bi.b1210.isChecked() ? "10"
                : bi.b1211.isChecked() ? "11"
                : bi.b12096.isChecked() ? "96"
                : "-1");

        json.put("b1301", bi.b130101.isChecked() ? "1"
                : bi.b130102.isChecked() ? "2"
                : "-1");

        json.put("b1302", bi.b130201.isChecked() ? "1"
                : bi.b130202.isChecked() ? "2"
                : "-1");

        json.put("b1303", bi.b130301.isChecked() ? "1"
                : bi.b130302.isChecked() ? "2"
                : "-1");

        json.put("b1304", bi.b130401.isChecked() ? "1"
                : bi.b130402.isChecked() ? "2"
                : "-1");

        json.put("b1305", bi.b130501.isChecked() ? "1"
                : bi.b130502.isChecked() ? "2"
                : "-1");

        json.put("b1306", bi.b130601.isChecked() ? "1"
                : bi.b130602.isChecked() ? "2"
                : "-1");

        json.put("b1307", bi.b130701.isChecked() ? "1"
                : bi.b130702.isChecked() ? "2"
                : "-1");

        json.put("b1308", bi.b130801.isChecked() ? "1"
                : bi.b130802.isChecked() ? "2"
                : "-1");

        json.put("b1309", bi.b130901.isChecked() ? "1"
                : bi.b130902.isChecked() ? "2"
                : "-1");

        json.put("b13096", "-1");

        json.put("b14", bi.b1401.isChecked() ? "1"
                : bi.b1402.isChecked() ? "2"
                : "-1");

        json.put("b1501", bi.b150101.isChecked() ? "1"
                : bi.b150102.isChecked() ? "2"
                : "-1");

        json.put("b1502", bi.b150201.isChecked() ? "1"
                : bi.b150202.isChecked() ? "2"
                : "-1");

        json.put("b1503", bi.b150301.isChecked() ? "1"
                : bi.b150302.isChecked() ? "2"
                : "-1");

        json.put("b1504", bi.b150401.isChecked() ? "1"
                : bi.b150402.isChecked() ? "2"
                : "-1");

        json.put("b1505", bi.b150501.isChecked() ? "1"
                : bi.b150502.isChecked() ? "2"
                : "-1");

        json.put("b15096", "-1");

        json.put("b16", bi.b1601.isChecked() ? "1"
                : bi.b1602.isChecked() ? "2"
                : "-1");

        json.put("b17", bi.b17.getText().toString());

        MainApp.form.setsB(json.toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}