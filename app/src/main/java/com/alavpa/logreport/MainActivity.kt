package com.alavpa.logreport

import android.os.Bundle
import com.alavpa.logger.Logger

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger(this)
    }
}
