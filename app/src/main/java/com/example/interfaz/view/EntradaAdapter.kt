package com.example.interfaz.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.interfaz.R
import com.example.interfaz.model.EntradaData

class EntradaAdapter(val c: Context, val detalleList: ArrayList<EntradaData>):RecyclerView.Adapter<EntradaAdapter.EntradaViewHolder>()
{
        inner class EntradaViewHolder(val v: View):RecyclerView.ViewHolder(v) {
                var IDetalle: TextView
                var CantidadEntrada: TextView
                var Fingresoentrada: TextView
                var Fvencimientoentrada: TextView
                var IDproductoentrada: TextView
                var entradamenu: ImageView

                init {
                        IDetalle = v.findViewById<TextView>(R.id.TitleIdetalle)
                        CantidadEntrada = v.findViewById<TextView>(R.id.titlecantidadentrada)
                        Fingresoentrada = v.findViewById<TextView>(R.id.TitleFingreso)
                        Fvencimientoentrada = v.findViewById<TextView>(R.id.TitleFvencimiento)
                        IDproductoentrada = v.findViewById<TextView>(R.id.TitleidProEntrada)
                        entradamenu = v.findViewById(R.id.menuentrada)
                        entradamenu.setOnClickListener { popupMenu(it) }
                }
        }
        private fun popupMenu(v:View) {
                val popupMenu = PopupMenu(c,v)
                popupMenu.inflate(R.menu.menu)
                popupMenu.setOnMenuItemClickListener {
                        when(it.itemId){
                                R.id.editText->{

                                        Toast.makeText(c,"El boton Editar Texto fue presionado",
                                                Toast.LENGTH_SHORT).show()
                                        true
                                }
                                R.id.delete->{
                                        Toast.makeText(c,"El boton Borrar fue presionado", Toast.LENGTH_SHORT).show()
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

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntradaAdapter.EntradaViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val v = inflater.inflate(R.layout.list_itemproducto,parent,false)
                return EntradaViewHolder(v)
        }

        override fun onBindViewHolder(holder: EntradaAdapter.EntradaViewHolder, position: Int) {
                val newList = detalleList[position]
                holder.IDetalle.text = newList.iddetalle
                holder.CantidadEntrada.text = newList.cantidadEnt
                holder.Fingresoentrada.text = newList.FingresoEnt
                holder.Fvencimientoentrada.text = newList.FvencimientoEnt
                holder.IDproductoentrada.text = newList.idproductoEnt
        }

        override fun getItemCount(): Int {
                return detalleList.size
        }
}