package edu.aku.hassannaqvi.mi_covid.ui.sections;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import com.validatorcrawler.aliazaz.Validator;
import org.json.JSONException;
import org.json.JSONObject;
import edu.aku.hassannaqvi.mi_covid.R;
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionDBinding;

public class SectionDActivity extends AppCompatActivity {

    ActivitySectionDBinding bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_d);
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
                :  "-1");


    }

    private boolean formValidation() {
        return true;

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}