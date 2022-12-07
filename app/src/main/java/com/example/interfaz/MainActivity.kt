package com.example.interfaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title="Principal"

        card_usuarios.setOnClickListener{
            val intent = Intent(this,MainAct_Usuarios::class.java)
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
        card_categoria.setOnClickListener {
            val intent = Intent(this,MainActcategoria::class.java)
            startActivity(intent);
        }
        card_entrada.setOnClickListener {
            val intent = Intent(this,MainActentrada::class.java)
            startActivity(intent);
        }
        card_salida.setOnClickListener {
            val intent = Intent(this,MainActsalida::class.java)
            startActivity(intent);
        }

    }

}

