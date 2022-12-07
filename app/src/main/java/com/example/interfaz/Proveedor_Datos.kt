package com.example.interfaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Proveedor_Datos : AppCompatActivity() {
    var et_nombre:EditText?=null
    var et_correo:EditText?=null
    var et_telefono:EditText?=null
    var et_direccion:EditText?=null
    var tv_idp: TextView?=null
    var idGlobal:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedor_datos)
        et_nombre = findViewById(R.id.et_nombre)
        et_correo = findViewById(R.id.et_correo)
        et_telefono = findViewById(R.id.et_telefono)
        et_direccion = findViewById(R.id.et_direccion)
        tv_idp=findViewById(R.id.tv_idprov)
        val nom=intent.getStringExtra("nom")
        val cor=intent.getStringExtra("cor")
        val tel=intent.getStringExtra("tel")
        val dir=intent.getStringExtra("dir")
        val idp=intent.getStringExtra("idp")
        et_nombre?.setText(nom)
        et_correo?.setText(cor)
        et_telefono?.setText(tel)
        et_direccion?.setText(dir)
        tv_idp?.text=idp
        idGlobal=idp

    }
    fun clickBotonGuardarProv(view: View) {

        val url = "http://192.168.43.102/VSC_PHP1/insertarp.php"
        val queue = Volley.newRequestQueue(this)

        var resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { respuesta ->
                //miclase.cargar()
                Toast.makeText(this, "Proveedor insertado ", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }) {

            override fun getParams(): MutableMap<String, String> {
                val parametro = HashMap<String, String>()
                parametro.put("nombreprov", et_nombre?.text.toString())
                parametro.put("correoprov", et_correo?.text.toString())
                parametro.put("telefonoprov", et_telefono?.text.toString())
                parametro.put("direccionprov", et_direccion?.text.toString())
                return parametro
            }
        }
        queue.add(resultadoPost)
    }

    fun clickGuardaEditarProv(view: View){
        val url="http://192.168.43.102/VSC_PHP1/modificarp.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Dato editado",Toast.LENGTH_LONG).show()
                //miclase.cargar()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar  $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("idproveedor",idGlobal!!)
                parametros.put("nombreprov",et_nombre?.text.toString())
                parametros.put("correoprov",et_correo?.text.toString())
                parametros.put("telefonoprov",et_telefono?.text.toString())
                parametros.put("direccionprov",et_direccion?.text.toString())

                return parametros
            }
        }
        queue.add(resultadoPost)


    }
}