package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class TelaCadastro : AppCompatActivity(), OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var botaoVoltar: ImageView
    private lateinit var spinnerUsuario: Spinner
    private lateinit var tipoDeUsuario: String
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

            if (verificarValoresPreenchidos()) {
                Toast.makeText(this, "Testes realizados deram true.", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        tipoDeUsuario = spinnerUsuario.selectedItem.toString()

        if (tipoDeUsuario == "Aluno" || tipoDeUsuario == "Professor") {

            val params = viewFormulario.layoutParams
            params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 425F, resources.displayMetrics).toInt()
            viewFormulario.layoutParams = params
            campoNomeUsuario.visibility = View.INVISIBLE

        } else {

            val params = viewFormulario.layoutParams
            params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480F, resources.displayMetrics).toInt()
            viewFormulario.layoutParams = params
            campoNomeUsuario.visibility = View.VISIBLE

        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun verificarValoresPreenchidos(): Boolean {

        if (this::tipoDeUsuario.isInitialized) {

            val provedoresDeEmail = listOf("@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com")
            val validaEmail = campoEmail.text.contains(provedoresDeEmail[0]) || campoEmail.text.contains(provedoresDeEmail[1])
                    || campoEmail.text.contains(provedoresDeEmail[2]) || campoEmail.text.contains(provedoresDeEmail[3])

            campoNome.text.forEach {
                if (it.isDigit()) {
                Toast.makeText(this, "O campo de nome deve conter apenas letras.", Toast.LENGTH_SHORT).show()
                return false
                }
            }

            if (campoNome.text.isEmpty()) {
                Toast.makeText(this, "É obrigatório o preenchimento do campo de nome.", Toast.LENGTH_SHORT).show()
                campoNome.requestFocus()
                return false
            }
            else if (campoCPF.text.isEmpty()) {
                Toast.makeText(this, "É obrigatório o preenchimento do campo de CPF.", Toast.LENGTH_SHORT).show()
                campoCPF.requestFocus()
                return false
            }
            else if (!campoCPF.text.isDigitsOnly()) {
                Toast.makeText(this, "CPF deve conter apenas números.", Toast.LENGTH_SHORT).show()
                campoCPF.requestFocus()
                return false
            }
            else if (campoCPF.text.length != 11) {
                Toast.makeText(this, "CPF deve conter apenas 11 caracteres.", Toast.LENGTH_SHORT).show()
                campoCPF.requestFocus()
                return false
            }
            else if (campoEmail.text.isEmpty()) {
                Toast.makeText(this, "É obrigatório o preenchimento do campo de E-mail.", Toast.LENGTH_SHORT).show()
                campoEmail.requestFocus()
                return false
            }
            else if (!validaEmail) {
                Toast.makeText(this, "Coloque um provedor de e-mail válido.", Toast.LENGTH_SHORT).show()
                campoEmail.requestFocus()
                return false
            }
            else if (campoSenha.text.isEmpty()) {
                Toast.makeText(this, "É obrigatório o preenchimento do campo de senha.", Toast.LENGTH_SHORT).show()
                campoSenha.requestFocus()
                return false
            }
            else if (campoSenha.text.length < 8) {
                Toast.makeText(this, "Campo de senha deve conter pelo menos 8 caracteres.", Toast.LENGTH_SHORT).show()
                campoSenha.requestFocus()
                return false
            }

        }

        return true

    }

}