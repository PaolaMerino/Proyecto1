package com.example.interfaz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Login : AppCompatActivity() {
    var et_usuario: EditText? = null
    var et_contraseña: EditText? = null
    var user:String?=null
    var pass:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_usuario = findViewById(R.id.et_usuariol)
        et_contraseña = findViewById(R.id.et_contraseñal)
    }
    fun clickIngresar(view: View) {
            user=et_usuario?.text.toString()
        pass=et_contraseña?.text.toString()
        Toast.makeText(this,view.id.toString(), Toast.LENGTH_LONG).show()

        val queue= Volley.newRequestQueue(this)
        val url="http://192.168.43.102/VSC_PHP1/consultausuario.php?usuario=${user}&contraseña=${pass}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                Toast.makeText(this, "Sesión Iniciada", Toast.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent);
                user=(response.getString("usuario"))
                pass=(response.getString("contraseña"))
                val inflter = LayoutInflater.from(this)
                val v = inflter.inflate(R.layout.ingreso_correcto,null)


            }, { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
        //Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
    }
    fun moveToRegistration(view: View?) {
        val intent = Intent(this,Usuario_Datos::class.java)
        startActivity(intent);

    }
}
