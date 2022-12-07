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

class Usuario_Datos : AppCompatActivity() {
    var etTel: EditText?=null
    var etNombre: EditText?=null
    var etEmail: EditText?=null
    var etPass: EditText?=null
    var etusuario: EditText?=null
    var idGlobal:String?=null
    var tv_id: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_datos)
        etNombre=findViewById(R.id.txb_Nombre)
        etEmail=findViewById(R.id.txb_Email)
        etPass=findViewById(R.id.txb_pass)
        etTel=findViewById(R.id.txb_Telefono)
        etusuario=findViewById(R.id.txb_usuario)
        tv_id= findViewById(R.id.tv_id2)

        val nom=intent.getStringExtra("nom")
        val cor=intent.getStringExtra("cor")
        val tel=intent.getStringExtra("tel")
        val usu=intent.getStringExtra("usu")
        val contra=intent.getStringExtra("contra")
        val idc=intent.getStringExtra("idc")
        etNombre?.setText(nom)
        etEmail?.setText(cor)
        etPass?.setText(contra)
        etTel?.setText(tel)
        etusuario?.setText(usu)
        tv_id?.text=idc
        idGlobal=idc

    }
    fun save(vista: View){
        val url = "http://192.168.43.102/VSC_PHP1/insertarU.php"
        val queue = Volley.newRequestQueue(this)

        var resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { respuesta ->
                //miclase.cargar()
                Toast.makeText(this, "Usuario insertado ", Toast.LENGTH_LONG).show()
                etNombre?.setText("")
                etPass?.setText("")
                etEmail?.setText("")
                etusuario?.setText("")
                etTel?.setText("")
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }) {

            override fun getParams(): MutableMap<String, String> {
                val parametro = HashMap<String, String>()
                parametro.put("nombre", etNombre?.text.toString())
                parametro.put("correo", etEmail?.text.toString())
                parametro.put("telefono", etTel?.text.toString())
                parametro.put("usuario", etusuario?.text.toString())
                parametro.put("contraseña", etPass?.text.toString())
                return parametro
            }
        }
        queue.add(resultadoPost)


    }

    fun clickGuardaEditar(view: View){
        val url="http://192.168.43.102/VSC_PHP1/modificarU.php"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"El usuario ha sido modificado", Toast.LENGTH_LONG).show()
                //miclase.cargar()
                tv_id?.setText("")
                etNombre?.setText("")
                etPass?.setText("")
                etEmail?.setText("")
                etusuario?.setText("")
                etTel?.setText("")
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar usuario $error", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("idusuario",idGlobal!!)
                parametros.put("nombre",etNombre?.text.toString())
                parametros.put("correo",etEmail?.text.toString())
                parametros.put("telefono",etTel?.text.toString())
                parametros.put("usuario",etusuario?.text.toString())
                parametros.put("contraseña",etPass?.text.toString())


                return parametros
            }
        }
        queue.add(resultadoPost)



    }
}