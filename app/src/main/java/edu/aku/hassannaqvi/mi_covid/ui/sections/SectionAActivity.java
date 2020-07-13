package edu.aku.hassannaqvi.mi_covid.ui.sections;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.mi_covid.R;
import edu.aku.hassannaqvi.mi_covid.contracts.FormsContract;
import edu.aku.hassannaqvi.mi_covid.core.DatabaseHelper;
import edu.aku.hassannaqvi.mi_covid.core.MainApp;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionABinding;

public class SectionAActivity extends AppCompatActivity {

    /*private static final String TAG = "";
    public static FormsContract fc;
    public List<String> talukaName, ucName, villageName, usersName, teamLeadName, healthFacilityCode;
    public List<String> talukaCode, ucCode, villageCode, usersCode, teamLeadCode, healthFacilityName;*/
    ActivitySectionABinding bi;
    //private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a);
        bi.setCallback(this);
        //setupSkips();
    }


    /*private void setupSkips() {
        db = new DatabaseHelper(this);
        populateSpinner(this);
        *//*bi.s1q8.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.s1q8b.getId()) {
                bi.fldGrpCVs1q8r.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVs1q8r);
                bi.fldGrpCVs1q8r.setVisibility(View.GONE);
            }
        });*//*
    }*/


    public void BtnContinue() {
        if (formValidation(true)) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionBActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.fc.set_UID(MainApp.fc.getDeviceID() + MainApp.fc.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, MainApp.fc.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        /*MainApp.form = new FormsContract();
        MainApp.fc.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        MainApp.fc.setFormType(CONSTANTS.CHILDRECRUITMENT);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setUser2(MainApp.userName2);
        MainApp.fc.setDeviceID(MainApp.appInfo.getDeviceID());
        MainApp.fc.setDevicetagID(MainApp.appInfo.getTagName());
        MainApp.fc.setAppversion(MainApp.appInfo.getAppVersion());
        MainApp.setGPS(this); // Set GPS

        NumberFormat f = new DecimalFormat("00");
        String hf_name = bi.s1q1.getSelectedItem().toString();
        long hf_code = getHfCode(hf_name);
        MainApp.fc.setHfCode(f.format(hf_code));*/

        JJSONObject json = new JSONObject();

        json.put("a01", bi.a01.getText().toString());

        json.put("a02", bi.a02.getText().toString());

        json.put("a03", bi.a03.getText().toString());

        json.put("a04", bi.a0401.isChecked() ? "1"
                : bi.a0402.isChecked() ? "2"
                : bi.a0403.isChecked() ? "3"
                : bi.a0404.isChecked() ? "4"
                : bi.a0405.isChecked() ? "5"
                : bi.a0406.isChecked() ? "6"
                : "-1");

        json.put("a05", bi.a05.getText().toString());

        json.put("a05a", bi.a05a.getText().toString());

        json.put("a06", "-1");

        json.put("a07", bi.a07.getText().toString());

        json.put("a08", bi.a08.getText().toString());

        json.put("a09", bi.a09.getText().toString());

        json.put("a10", bi.a10.getText().toString());

        json.put("a11", bi.a1101.isChecked() ? "1"
                : bi.a1102.isChecked() ? "2"
                : "-1");

        json.put("a13dd", bi.a13dd.getText().toString());

        json.put("a13mm", bi.a13mm.getText().toString());

        json.put("a13yy", bi.a13yy.getText().toString());

        json.put("a15mm", bi.a15mm.getText().toString());

        json.put("a15yy", bi.a15yy.getText().toString());

        json.put("a16", bi.a1601.isChecked() ? "1"
                : bi.a1602.isChecked() ? "2"
                : bi.a1603.isChecked() ? "3"
                : "-1");

        json.put("a17", bi.a1701.isChecked() ? "1"
                : bi.a1702.isChecked() ? "2"
                : bi.a1703.isChecked() ? "3"
                : bi.a1704.isChecked() ? "4"
                : bi.a1705.isChecked() ? "5"
                : bi.a1706.isChecked() ? "6"
                : bi.a1707.isChecked() ? "7"
                : bi.a1708.isChecked() ? "8"
                : bi.a1709.isChecked() ? "9"
                : bi.a17096.isChecked() ? "96"
                : "-1");

        json.put("a18", bi.a18.getText().toString());

        json.put("a19", "-1");

        json.put("a2001", "-1");

        json.put("a2002", "-1");

        json.put("a2003", "-1");

        json.put("a2004", "-1");

        json.put("a20096", "-1");

        json.put("a21", bi.a2101.isChecked() ? "1"
                : bi.a2102.isChecked() ? "2"
                : bi.a2103.isChecked() ? "3"
                : bi.a2104.isChecked() ? "4"
                : bi.a2105.isChecked() ? "5"
                : bi.a2106.isChecked() ? "6"
                : bi.a2107.isChecked() ? "7"
                : bi.a2108.isChecked() ? "8"
                : bi.a2109.isChecked() ? "9"
                : bi.a2110.isChecked() ? "10"
                : bi.a2111.isChecked() ? "11"
                : bi.a21096.isChecked() ? "96"
                : "-1");

        json.put("a22", bi.a2201.isChecked() ? "1"
                : bi.a2202.isChecked() ? "2"
                : bi.a2203.isChecked() ? "3"
                : bi.a2204.isChecked() ? "4"
                : bi.a2205.isChecked() ? "5"
                : "-1");

        json.put("a23", bi.a2301.isChecked() ? "1"
                : bi.a2302.isChecked() ? "2"
                : bi.a2303.isChecked() ? "3"
                : bi.a2304.isChecked() ? "4"
                : bi.a2305.isChecked() ? "5"
                : bi.a2306.isChecked() ? "6"
                : bi.a2307.isChecked() ? "7"
                : bi.a2308.isChecked() ? "8"
                : bi.a2309.isChecked() ? "9"
                : bi.a2310.isChecked() ? "10"
                : bi.a2311.isChecked() ? "11"
                : bi.a23096.isChecked() ? "96"
                : "-1");

        json.put("a25pos", bi.a25pos.getText().toString());

        json.put("a25org", bi.a25org.getText().toString());

        json.put("a26", bi.a2601.isChecked() ? "1"
                : bi.a2602.isChecked() ? "2"
                : "-1");


        MainApp.fc.setsA(String.valueOf(json));
    }


    private boolean formValidation(boolean flag) {
        if (flag) return Validator.emptyCheckingContainer(this, bi.fldGrpSectionA);
        else return Validator.emptyCheckingContainer(this, bi.fldGrpSectionA01);
    }


    public void BtnEnd() {
        /*if (!formValidation(false)) return;
        UtilKt.contextEndActivity(this, false);*/
    }

    public void populateSpinner(final Context context) {
        // Spinner Drop down elements
        /*talukaName = new ArrayList<>();
        talukaCode = new ArrayList<>();

        talukaName.add("....");
        talukaCode.add("....");

        Collection<TalukasContract> dc = db.getTalukas();
        for (TalukasContract d : dc) {
            talukaName.add(d.getTaluka());
            talukaCode.add(d.getTalukacode());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, talukaName);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        bi.s1q2.setAdapter(dataAdapter);

        bi.s1q2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                healthFacilityCode = new ArrayList<>();
                healthFacilityName = new ArrayList<>();


                healthFacilityCode.add("....");
                healthFacilityName.add("....");

                Collection<HealthFacilitiesContract> pc = db.getHealthFacilities(talukaCode.get(position));
                for (HealthFacilitiesContract p : pc) {
                    healthFacilityCode.add(p.getFacilityCode());
                    healthFacilityName.add(p.getFacilityName());
                }
                ArrayAdapter<String> psuAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, healthFacilityName);

                psuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.s1q1.setAdapter(psuAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bi.s1q1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                villageCode = new ArrayList<>();
                villageName = new ArrayList<>();


                villageCode.add("....");
                villageName.add("....");

                Collection<VillagesContract> pc = db.getVillages(healthFacilityCode.get(position));
                for (VillagesContract p : pc) {
                    villageCode.add(p.getVillagecode());
                    villageName.add(p.getVillagename());
                }
                ArrayAdapter<String> vilAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, villageName);

                vilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.s1q3.setAdapter(vilAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }


    /*long getHfCode(String hf_name) {

     *//*DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.getHfCode("healthFacilities", hf_name);
        res.moveToFirst();
        return Long.parseLong(res.getString(res.getColumnIndex(HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_CODE)));*//*
    }*/

    /*@Override
    public void endSecActivity(boolean flag) {
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", flag));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
    }*/

}