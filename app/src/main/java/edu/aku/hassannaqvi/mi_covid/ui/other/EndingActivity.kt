package edu.aku.hassannaqvi.mi_covid.ui.other

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.aku.hassannaqvi.mi_covid.CONSTANTS
import edu.aku.hassannaqvi.mi_covid.R
import edu.aku.hassannaqvi.mi_covid.core.MainApp
import edu.aku.hassannaqvi.mi_covid.databinding.ActivityEndingBinding
import edu.aku.hassannaqvi.mi_covid.utils.JSONUtils
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class EndingActivity : AppCompatActivity() {
    lateinit var bi: ActivityEndingBinding
    private var subInfoEndActivityFlag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_ending)
        bi.callback = this

        val check = intent.getBooleanExtra("complete", false)
        subInfoEndActivityFlag = intent.getBooleanExtra(CONSTANTS.SUB_INFO_END_FLAG, false)
        val fStatus = intent.getStringExtra(CONSTANTS.FSTATUS_END_FLAG)
        val fStatusEndActivityFlag = fStatus != null
        if (check) {
            bi.a0601.isEnabled = true
            bi.a0602.isEnabled = fStatusEndActivityFlag
            bi.a0603.isEnabled = fStatusEndActivityFlag
            bi.a0604.isEnabled = fStatusEndActivityFlag
            bi.a0605.isEnabled = fStatusEndActivityFlag
            bi.a0606.isEnabled = fStatusEndActivityFlag
            bi.a0607.isEnabled = fStatusEndActivityFlag
        } else {
            bi.a0601.isEnabled = false
            if (fStatus == null) {
                bi.a0602.isEnabled = true
                bi.a0603.isEnabled = true
                bi.a0604.isEnabled = true
                bi.a0605.isEnabled = true
                bi.a0606.isEnabled = true
                bi.a0696.isEnabled = true
            } else {
                bi.a0602.isEnabled = false
                bi.a0603.isEnabled = false
                bi.a0604.isEnabled = false
                bi.a0605.isEnabled = false
                bi.a0606.isEnabled = false
                bi.a0607.isEnabled = false
                bi.a0696.isEnabled = false
                when (fStatus.toInt()) {
                    1 -> {
                        bi.a0602.isEnabled = true
                        bi.a0603.isEnabled = true
                        bi.a0604.isEnabled = true
                        bi.a0605.isEnabled = true
                        bi.a0606.isEnabled = true
                        bi.a0607.isEnabled = true
                        bi.a0696.isEnabled = true
                    }
                    2 -> bi.a0602.isEnabled = true
                    3 -> bi.a0603.isEnabled = true
                    4 -> bi.a0604.isEnabled = true
                    5 -> bi.a0605.isEnabled = true
                    6 -> bi.a0606.isEnabled = true
                    6 -> bi.a0607.isEnabled = true
                    96 -> bi.a0696.isEnabled = true
                }
            }
        }
    }

    fun BtnEnd() {
        if (formValidation()) {
            saveDraft()
            if (updateDB()) {
                finish()
                if (subInfoEndActivityFlag) startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveDraft() {
        val statusValue = if (bi.a0601.isChecked) "1" else if (bi.a0602.isChecked) "2" else if (bi.a0603.isChecked) "3" else if (bi.a0604.isChecked) "4" else if (bi.a0605.isChecked) "5" else if (bi.a0606.isChecked) "6" else if (bi.a0607.isChecked) "96" else "0"
        if (subInfoEndActivityFlag) {
            MainApp.form.istatus = statusValue
            MainApp.form.istatus96x = bi.a0696x.text.toString()

            val json = JSONObject()
//            json.put("ttChild", SectionSubInfoActivity.mainVModel.childU5.value.size ?: 0)
            try {
                val json_merge = JSONUtils.mergeJSONObjects(JSONObject(MainApp.form.getsInfo()), json)
                MainApp.form.setsInfo(json_merge.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        } else {
            MainApp.form.endingdatetime = SimpleDateFormat("dd-MM-yy HH:mm").format(Date().time)
            MainApp.form.setfStatus(statusValue)
            MainApp.form.fstatus96x = bi.a0696x.text.toString()
        }
    }

    private fun updateDB(): Boolean {
        val db = MainApp.appInfo.dbHelper
        val updcount = db.updateEnding(subInfoEndActivityFlag)
        return if (updcount == 1) {
            true
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun formValidation(): Boolean {
//        return ValidatorClass.EmptyRadioButton(this, bi.istatus, bi.istatus96, bi.istatus96x, getString(R.string.hh22h))
        return true
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext, "You Can't go back", Toast.LENGTH_LONG).show()
    }
}