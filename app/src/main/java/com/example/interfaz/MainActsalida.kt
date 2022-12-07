package com.example.interfaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActsalida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_actsalida)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Acerca De"
    }
}