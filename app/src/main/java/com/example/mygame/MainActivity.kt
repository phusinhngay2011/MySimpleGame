package com.example.mygame

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        Log.d("MainActivity", "OnStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "OnPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "OnStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "OnDestroy()")
        super.onDestroy()
    }

}