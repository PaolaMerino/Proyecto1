package com.example.interfaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card_cliente.setOnClickListener{
            val intent = Intent(this,MainAct_Clientes::class.java)
            startActivity(intent);
        }
        card_proveedor.setOnClickListener{
            val intent = Intent(this,MainActproveedores::class.java)
            startActivity(intent);
        }
        card_producto.setOnClickListener{
            val intent = Intent(this,MainActiproducto::class.java)
            startActivity(intent);
        }
        card_editar.setOnClickListener {
            val intent = Intent(this,MainActeditar::class.java)
            startActivity(intent);
        }
        card_reporte.setOnClickListener {
            val intent = Intent(this,MainActreporte::class.java)
            startActivity(intent);
        }
        card_editar.setOnClickListener {
            val intent = Intent(this,MainActbuscar::class.java)
            startActivity(intent);
        }

    }

}

