package com.jjmin.izcalendar.ui.detailplan

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.ext.android.viewModel
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityDetailPlanBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import com.jjmin.izcalendar.ui.setting.SettingActivity
import com.jjmin.izcalendar.utils.SetTheme
import org.koin.core.parameter.parametersOf

class DetailPlanActivity : BaseActivity<ActivityDetailPlanBinding>() {

    override val layoutResourceId = R.layout.activity_detail_plan

    val useCase by lazy { DetailUseCase(this) }
    val theme by lazy { SetTheme() }

    private val viewModel: DetailViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewDataBinding.theme = theme
        setSupportActionBar(viewDataBinding.detailToolbar)
        supportActionBar?.title = useCase.toolbarDate
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        this.recreate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menuSetting -> {
                var intent = Intent(this, SettingActivity::class.java)
                startActivityForResult(intent,11)
            }

            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
