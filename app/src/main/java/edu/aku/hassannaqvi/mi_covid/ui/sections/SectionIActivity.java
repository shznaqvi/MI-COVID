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
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionIBinding;
import edu.aku.hassannaqvi.mi_covid.utils.AppUtilsKt;

public class SectionIActivity extends AppCompatActivity {

    ActivitySectionIBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_i);
        bi.setCallback(this);
        setupSkips();
    }


    private void setupSkips() {

        bi.i02.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.i0202.getId()) {
                Clear.clearAllFields(bi.fldGrpSecI01);
            }
        }));

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
            startActivity(new Intent(this, SectionJActivity.class));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SI, MainApp.form.getsI());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("i01", bi.i0101.isChecked() ? "1"
                : bi.i0102.isChecked() ? "2"
                : "-1");

        json.put("i02", bi.i0201.isChecked() ? "1"
                : bi.i0202.isChecked() ? "2"
                : "-1");

        json.put("i03", bi.i0301.isChecked() ? "1"
                : bi.i0302.isChecked() ? "2"
                : bi.i0303.isChecked() ? "3"
                : bi.i03098.isChecked() ? "98"
                : "-1");

        json.put("i04", bi.i0401.isChecked() ? "1"
                : bi.i0402.isChecked() ? "2"
                : bi.i0403.isChecked() ? "3"
                : bi.i0404.isChecked() ? "4"
                : bi.i0405.isChecked() ? "5"
                : bi.i0406.isChecked() ? "6"
                : bi.i0407.isChecked() ? "7"
                : bi.i04096.isChecked() ? "96"
                : "-1");

        json.put("i05", bi.i0501.isChecked() ? "1"
                : bi.i0502.isChecked() ? "2"
                : bi.i0503.isChecked() ? "3"
                : bi.i0504.isChecked() ? "4"
                : bi.i0505.isChecked() ? "5"
                : "-1");

        json.put("i06", bi.i0601.isChecked() ? "1"
                : bi.i0602.isChecked() ? "2"
                : bi.i0603.isChecked() ? "3"
                : "-1");

        json.put("i07", bi.i0701.isChecked() ? "1"
                : bi.i0702.isChecked() ? "2"
                : "-1");

        MainApp.form.setsI(json.toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionI);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}