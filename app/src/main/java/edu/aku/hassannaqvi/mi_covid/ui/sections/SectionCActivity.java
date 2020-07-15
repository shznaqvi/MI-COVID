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
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionCBinding;
import edu.aku.hassannaqvi.mi_covid.utils.AppUtilsKt;

public class SectionCActivity extends AppCompatActivity {

    ActivitySectionCBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {
        //c01
        bi.c01.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.llc02);
        });

        //c07
        bi.c07.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.llc08);
        });
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionDActivity.class));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SC, MainApp.form.getsC());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("c01", bi.c0101.isChecked() ? "1"
                : bi.c0102.isChecked() ? "2"
                : bi.c0103.isChecked() ? "3"
                : bi.c0104.isChecked() ? "4"
                : bi.c0105.isChecked() ? "5"
                : bi.c0106.isChecked() ? "6"
                : bi.c0107.isChecked() ? "7"
                : bi.c01096.isChecked() ? "96"
                : "-1");

        json.put("c0201", bi.c0201.getText().toString());

        json.put("c0202", bi.c0202.getText().toString());

        json.put("c03", bi.c0301.isChecked() ? "1"
                : bi.c0302.isChecked() ? "2"
                : bi.c0303.isChecked() ? "3"
                : bi.c0304.isChecked() ? "4"
                : bi.c03096.isChecked() ? "96"
                : "-1");

        json.put("c04", bi.c0401.isChecked() ? "1"
                : bi.c0402.isChecked() ? "2"
                : bi.c0403.isChecked() ? "3"
                : bi.c0404.isChecked() ? "4"
                : bi.c0405.isChecked() ? "5"
                : bi.c0406.isChecked() ? "6"
                : bi.c0407.isChecked() ? "96"
                : "-1");

        json.put("c05", bi.c0501.isChecked() ? "1"
                : bi.c0502.isChecked() ? "2"
                : bi.c0503.isChecked() ? "3"
                : bi.c05096.isChecked() ? "96"
                : "-1");

        json.put("c06", bi.c0601.isChecked() ? "1"
                : bi.c0602.isChecked() ? "2"
                : bi.c0603.isChecked() ? "3"
                : bi.c0604.isChecked() ? "4"
                : bi.c0605.isChecked() ? "5"
                : bi.c06096.isChecked() ? "96"
                : "-1");

        json.put("c07", bi.c0701.isChecked() ? "1"
                : bi.c0702.isChecked() ? "2"
                : "-1");

        json.put("c08", bi.c0801.isChecked() ? "1"
                : bi.c0802.isChecked() ? "2"
                : "-1");

    //    json.put("ch06a", "-1");

        //    json.put("ch06a1", "-1");

        //    json.put("ch06a2", "-1");

        //    json.put("ch06a3", "-1");

        //    json.put("ch06a4", "-1");

        //    json.put("ch06a5", "-1");

        //    json.put("ch06a6", "-1");

        //    json.put("ch06a7", "-1");

        //    json.put("ch06a8", "-1");

        //    json.put("ch06ax", "-1");

        MainApp.form.setsC(json.toString());


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
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
                SectionCActivity.super.onBackPressed();
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