package com.example.interfaz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton

import org.json.JSONException


class MainActcategoria : AppCompatActivity() {
    var tbCategoria: TableLayout? = null
    val MY_DEFAULT_TIMEOUT = 15000
    val miclase = Categoria_Datos()
    var idGlobal:String?=null
    var cate:String?=null
    private var tv_get:TextView?=null

    //var addsBtn: FloatingActionButton?=null
    private lateinit var addsBtn: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_actcategoria)
        tbCategoria = findViewById(R.id.tbCategoria)
        addsBtn = findViewById(R.id.addingBtn)

         cargar()
    }
fun cargar(){
    tbCategoria?.removeAllViews()
    var queue = Volley.newRequestQueue(this)
    var url = "http://192.168.43.102/VSC_PHP1/registros.php"

    var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
        { response ->
            try {
                var jsonArray = response.getJSONArray("data")
                for (i in 0 until jsonArray.length()) {
                    var jsonObject = jsonArray.getJSONObject(i)
                    val registro = LayoutInflater.from(this)
                        .inflate(R.layout.table_row_proyecto1, null, false)
                    val colCategoria =registro.findViewById<View>(R.id.colCategoria) as TextView
                    val colEditar = registro.findViewById<View>(R.id.colEditar)
                    val colBorrar = registro.findViewById<View>(R.id.colBorrar)
                    colCategoria.text = jsonObject.getString("categoria")
                    colEditar.id = jsonObject.getString("idcategoria").toInt()
                    colBorrar.id = jsonObject.getString("idcategoria").toInt()
                    tbCategoria?.addView(registro)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
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

        Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
        idGlobal=view.id.toString()
        val queue=Volley.newRequestQueue(this)
        val url="http://192.168.43.102/VSC_PHP1/consulta.php?idcategoria=${idGlobal}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                cate=(response.getString("categoria"))
                var dato=cate.toString()
                var  idc=idGlobal
               val intent=Intent(this, Categoria_Datos::class.java)
                intent.putExtra("dato",dato)
                intent.putExtra("idc",idc)
                startActivity(intent)

            },Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)




        //Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
    }

    fun clickTablaBorrar(view: View) {
        Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
        val url="http://192.168.43.102/VSC_PHP1/borrar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Categoria Borrada",Toast.LENGTH_LONG).show()
                cargar()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error al borrar categoria $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("idcategoria",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
        //Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
    }
    fun addsbtn(view: View){
        val intent = Intent(this, Categoria_Datos::class.java)
        startActivity(intent)
    }





    }

















