package com.wrl.exercise

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.wrl.exercise.util.Utils
import kotlinx.android.synthetic.main.activity_jump_to_another.*

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-23 14:39
 */
class JumpToAnotherActivity :AppCompatActivity() {

    var pkg  = "com.sohu.finance"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jump_to_another)
        setListener()
    }

    private fun setListener() {
        sohuBtn.setOnClickListener {
            if (Utils.checkInstall(this,pkg)) {
                if (!Utils.openApp(this,pkg)) {
                    Toast.makeText(this,"该应用没有安装",Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this,"该应用没有安装",Toast.LENGTH_SHORT).show()
            }
        }
    }
}