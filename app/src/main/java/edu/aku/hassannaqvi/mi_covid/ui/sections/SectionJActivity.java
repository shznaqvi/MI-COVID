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
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionJBinding;
import edu.aku.hassannaqvi.mi_covid.ui.other.EndingActivity;

public class SectionJActivity extends AppCompatActivity {

    ActivitySectionJBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_j);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        bi.j01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.j0104.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj02);
            }
        }));

        bi.j03.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.j0302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj04);
            }
        }));

        bi.j06.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.j0601.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj07);
            }
        }));

        /*bi.j011.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.j01102.getId()) {
                Clear.clearAllFields(bi.fldGrpSecJ01);
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
                startActivity(new Intent(this, SectionKActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {
        startActivity(new Intent(this, EndingActivity.class).putExtra("complete" false))
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

        json.put("j01", bi.j0101.isChecked() ? "1"
                : bi.j0102.isChecked() ? "2"
                : bi.j0103.isChecked() ? "3"
                : bi.j0104.isChecked() ? "4"
                : "-1");

        json.put("j02", bi.j0201.isChecked() ? "1"
                : bi.j0202.isChecked() ? "2"
                : "-1");

        json.put("j03", bi.j0301.isChecked() ? "1"
                : bi.j0302.isChecked() ? "2"
                : "-1");

        json.put("j04", bi.j0401.isChecked() ? "1"
                : bi.j0402.isChecked() ? "2"
                : "-1");

        //    json.put("j05", bi.j05.getText().toString());

        json.put("j06", bi.j0601.isChecked() ? "1"
                : bi.j0602.isChecked() ? "2"
                : bi.j0603.isChecked() ? "3"
                : bi.j0604.isChecked() ? "4"
                : "-1");

        json.put("j07", bi.j0701.isChecked() ? "1"
                : bi.j0702.isChecked() ? "2"
                : "-1");

        json.put("j09", bi.j0901.isChecked() ? "1"
                : bi.j0902.isChecked() ? "2"
                : bi.j0903.isChecked() ? "3"
                : bi.j09096.isChecked() ? "96"
                : "-1");

        json.put("j010", bi.j01001.isChecked() ? "1"
                : bi.j01002.isChecked() ? "2"
                : "-1");

        json.put("j012", bi.j012.getText().toString());


        //    MainApp.adolscent.setsAH1(String.valueOf(json));

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionJ);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}