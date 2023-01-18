package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.ravtec.gestaoacademica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var radioButtonProfessorAluno: RadioButton
    private lateinit var radioButtonCoordenador: RadioButton
    private lateinit var campoMatricula: EditText
    private lateinit var campoSenha: EditText
    private lateinit var botaoEntrar: Button
    private lateinit var botaoCadastrar: Button
    private val dbHelper = UsuariosDB(this)

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = dbHelper.writableDatabase

        val valores = ContentValues().apply {
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_NOME, "Ronaldo Júnior")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_NOMEUSUARIO, "RonaldoC")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_CPF, "12312312312")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_EMAIL, "ronaldoJun@hotmail.com")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_SENHA, "12345678")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_TELEFONE, "1111")
        }
        db!!.insert(UsuariosDB.UsuariosEntry.TABLE_NAME, null, valores)

        radioButtonProfessorAluno = findViewById(R.id.botaoProfessorAluno)
        radioButtonCoordenador = findViewById(R.id.botaoCoordenador)
        campoMatricula = findViewById(R.id.campoMatriculaNome)
        campoSenha = findViewById(R.id.campoSenha)
        botaoEntrar = findViewById(R.id.botaoEntrar)
        botaoCadastrar = findViewById(R.id.botaoCadastro)

        radioButtonProfessorAluno.setOnClickListener(this)
        radioButtonCoordenador.setOnClickListener(this)
        botaoEntrar.setOnClickListener(this)
        botaoCadastrar.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        if (view!!.id == R.id.botaoCadastro) {

            val navegarTelaCadastro = Intent(this, TelaCadastro::class.java)
            startActivity(navegarTelaCadastro)

        } else if (view.id == R.id.botaoEntrar) {

            Toast.makeText(this, "Botão entrar foi pressionado.", Toast.LENGTH_SHORT).show()

        }

    }

}