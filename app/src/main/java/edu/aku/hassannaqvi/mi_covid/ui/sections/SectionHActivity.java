package edu.aku.hassannaqvi.mi_covid.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionHBinding;
import edu.aku.hassannaqvi.mi_covid.utils.AppUtilsKt;

public class SectionHActivity extends AppCompatActivity {

    ActivitySectionHBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_h);
        bi.setCallback(this);
        setupSkips();
    }


    private void setupSkips() {

        bi.h04.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.h0402.getId()) {
                Clear.clearAllFields(bi.fldGrpCVh05);
            }
        }));

        bi.h06.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.h0602.getId()) {
                Clear.clearAllFields(bi.fldGrpSecH01);
            }
        }));

        bi.h10.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.h1002.getId()) {
                Clear.clearAllFields(bi.fldGrpSecH02);
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
            startActivity(new Intent(this, SectionIActivity.class));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SH, MainApp.form.getsH());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("h01dd", bi.h01dd.getText().toString());

        json.put("h01mm", bi.h01mm.getText().toString());

        json.put("h01yy", bi.h01yy.getText().toString());

        json.put("h02dd", bi.h02dd.getText().toString());

        json.put("h02mm", bi.h02mm.getText().toString());

        json.put("h02yy", bi.h02yy.getText().toString());

        json.put("h04", bi.h0401.isChecked() ? "1"
                : bi.h0402.isChecked() ? "2"
                : "-1");

        json.put("h05", bi.h05.getText().toString());

        json.put("h06", bi.h0601.isChecked() ? "1"
                : bi.h0602.isChecked() ? "2"
                : "-1");

        json.put("h0701", bi.h0701.getText().toString());

        json.put("h0702", bi.h0702.getText().toString());

        json.put("h09", bi.h0901.isChecked() ? "1"
                : bi.h0902.isChecked() ? "2"
                : bi.h0903.isChecked() ? "3"
                : "-1");

        json.put("h10", bi.h1001.isChecked() ? "1"
                : bi.h1002.isChecked() ? "2"
                : "-1");

        json.put("h11", bi.h1101.isChecked() ? "1"
                : bi.h1102.isChecked() ? "2"
                : bi.h1103.isChecked() ? "3"
                : bi.h11096.isChecked() ? "96"
                : bi.h11098.isChecked() ? "98"
                : "-1");

        json.put("h12dd", bi.h12dd.getText().toString());

        json.put("h12mm", bi.h12mm.getText().toString());

        json.put("h12yy", bi.h12yy.getText().toString());

        json.put("h13", bi.h1301.isChecked() ? "1"
                : bi.h1302.isChecked() ? "2"
                : bi.h1303.isChecked() ? "3"
                : bi.h13096.isChecked() ? "96"
                : "-1");

        json.put("h14", bi.h1401.isChecked() ? "1"
                : bi.h1402.isChecked() ? "2"
                : bi.h1403.isChecked() ? "3"
                : bi.h14096.isChecked() ? "96"
                : "-1");

        json.put("h15", bi.h1501.isChecked() ? "1"
                : bi.h1502.isChecked() ? "2"
                : bi.h1503.isChecked() ? "3"
                : bi.h1504.isChecked() ? "4"
                : bi.h1505.isChecked() ? "5"
                : bi.h1506.isChecked() ? "6"
                : "-1");

        json.put("h16", bi.h16.getText().toString());

        json.put("h17", bi.h1701.isChecked() ? "1"
                : bi.h1702.isChecked() ? "2"
                : "-1");

        json.put("h1801", bi.h1801.getText().toString());

        json.put("h1802", bi.h1802.getText().toString());

        MainApp.form.setsH(json.toString());

    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionH);

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //ViewGroup viewGroup = findViewById(android.R.id.content);
        View backDialogView = LayoutInflater.from(this).inflate(R.layout.back_dialog, null, false);
        builder.setView(backDialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        backDialogView.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SectionHActivity.super.onBackPressed();
            }
        });
        backDialogView.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        // Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
        //backDialogView.findViewById<View>(R.id.btnOk).setOnClickListener


    }

}