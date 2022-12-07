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
import com.example.interfaz.model.ProductoData
import com.example.interfaz.view.ProductoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActiproducto : AppCompatActivity() {
    private lateinit var addsBtn3:FloatingActionButton
    private lateinit var recvproducto:RecyclerView
    private lateinit var productList:ArrayList<ProductoData>
    private lateinit var productAdapter:ProductoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_actiproducto)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Producto"

        /**set list*/
        productList = ArrayList()
        /**set find id*/
        addsBtn3 = findViewById(R.id.addingBtn3)
        recvproducto = findViewById(R.id.recyclerviewProducto)
        /**set adapter*/
        productAdapter = ProductoAdapter(this,productList)
        /**set recyclerview adapter*/
        recvproducto.layoutManager = LinearLayoutManager(this)
        recvproducto.adapter = productAdapter
        /**set dialog*/
        addsBtn3.setOnClickListener{addInfoProduct()}
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addInfoProduct() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.activity_productos_datos,null)
        /**set view*/
        val productname = v.findViewById<EditText>(R.id.Productonombre)
        val productID = v.findViewById<EditText>(R.id.Productoid)
        val existenciaP = v.findViewById<EditText>(R.id.Productoexistencia)
        val provProducto = v.findViewById<EditText>(R.id.Provproducto)
        val catProducto = v.findViewById<EditText>(R.id.Categoriaproducto)
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setView(v)
        addDialog.setPositiveButton("Agregar"){
                dialog,_->
            val nombreProduct = productname.text.toString()
            val IdProduct = productID.text.toString()
            val Pexistencia = existenciaP.text.toString()
            val proveedorProd = provProducto.text.toString()
            val Productocat = catProducto.text.toString()
            productList.add(ProductoData("Producto: $nombreProduct", "ID Producto: $IdProduct","Existencia: $Pexistencia","Proveedor: $proveedorProd","Categoria: $Productocat"))
            productAdapter.notifyDataSetChanged()
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