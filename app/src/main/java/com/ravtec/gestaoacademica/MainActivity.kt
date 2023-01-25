package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.view.isVisible
import com.ravtec.gestaoacademica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var campoMatricula: EditText
    private lateinit var campoNomeUsuario: EditText
    private lateinit var campoSenha: EditText
    private val dbHelper = UsuariosDB(this)

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        campoMatricula = binding.campoMatricula
        campoNomeUsuario = binding.campoNomeUsuario
        campoSenha = binding.campoSenha
        binding.botaoProfessorAluno.setOnClickListener(this)
        binding.botaoCoordenador.setOnClickListener(this)
        binding.botaoEntrar.setOnClickListener(this)
        binding.botaoCadastro.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        if (view!!.id == R.id.botaoCadastro) {

            val navegarTelaCadastro = Intent(this, TelaCadastro::class.java)
            startActivity(navegarTelaCadastro)

        } else if (view.id == R.id.botaoEntrar) {

            if (campoNomeUsuario.isVisible) {

                if (verificarCampos()) {

                    if (dbHelper.autenticarUsuarioCoordenador(campoNomeUsuario.text.toString(), campoSenha.text.toString())) {
                        val navegarDashboardCoordenador = Intent(this, DashboardCoordenador::class.java)
                        navegarDashboardCoordenador.putExtra("nomeUsuario", campoNomeUsuario.text.toString())
                        startActivity(navegarDashboardCoordenador)
                    } else Toast.makeText(this, "Dados inseridos inválidos", Toast.LENGTH_SHORT).show()

                }

            }
            else if (campoMatricula.isVisible) {

                if (verificarCampos()) {

                    if (dbHelper.autenticarUsuarioAlunoProfessor(campoMatricula.text.toString().toInt(), campoSenha.text.toString())) {
                        Toast.makeText(this, "Dados inseridos inválidos", Toast.LENGTH_SHORT).show()
                    } else Toast.makeText(this, "Dados inseridos inválidos", Toast.LENGTH_SHORT).show()

                }

            }

        } else if (view.id == R.id.botaoProfessorAluno) {

            campoMatricula.visibility = View.VISIBLE
            campoNomeUsuario.visibility = View.INVISIBLE
            apagarCampos()

        } else {

            campoMatricula.visibility = View.INVISIBLE
            campoNomeUsuario.visibility = View.VISIBLE
            apagarCampos()

        }

    }

    private fun verificarCampos(): Boolean {

        if (campoMatricula.isVisible) {

            if (campoMatricula.text.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o campo de matrícula", Toast.LENGTH_SHORT).show()
                campoMatricula.requestFocus()
                return false
            }

        } else {

            if (campoNomeUsuario.text.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o campo de nome de usuário", Toast.LENGTH_SHORT).show()
                campoNomeUsuario.requestFocus()
                return false
            }

        }

        if (campoSenha.text.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o campo de senha", Toast.LENGTH_SHORT).show()
            campoSenha.requestFocus()
            return false
        }

        return true

    }

    fun apagarCampos() {
        campoMatricula.text.clear()
        campoNomeUsuario.text.clear()
        campoSenha.text.clear()
    }

}