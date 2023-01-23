package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.ravtec.gestaoacademica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var campoMatricula: EditText
    private lateinit var campoSenha: EditText
    private val dbHelper = UsuariosDB(this)

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val novoCoordenador = UsuarioCoordenador("Ronaldo Júnior", "12312312312", "ronaldoJun@hotmail.com", "12345678", "1111", "", "RonaldoC")
        dbHelper.adicionarCoordenador(novoCoordenador)

        campoMatricula = binding.campoMatriculaNome
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

            Toast.makeText(this, "Botão entrar foi pressionado.", Toast.LENGTH_SHORT).show()

        } else if (view.id == R.id.botaoProfessorAluno) {

            campoMatricula.hint = "Digite sua matrícula"

        } else {

            campoMatricula.hint = "Digite seu nome de usuário"

        }

    }

}