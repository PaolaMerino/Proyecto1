package com.example.interfaz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interfaz.model.EntradaData
import com.example.interfaz.view.EntradaAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActentrada : AppCompatActivity() {
    private lateinit var addsBtn4: FloatingActionButton
    private lateinit var recventrada: RecyclerView
    private lateinit var entradaList:ArrayList<EntradaData>
    private lateinit var entradaAdapter: EntradaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_actentrada)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Entrada de productos"

        /**set list*/
        entradaList = ArrayList()
        /**set find id*/
        addsBtn4 = findViewById(R.id.addingBtn4)
        recventrada = findViewById(R.id.recyclerviewentrada)
        /**set adapter*/
        entradaAdapter = EntradaAdapter(this,entradaList)
        /**set recyclerview adapter*/
        recventrada.layoutManager = LinearLayoutManager(this)
        recventrada.adapter = entradaAdapter
        /**set dialog*/
        addsBtn4.setOnClickListener{addInfoProduct()}
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addInfoProduct() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.activity_entrada_datos,null)
        /**set view*/
        val idetalle = v.findViewById<EditText>(R.id.idetalle)
        val cantidad = v.findViewById<EditText>(R.id.Cantidadentrada)
        val Fingreso = v.findViewById<EditText>(R.id.Fingresoentrada)
        val Fvencimiento = v.findViewById<EditText>(R.id.Fvencimientoentrada)
        val idproducto = v.findViewById<EditText>(R.id.idproductoentrada)
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setView(v)
        addDialog.setPositiveButton("Agregar"){
                dialog,_->
                val IDetalle= idetalle.text.toString()
                val Cantidadentr = cantidad.text.toString()
                val Fingresoentr = Fingreso.text.toString()
                val Fvencimientoentr = Fvencimiento.text.toString()
                val idproductentr = idproducto.text.toString()
                entradaList.add(EntradaData("ID Detalle: $IDetalle","Cantidad: $Cantidadentr","Fecha Ing.: $Fingresoentr", "Fecha Venc. $Fvencimientoentr", "ID Producto: $idproductentr"))
                entradaAdapter.notifyDataSetChanged()
                Toast.makeText(this,"Agregar", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            addDialog.setNegativeButton("Cancelar"){
                    dialog,_->
                dialog.dismiss()
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show()
            }
            addDialog.create()
            addDialog.show()
    }
}