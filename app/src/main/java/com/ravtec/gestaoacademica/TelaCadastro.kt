package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.ContentValues
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
import androidx.core.view.isVisible
import com.ravtec.gestaoacademica.databinding.ActivityMainBinding
import com.ravtec.gestaoacademica.databinding.ActivityTelaCadastroBinding

class TelaCadastro : AppCompatActivity(), OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityTelaCadastroBinding
    private val dbHelper = UsuariosDB(this)
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinnerUsuario = binding.spinnerUsuarioTelaCadastro
        val adapter = ArrayAdapter.createFromResource(this, R.array.tipos_usuario, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUsuario.adapter = adapter

        viewFormulario = binding.viewFormularioTelaCadastro
        campoNome = binding.campoNomeTelaCadastro
        campoCPF = binding.campoCPFTelaCadastro
        campoTelefone = binding.campoTelefoneTelaCadastro
        campoEndereco = binding.campoEnderecoTelaCadastro
        campoEmail = binding.campoEmailTelaCadastro
        campoSenha = binding.campoSenhaTelaCadastro
        campoNomeUsuario = binding.campoNomeUsuarioTelaCadastro

        binding.botaoVoltarTelaCadastro.setOnClickListener(this)
        binding.botaoCriarContaTelaCadastro.setOnClickListener(this)
        spinnerUsuario.onItemSelectedListener = this

    }

    override fun onClick(v: View?) {

        if (v!!.id == R.id.botaoVoltarTelaCadastro) {

            onBackPressedDispatcher.onBackPressed()

        } else if (v.id == R.id.botaoCriarContaTelaCadastro) {

            if (verificarValoresPreenchidos()) {

                if (this::tipoDeUsuario.isInitialized) {

                    val nome = campoNome.text.toString()
                    val cpf = campoCPF.text.toString()
                    val email = campoEmail.text.toString()
                    val senha = campoSenha.text.toString()
                    val telefone = campoTelefone.text.toString()
                    val endereco = campoEndereco.text.toString()

                    if (tipoDeUsuario == "Coordenador") {

                        val nomeDeUsuario = campoNomeUsuario.text.toString()
                        val novoCoordendor = UsuarioCoordenador(nome, cpf, email, senha, telefone, endereco, nomeDeUsuario)
                        dbHelper.adicionarCoordenador(novoCoordendor)

                    } else {

                        val novoUsuario = Usuario(nome, cpf, email, senha, telefone, endereco)
                        dbHelper.adicionarProfessorAluno(novoUsuario)

                    }

                    Toast.makeText(this, "Conta criada com sucesso!!!", Toast.LENGTH_SHORT).show()

                }

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
            else if (dbHelper.verificarEmailExistente(campoEmail.text.toString())) {
                Toast.makeText(this, "E-mail já cadastrado.", Toast.LENGTH_SHORT).show()
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
            } else if (tipoDeUsuario == "Professor" && !campoEmail.text.contains("@prof.ce.gov.br")) {
                Toast.makeText(this, "Coloque o provedor de e-mail correto.", Toast.LENGTH_SHORT).show()
                campoEmail.requestFocus()
                return false
            }

            if (campoNomeUsuario.isVisible) {

                if (campoNomeUsuario.text.isEmpty()) {
                    Toast.makeText(this, "É obrigatório o preenchimento do campo de nome de usuário.", Toast.LENGTH_SHORT).show()
                    campoNomeUsuario.requestFocus()
                    return false
                } else if (dbHelper.verificarNomeDeUsuarioExistente(campoNomeUsuario.text.toString())) {
                    Toast.makeText(this, "Nome de usuário já existente na plataforma.", Toast.LENGTH_SHORT).show()
                    campoNomeUsuario.requestFocus()
                    return false
                }

            }

        }

        return true

    }

}