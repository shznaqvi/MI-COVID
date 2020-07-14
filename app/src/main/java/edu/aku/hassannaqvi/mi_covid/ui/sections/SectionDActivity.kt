package edu.aku.hassannaqvi.mi_covid.ui.sections

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.validatorcrawler.aliazaz.Clear
import com.validatorcrawler.aliazaz.Validator.Companion.emptyCheckingContainer
import edu.aku.hassannaqvi.mi_covid.R
import edu.aku.hassannaqvi.mi_covid.contracts.FormsContract
import edu.aku.hassannaqvi.mi_covid.core.MainApp
import edu.aku.hassannaqvi.mi_covid.databinding.ActivitySectionDBinding
import edu.aku.hassannaqvi.mi_covid.utils.openEndActivity
import org.json.JSONException
import org.json.JSONObject

class SectionDActivity : AppCompatActivity() {
    lateinit var bi: ActivitySectionDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d)
        bi.callback = this
        setupSkips()
    }

    private fun setupSkips() {
        bi.d02.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
            if (i == bi.d0202.id) {
                Clear.clearAllFields(bi.fldGrpCVd03)
            }
        }
    }

    @Throws(JSONException::class)
    private fun saveDraft() {
        val json = JSONObject()
        json.put("d1", bi.d1.text.toString())
        json.put("d02", when {
            bi.d0201.isChecked -> "1"
            bi.d0202.isChecked -> "2"
            else -> "-1"
        })
        json.put("d03", when {
            bi.d0301.isChecked -> "1"
            bi.d0302.isChecked -> "2"
            bi.d0303.isChecked -> "3"
            bi.d0304.isChecked -> "4"
            bi.d0305.isChecked -> "5"
            bi.d03096.isChecked -> "96"
            else -> "-1"
        })
        json.put("d04", when {
            bi.d0401.isChecked -> "1"
            bi.d0402.isChecked -> "2"
            else -> "-1"
        })
        json.put("d05", when {
            bi.d0501.isChecked -> "1"
            bi.d0502.isChecked -> "2"
            else -> "-1"
        })
    }

    private fun formValidation(): Boolean {
        return emptyCheckingContainer(this, bi.fldGrpSectionD)
    }

    private fun updateDB(): Boolean {
        val db = MainApp.appInfo.dbHelper
        val updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SM, MainApp.form.getsM())
        return if (updcount > 0) {
            true
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun btnContinue(v: View) {
        if (!formValidation()) return
        try {
            saveDraft()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (updateDB()) {
            finish()
            startActivity(Intent(this, SectionEActivity::class.java))
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnEnd(v: View) {
        openEndActivity(this)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show()
    }
}