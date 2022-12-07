package com.example.interfaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton

import org.json.JSONException

class MainAct_Usuarios : AppCompatActivity() {

    var idGlobal:String?=null
    val MY_DEFAULT_TIMEOUT = 15000
    var tbusuario: TableLayout? = null
    var n:String?=null
    var c:String?=null
    var t:String?=null
    var u:String?=null
    var con:String?=null

    //var addsBtn: FloatingActionButton?=null
    private lateinit var addsBtn: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_act_usuarios)
        tbusuario = findViewById(R.id.tbCategoria)
        addsBtn = findViewById(R.id.addingBtn)

        cargar()
    }

fun cargar(){
    tbusuario?.removeAllViews()
    var queue = Volley.newRequestQueue(this)
    var url = "http://192.168.43.102/VSC_PHP1/registrosU.php"

    var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
        Response.Listener { response ->
            try {
                var jsonArray = response.getJSONArray("data")
                for (i in 0 until jsonArray.length()) {
                    var jsonObject = jsonArray.getJSONObject(i)
                    val registro = LayoutInflater.from(this)
                        .inflate(R.layout.table_row_usuario, null, false)
                    val colnombre =registro.findViewById<View>(R.id.colnombre) as TextView
                    val colcorreo =registro.findViewById<View>(R.id.colcorreo) as TextView
                    val coltelefono =registro.findViewById<View>(R.id.coltelefono) as TextView
                    val colusuario =registro.findViewById<View>(R.id.colusuario) as TextView
                    val colpass =registro.findViewById<View>(R.id.colpass) as TextView

                    val colEditar = registro.findViewById<View>(R.id.colEditar)
                    val colBorrar = registro.findViewById<View>(R.id.colBorrar)
                    colnombre.text = jsonObject.getString("nombre")
                    colcorreo.text = jsonObject.getString("correo")
                    coltelefono.text = jsonObject.getString("telefono")
                    colusuario.text = jsonObject.getString("usuario")
                    colpass.text = jsonObject.getString("contraseña")

                    colEditar.id = jsonObject.getString("idusuario").toInt()
                    colBorrar.id = jsonObject.getString("idusuario").toInt()
                    tbusuario?.addView(registro)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
        }
    )

    jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
        MY_DEFAULT_TIMEOUT,
        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
    )
    queue.add(jsonObjectRequest)
}
    fun clickTablaEditar(view: View) {
        Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
        idGlobal = view.id.toString()
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.43.102/VSC_PHP1/consultaU.php?idusuario=${idGlobal}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                n = (response.getString("nombre"))
                c = (response.getString("correo"))
                t = (response.getString("telefono"))
                u = (response.getString("usuario"))
                con = (response.getString("contraseña"))
                var nom = n.toString()
                var cor = c.toString()
                var tel = t.toString()
                var usu = u.toString()
                var contra = con.toString()
                var idc = idGlobal
                val intent = Intent(this, Usuario_Datos::class.java)
                intent.putExtra("nom", nom)
                intent.putExtra("cor", cor)
                intent.putExtra("tel", tel)
                intent.putExtra("usu", usu)
                intent.putExtra("contra", contra)
                intent.putExtra("idc", idc)
                startActivity(intent)
            }, Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)


        //Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
    }
    fun clickTablaBorrar(view: View) {
        Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
        val url="http://192.168.43.102/VSC_PHP1/borrarU.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Usuario Borrado",Toast.LENGTH_LONG).show()
                cargar()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error al borrar usuario $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("idusuario",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
        Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
    }
    fun addsbtn(view: View){
        val intent = Intent(this, Usuario_Datos::class.java)
        startActivity(intent)
    }


}



