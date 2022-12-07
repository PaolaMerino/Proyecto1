package com.example.interfaz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_categoria_datos.*

class Categoria_Datos : AppCompatActivity() {
     var et_Categoria: EditText?=null
    var textView8: TextView?=null
    var idGlobal:String?=null
    var btGuardar: Button ?=null
    var btCancelar: Button?=null

    //val miclase = MainActcategoria()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_datos)
        et_Categoria = findViewById(R.id.etCategoria)
        textView8= findViewById(R.id.tv_id)

        val dato=intent.getStringExtra("dato")
        val idc=intent.getStringExtra("idc")
        et_Categoria?.setText(dato)
        textView8?.text=idc
        idGlobal=idc

    }
    fun clickBotonGuardar(view: View) {

        val url = "http://192.168.43.102/VSC_PHP1/insertar.php"
        val queue = Volley.newRequestQueue(this)

        var resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { respuesta ->
                //miclase.cargar()
                Toast.makeText(this, "Categoria insertada ", Toast.LENGTH_LONG).show()
                et_Categoria?.setText("")
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }) {

            override fun getParams(): MutableMap<String, String> {
                val parametro = HashMap<String, String>()
                parametro.put("categoria", et_Categoria?.text.toString())
                return parametro
            }
        }

        queue.add(resultadoPost)
    }
    fun clickGuardaEditar(view: View){
        val url="http://192.168.43.102/VSC_PHP1/modificar.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"La categoria ha sido editado de forma exitosa",Toast.LENGTH_LONG).show()
                //miclase.cargar()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar la categoria $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("idcategoria",idGlobal!!)
                parametros.put("categoria",et_Categoria?.text.toString())

                return parametros
            }
        }
        queue.add(resultadoPost)


    }

}