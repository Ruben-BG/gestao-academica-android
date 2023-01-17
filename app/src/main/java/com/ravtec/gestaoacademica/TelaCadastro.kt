package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.view.get

class TelaCadastro : AppCompatActivity(), OnClickListener {

    private lateinit var spinnerUsuario: Spinner
    //private lateinit var itemSelecionado: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        spinnerUsuario = findViewById(R.id.spinnerUsuarioTelaCadastro)
        val adapter = ArrayAdapter.createFromResource(this, R.array.tipos_usuario, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUsuario.adapter = adapter
        //spinnerUsuario.setOnClickListener(this)

        /*if (itemSelecionado.equals("Coordenador")) {
            findViewById<EditText>(R.id.campoNomeUsuarioTelaCadastro).visibility = View.VISIBLE
        } else findViewById<EditText>(R.id.campoNomeUsuarioTelaCadastro).visibility = View.INVISIBLE*/

    }

    override fun onClick(v: View?) {

    }

}