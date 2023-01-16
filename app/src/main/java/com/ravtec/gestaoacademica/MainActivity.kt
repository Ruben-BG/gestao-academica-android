package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ravtec.gestaoacademica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val dbHelper = UsuariosDB(this)

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val db = dbHelper.writableDatabase

        val valores = ContentValues().apply {
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_NOME, "Ronaldo Júnior")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_NOMEUSUARIO, "RonaldoC")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_CPF, "12312312312")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_EMAIL, "ronaldoJun@hotmail.com")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_SENHA, "12345678")
            put(UsuariosDB.UsuariosEntry.COLUMN_NAME_TELEFONE, "1111")
        }
        db?.insert(UsuariosDB.UsuariosEntry.TABLE_NAME, null, valores)

        binding.botaoProfessorAluno.setOnClickListener(this)
        binding.botaoCoordenador.setOnClickListener(this)
        binding.botaoEntrar.setOnClickListener(this)
        binding.botaoCadastro.setOnClickListener {

        }

    }

    override fun onClick(view: View?) {
        if (view!!.id == R.id.botaoCadastro) {
            val navegarTelaCadastro = Intent(this, TelaCadastro::class.java)
            startActivity(navegarTelaCadastro)
        } else if (view.id == R.id.botaoEntrar) {
            Toast.makeText(this, "lllllllllllll", Toast.LENGTH_SHORT).show()
        }
    }
}