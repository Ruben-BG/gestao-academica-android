package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.TypedValue
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.marginBottom
import androidx.core.view.setMargins

class TelaCadastro : AppCompatActivity(), OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var botaoVoltar: ImageView
    private lateinit var spinnerUsuario: Spinner
    private lateinit var viewFormulario: View
    private lateinit var campoNome: EditText
    private lateinit var campoCPF: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEndereco: EditText
    private lateinit var campoEmail: EditText
    private lateinit var campoSenha: EditText
    private lateinit var campoNomeUsuario: EditText
    private lateinit var botaoCriarConta: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        spinnerUsuario = findViewById(R.id.spinnerUsuarioTelaCadastro)
        val adapter = ArrayAdapter.createFromResource(this, R.array.tipos_usuario, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUsuario.adapter = adapter

        botaoVoltar = findViewById(R.id.botaoVoltarTelaCadastro)
        viewFormulario = findViewById(R.id.viewFormularioTelaCadastro)
        campoNome = findViewById(R.id.campoNomeTelaCadastro)
        campoCPF = findViewById(R.id.campoCPFTelaCadastro)
        campoTelefone = findViewById(R.id.campoTelefoneTelaCadastro)
        campoEndereco = findViewById(R.id.campoEnderecoTelaCadastro)
        campoEmail = findViewById(R.id.campoEmailTelaCadastro)
        campoSenha = findViewById(R.id.campoSenhaTelaCadastro)
        campoNomeUsuario = findViewById(R.id.campoNomeUsuarioTelaCadastro)
        botaoCriarConta = findViewById(R.id.botaoCriarContaTelaCadastro)

        botaoVoltar.setOnClickListener(this)
        botaoCriarConta.setOnClickListener(this)
        spinnerUsuario.onItemSelectedListener = this

    }

    override fun onClick(v: View?) {

        if (v!!.id == R.id.botaoVoltarTelaCadastro) {

            onBackPressed()

        } else if (v.id == R.id.botaoCriarContaTelaCadastro) {

            Toast.makeText(this, "Botão criar conta selecionado.", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val tipoDeUsuario = spinnerUsuario.selectedItem.toString()

        if (tipoDeUsuario == "Aluno" || tipoDeUsuario == "Professor") {

            viewFormulario.layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 425F, resources.displayMetrics).toInt()
            campoNomeUsuario.visibility = View.INVISIBLE

        } else {

            viewFormulario.layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480F, resources.displayMetrics).toInt()
            campoNomeUsuario.visibility = View.VISIBLE

        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}