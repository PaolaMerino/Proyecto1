package com.example.interfaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException

class MainActproveedores : AppCompatActivity() {
    var tbproveedor: TableLayout? = null
    var idGlobal:String?=null
    var dn:String?=null
    var dc:String?=null
    var dt:String?=null
    var dd:String?=null
    private lateinit var addsBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_actproveedores)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Proveedores"
        tbproveedor = findViewById(R.id.tbproveedor)
        addsBtn = findViewById(R.id.addingBtn2)
        cargar()
    }
    fun cargar(){
        tbproveedor?.removeAllViews()
        var queue = Volley.newRequestQueue(this)
        var url = "http://192.168.43.102/VSC_PHP1/registrosP.php"

        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    var jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        var jsonObject = jsonArray.getJSONObject(i)
                        val registro = LayoutInflater.from(this)
                            .inflate(R.layout.table_row_proveedores, null, false)
                        val colnombre =registro.findViewById<View>(R.id.colnombre) as TextView
                        val colcorreo =registro.findViewById<View>(R.id.colcorreo) as TextView
                        val coltelefono =registro.findViewById<View>(R.id.coltelefono) as TextView
                        val coldireccion =registro.findViewById<View>(R.id.coldireccion) as TextView
                        val colEditar = registro.findViewById<View>(R.id.colEditar)
                        val colBorrar = registro.findViewById<View>(R.id.colBorrar)
                        colnombre.text = jsonObject.getString("nombreprov")
                        colcorreo.text = jsonObject.getString("correoprov")
                        coltelefono.text = jsonObject.getString("telefonoprov")
                        coldireccion.text = jsonObject.getString("direccionprov")
                        colEditar.id = jsonObject.getString("idproveedor").toInt()
                        colBorrar.id = jsonObject.getString("idproveedor").toInt()
                        tbproveedor?.addView(registro)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }

        )
        queue.add(jsonObjectRequest)
    }
    fun clickTablaEditar(view: View) {

        Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
        idGlobal=view.id.toString()
        val queue=Volley.newRequestQueue(this)
        val url="http://192.168.43.102/VSC_PHP1/consultaP.php?idproveedor=${idGlobal}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                dn=(response.getString("nombreprov"))
                dc=(response.getString("correoprov"))
                dt=(response.getString("telefonoprov"))
                dd=(response.getString("direccionprov"))
                var nom=dn.toString()
                var cor=dc.toString()
                var tel=dt.toString()
                var dir=dd.toString()
                var  idp=idGlobal
                val intent= Intent(this, Proveedor_Datos::class.java)
                intent.putExtra("nom",nom)
                intent.putExtra("cor",cor)
                intent.putExtra("tel",tel)
                intent.putExtra("dir",dir)
                intent.putExtra("idp",idp)
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
        val url="http://192.168.43.102/VSC_PHP1/borrarP.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Proveedor Borrado",Toast.LENGTH_LONG).show()
                cargar()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error al borrar proveedor $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("idproveedor",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
        //Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
    }
    fun addsbtn(view: View){
        val intent = Intent(this, Proveedor_Datos::class.java)
        startActivity(intent)
    }
}