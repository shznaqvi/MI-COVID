package edu.aku.hassannaqvi.mi_covid.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.mi_covid.R;
import edu.aku.hassannaqvi.mi_covid.contracts.FormsContract;
import edu.aku.hassannaqvi.mi_covid.core.DatabaseHelper;
import edu.aku.hassannaqvi.mi_covid.core.MainApp;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionDBinding;
import edu.aku.hassannaqvi.mi_covid.ui.other.EndingActivity;

public class SectionDActivity extends AppCompatActivity {

    ActivitySectionDBinding bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_d);
        setupSkips();
    }


    private void setupSkips() {

        bi.d02.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.d0202.getId()) {
                Clear.clearAllFields(bi.fldGrpCVd03);
            }
        }));

        /*bi.ah2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah2b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVah3);
            }
        }));*/

    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SD, MainApp.form.getsD());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
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
                startActivity(new Intent(this, SectionEActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void SaveDraft() throws JSONException {


        JSONObject json = new JSONObject();
        json.put("d1", bi.d1.getText().toString());

        json.put("d02", bi.d0201.isChecked() ? "1"
                : bi.d0202.isChecked() ? "2"
                :  "-1");

        json.put("d03", bi.d0301.isChecked() ? "1"
                : bi.d0302.isChecked() ? "2"
                : bi.d0303.isChecked() ? "3"
                : bi.d0304.isChecked() ? "4"
                : bi.d0305.isChecked() ? "5"
                : bi.d03096.isChecked() ? "96"
                :  "-1");

        json.put("d04", bi.d0401.isChecked() ? "1"
                : bi.d0402.isChecked() ? "2"
                :  "-1");

        json.put("d05", bi.d0501.isChecked() ? "1"
                : bi.d0502.isChecked() ? "2"
                : "-1");


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionD);
    }


    public void BtnEnd() {
        startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}