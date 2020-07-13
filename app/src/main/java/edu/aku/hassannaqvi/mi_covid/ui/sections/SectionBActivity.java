package edu.aku.hassannaqvi.mi_covid.ui.sections;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.mi_covid.R;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionBBinding;

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

        //s2q3
        bi.s2q3.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.s2q3a.getId()) {
                bi.fldGrpCVs2q4.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVs2q4);
                bi.fldGrpCVs2q4.setVisibility(View.GONE);
            }
        });


        //a40397
        /*bi.a40397.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.a403check, false);
                bi.a403check.setTag("-1");
                bi.fldGrpCVa02.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.a403check, true);
                bi.a403check.setTag("0");
                Clear.clearAllFields(bi.fldGrpCVa02);
                bi.fldGrpCVa02.setVisibility(View.GONE);
            }
        });*/

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
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SB, MainApp.fc.getsB());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        ActivitySectionBBinding bi;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
            bi.setCallback(this);
            setupSkip();
        }


        private void setupSkip () {

            //s2q3
            bi.s2q3.setOnCheckedChangeListener((group, checkedId) -> {

                if (checkedId == bi.s2q3a.getId()) {
                    bi.fldGrpCVs2q4.setVisibility(View.VISIBLE);
                } else {
                    Clear.clearAllFields(bi.fldGrpCVs2q4);
                    bi.fldGrpCVs2q4.setVisibility(View.GONE);
                }
            });


            //a40397
        /*bi.a40397.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.a403check, false);
                bi.a403check.setTag("-1");
                bi.fldGrpCVa02.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.a403check, true);
                bi.a403check.setTag("0");
                Clear.clearAllFields(bi.fldGrpCVa02);
                bi.fldGrpCVa02.setVisibility(View.GONE);
            }
        });*/

        }


        public void BtnContinue () {
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


        private boolean UpdateDB () {

            DatabaseHelper db = MainApp.appInfo.getDbHelper();
            int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SB, MainApp.fc.getsB());
            if (updcount > 0) {
                return true;
            } else {
                Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        private void SaveDraft () throws JSONException {

            JSONObject json = new JSONObject();

            json.put("muac1", bi.muac1.getText().toString().trim().isEmpty() ? "-1" : bi.muac1.getText().toString());

            json.put("muac2", bi.muac2.getText().toString().trim().isEmpty() ? "-1" : bi.muac2.getText().toString());

            json.put("s2q1", bi.s2q1a.isChecked() ? "1" : bi.s2q1b.isChecked() ? "2" : "-1");

            json.put("s2q2", bi.s2q2a.isChecked() ? "1"
                    : bi.s2q2b.isChecked() ? "2"
                    : bi.s2q2c.isChecked() ? "3"
                    : bi.s2q2d.isChecked() ? "4"
                    : "-1");

            json.put("s2q3", bi.s2q3a.isChecked() ? "1"
                    : bi.s2q3b.isChecked() ? "2"
                    : "-1");

            json.put("s2q4", bi.s2q4a.isChecked() ? "1"
                    : bi.s2q4b.isChecked() ? "2"
                    : bi.s2q4c.isChecked() ? "3"
                    : "-1");

            json.put("s2q501", bi.s2q501a.isChecked() ? "1"
                    : bi.s2q501b.isChecked() ? "2"
                    : "-1");

            json.put("s2q502", bi.s2q502a.isChecked() ? "1"
                    : bi.s2q502b.isChecked() ? "2"
                    : "-1");

            json.put("s2q503", bi.s2q503a.isChecked() ? "1"
                    : bi.s2q503b.isChecked() ? "2"
                    : "-1");

            json.put("s2q504", bi.s2q504a.isChecked() ? "1"
                    : bi.s2q504b.isChecked() ? "2"
                    : "-1");

            json.put("s2q505", bi.s2q505a.isChecked() ? "1"
                    : bi.s2q505b.isChecked() ? "2"
                    : "-1");

            json.put("s2q506", bi.s2q506a.isChecked() ? "1"
                    : bi.s2q506b.isChecked() ? "2"
                    : "-1");

            json.put("s2q507", bi.s2q507a.isChecked() ? "1"
                    : bi.s2q507b.isChecked() ? "2"
                    : "-1");

            json.put("s2q508", bi.s2q508a.isChecked() ? "1"
                    : bi.s2q508b.isChecked() ? "2"
                    : "-1");

            json.put("s2q6", bi.s2q6a.isChecked() ? "1"
                    : bi.s2q6b.isChecked() ? "2"
                    : "-1");

            json.put("s2q7", bi.s2q7a.isChecked() ? "1"
                    : bi.s2q7b.isChecked() ? "2"
                    : "-1");

            json.put("s2q8", bi.s2q8a.isChecked() ? "1"
                    : bi.s2q8b.isChecked() ? "2"
                    : "-1");

            json.put("s2q9", bi.s2q9a.isChecked() ? "1"
                    : bi.s2q9b.isChecked() ? "2"
                    : "-1");

            MainApp.fc.setsB(String.valueOf(json));
        }


        private boolean formValidation () {

            if (!Validator.emptyCheckingContainer(this, bi.fldGrpSectionB)) {
                return false;
            }

            if (Float.parseFloat(bi.muac1.getText().toString().trim()) < 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) < 11.5 && !bi.s2q1a.isChecked()) {
                return ValidatorClass.EmptyCustomRadio(this, bi.s2q1b, "Response is not matching with MUAC, please re-check");
            } else if (Float.parseFloat(bi.muac1.getText().toString().trim()) >= 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) >= 11.5 && !bi.s2q1b.isChecked()) {
                return ValidatorClass.EmptyCustomRadio(this, bi.s2q1a, "Response is not matching with MUAC, please re-check");
            } else if ((Float.parseFloat(bi.muac1.getText().toString().trim()) < 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) >= 11.5) || (Float.parseFloat(bi.muac1.getText().toString().trim()) >= 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) < 11.5)) {
                return ValidatorClass.EmptyCustomRadio(this, bi.s2q1a, "MUAC reading error, please re-check");
            }

            return true;
        }


        public void BtnEnd () {
            UtilKt.openEndActivity(this);
        }

        @Override
        public void onBackPressed () {
            Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
        }

        public void showTooltip (@NotNull View view){
            if (view.getId() != View.NO_ID) {
                String package_name = getApplicationContext().getPackageName();
                // Question Number Textview ID must be prefixed with q_ e.g.: 'q_aa12a'
                String infoid = view.getResources().getResourceName(view.getId()).replace(package_name + ":id/q_", "");
                // Question info text must be suffixed with _info e.g.: aa12a_info
                int stringRes = this.getResources().getIdentifier(infoid + "_info", "string", getApplicationContext().getPackageName());
                // Fetch info text from strings.xml
                //String infoText = (String) getResources().getText(stringRes);
                // Check if string resource exists to avoid crash on missing info string
                if (stringRes != 0) {
                    // Fetch info text from strings.xml
                    String infoText = (String) getResources().getText(stringRes);
                    new AlertDialog.Builder(this)
                            .setTitle("Info: " + infoid.toUpperCase())
                            .setMessage(infoText)
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                } else {
                    Toast.makeText(this, "No information available on this question.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No ID Associated with this question.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.fldGrpSectionB)) {
            return false;
        }

        if (Float.parseFloat(bi.muac1.getText().toString().trim()) < 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) < 11.5 && !bi.s2q1a.isChecked()) {
            return ValidatorClass.EmptyCustomRadio(this, bi.s2q1b, "Response is not matching with MUAC, please re-check");
        } else if (Float.parseFloat(bi.muac1.getText().toString().trim()) >= 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) >= 11.5 && !bi.s2q1b.isChecked()) {
            return ValidatorClass.EmptyCustomRadio(this, bi.s2q1a, "Response is not matching with MUAC, please re-check");
        } else if ((Float.parseFloat(bi.muac1.getText().toString().trim()) < 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) >= 11.5) || (Float.parseFloat(bi.muac1.getText().toString().trim()) >= 11.5 && Float.parseFloat(bi.muac2.getText().toString().trim()) < 11.5)) {
            return ValidatorClass.EmptyCustomRadio(this, bi.s2q1a, "MUAC reading error, please re-check");
        }

        return true;
    }


    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

    public void showTooltip(@NotNull View view) {
        if (view.getId() != View.NO_ID) {
            String package_name = getApplicationContext().getPackageName();
            // Question Number Textview ID must be prefixed with q_ e.g.: 'q_aa12a'
            String infoid = view.getResources().getResourceName(view.getId()).replace(package_name + ":id/q_", "");
            // Question info text must be suffixed with _info e.g.: aa12a_info
            int stringRes = this.getResources().getIdentifier(infoid + "_info", "string", getApplicationContext().getPackageName());
            // Fetch info text from strings.xml
            //String infoText = (String) getResources().getText(stringRes);
            // Check if string resource exists to avoid crash on missing info string
            if (stringRes != 0) {
                // Fetch info text from strings.xml
                String infoText = (String) getResources().getText(stringRes);
                new AlertDialog.Builder(this)
                        .setTitle("Info: " + infoid.toUpperCase())
                        .setMessage(infoText)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            } else {
                Toast.makeText(this, "No information available on this question.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No ID Associated with this question.", Toast.LENGTH_SHORT).show();
        }
    }
}