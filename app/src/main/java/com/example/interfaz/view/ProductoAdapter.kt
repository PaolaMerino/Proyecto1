package com.example.interfaz.view

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.interfaz.R
import com.example.interfaz.model.ProductoData

class ProductoAdapter(val c: Context, val productoList: ArrayList<ProductoData>): RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>()
{
    inner class ProductoViewHolder(val v: View):RecyclerView.ViewHolder(v){
        var nomProducto:TextView
        var idProducto:TextView
        var existenciaProducto:TextView
        var proveedorProducto:TextView
        var categoriaProducto:TextView
        var productomenu:ImageView

        init{
            nomProducto = v.findViewById<TextView>(R.id.TitleIdproducto)
            idProducto = v.findViewById<TextView>(R.id.titlenombreproducto)
            existenciaProducto = v.findViewById<TextView>(R.id.Titlexistenciaproducto)
            proveedorProducto = v.findViewById<TextView>(R.id.Titleproveedorproducto)
            categoriaProducto = v.findViewById<TextView>(R.id.Titlecategoriaproducto)
            productomenu = v.findViewById(R.id.menuproducto)
            productomenu.setOnClickListener{popupMenu(it)}
        }
    }

    private fun popupMenu(v:View){
        val popupMenu = PopupMenu(c,v)
        popupMenu.inflate(R.menu.menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.editText->{

                    Toast.makeText(c,"El boton Editar Texto fue presionado",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.delete->{
                    Toast.makeText(c,"El boton Borrar fue presionado",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> true
            }
        }
        popupMenu.show()
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenu)
        menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
            .invoke(menu,true)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_itemproducto,parent,false)
        return ProductoViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val newList = productoList[position]
        holder.nomProducto.text = newList.NomProducto
        holder.idProducto.text = newList.idProducto
        holder.existenciaProducto.text = newList.existencias
        holder.proveedorProducto.text = newList.proveedor
        holder.categoriaProducto.text = newList.categoria
    }

    override fun getItemCount(): Int {
        return productoList.size
    }
}