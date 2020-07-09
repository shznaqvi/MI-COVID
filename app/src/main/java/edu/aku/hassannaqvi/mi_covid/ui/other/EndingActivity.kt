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
import edu.aku.hassannaqvi.mi_covid.validator.ValidatorClass
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
            bi.istatusa.isEnabled = true
            bi.istatusb.isEnabled = fStatusEndActivityFlag
            bi.istatusc.isEnabled = fStatusEndActivityFlag
            bi.istatusd.isEnabled = fStatusEndActivityFlag
            bi.istatuse.isEnabled = fStatusEndActivityFlag
            bi.istatusf.isEnabled = fStatusEndActivityFlag
            bi.istatus96.isEnabled = fStatusEndActivityFlag
        } else {
            bi.istatusa.isEnabled = false
            if (fStatus == null) {
                bi.istatusb.isEnabled = true
                bi.istatusc.isEnabled = true
                bi.istatusd.isEnabled = true
                bi.istatuse.isEnabled = true
                bi.istatusf.isEnabled = true
                bi.istatus96.isEnabled = true
            } else {
                bi.istatusb.isEnabled = false
                bi.istatusc.isEnabled = false
                bi.istatusd.isEnabled = false
                bi.istatuse.isEnabled = false
                bi.istatusf.isEnabled = false
                bi.istatus96.isEnabled = false
                when (fStatus.toInt()) {
                    1 -> {
                        bi.istatusb.isEnabled = true
                        bi.istatusc.isEnabled = true
                        bi.istatusd.isEnabled = true
                        bi.istatuse.isEnabled = true
                        bi.istatusf.isEnabled = true
                        bi.istatus96.isEnabled = true
                    }
                    2 -> bi.istatusb.isEnabled = true
                    3 -> bi.istatusc.isEnabled = true
                    4 -> bi.istatusd.isEnabled = true
                    5 -> bi.istatuse.isEnabled = true
                    6 -> bi.istatusf.isEnabled = true
                    96 -> bi.istatus96.isEnabled = true
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
        val statusValue = if (bi.istatusa.isChecked) "1" else if (bi.istatusb.isChecked) "2" else if (bi.istatusc.isChecked) "3" else if (bi.istatusd.isChecked) "4" else if (bi.istatuse.isChecked) "5" else if (bi.istatusf.isChecked) "6" else if (bi.istatus96.isChecked) "96" else "0"
        if (subInfoEndActivityFlag) {
            MainApp.form.istatus = statusValue
            MainApp.form.istatus88x = bi.istatus96x.text.toString()

            val json = JSONObject()
            json.put("ttChild", SectionSubInfoActivity.mainVModel.childU5.value.size ?: 0)
            try {
                val json_merge = JSONUtils.mergeJSONObjects(JSONObject(MainApp.form.getsInfo()), json)
                MainApp.form.setsInfo(json_merge.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        } else {
            MainApp.form.endingdatetime = SimpleDateFormat("dd-MM-yy HH:mm").format(Date().time)
            MainApp.form.setfStatus(statusValue)
            MainApp.form.fstatus88x = bi.istatus96x.text.toString()
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
        return ValidatorClass.EmptyRadioButton(this, bi.istatus, bi.istatus96, bi.istatus96x, getString(R.string.hh22h))
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext, "You Can't go back", Toast.LENGTH_LONG).show()
    }
}