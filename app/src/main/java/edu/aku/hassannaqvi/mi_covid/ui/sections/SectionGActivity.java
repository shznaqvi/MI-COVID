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
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionGBinding;
import edu.aku.hassannaqvi.mi_covid.utils.AppUtilsKt;

public class SectionGActivity extends AppCompatActivity {

    ActivitySectionGBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g);
        bi.setCallback(this);
        setupSkips();
    }


    private void setupSkips() {

        bi.g01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVllg01);
            }
        }));

        bi.g02.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0202.getId()) {
                Clear.clearAllFields(bi.fldGrpCVllg02);
            }
        }));

        bi.g03.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0301.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg04);
            }
        }));

        bi.g06.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0601.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg07);
            } else if (i == bi.g0602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg08);
            }
        }));

        bi.g09.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVllg03);
            }
        }));

        bi.g11.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g1102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg12);
            }
        }));
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SG, MainApp.form.getsG());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
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


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("g01", bi.g0101.isChecked() ? "1"
                : bi.g0102.isChecked() ? "2"
                : "-1");

        json.put("g02", bi.g0201.isChecked() ? "1"
                : bi.g0202.isChecked() ? "2"
                : "-1");

        json.put("g03", bi.g0301.isChecked() ? "1"
                : bi.g0302.isChecked() ? "2"
                : "-1");

        json.put("g04", bi.g0401.isChecked() ? "1"
                : bi.g0402.isChecked() ? "2"
                : bi.g0403.isChecked() ? "3"
                : bi.g0404.isChecked() ? "4"
                : bi.g0405.isChecked() ? "5"
                : bi.g0406.isChecked() ? "6"
                : bi.g0407.isChecked() ? "7"
                //        : bi.g04096.isChecked() ? "96"
                : "-1");

        //    json.put("g04096x", bi.g04096x.getText().toString());
        json.put("g06", bi.g0601.isChecked() ? "1"
                : bi.g0602.isChecked() ? "2"
                : "-1");

        json.put("g09", bi.g0901.isChecked() ? "1"
                : bi.g0902.isChecked() ? "2"
                : "-1");

        json.put("g10", bi.g10.getText().toString());

        json.put("g11", bi.g1101.isChecked() ? "1"
                : bi.g1102.isChecked() ? "2"
                : "-1");

        json.put("g12", bi.g1201.isChecked() ? "1"
                : bi.g1202.isChecked() ? "2"
                : bi.g1203.isChecked() ? "3"
                : bi.g1204.isChecked() ? "4"
                : bi.g1205.isChecked() ? "5"
                : bi.g12096.isChecked() ? "96"
                : "-1");

        json.put("g13", bi.g1301.isChecked() ? "1"
                : bi.g1302.isChecked() ? "2"
                : bi.g1303.isChecked() ? "3"
                : bi.g1304.isChecked() ? "4"
                : bi.g1305.isChecked() ? "5"
                : bi.g1306.isChecked() ? "6"
                : bi.g13096.isChecked() ? "96"
                : "-1");

        MainApp.form.setsG(json.toString());

    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
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
                SectionGActivity.super.onBackPressed();
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