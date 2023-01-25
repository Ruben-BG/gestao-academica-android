package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.ravtec.gestaoacademica.databinding.ActivityDashboardCoordenadorBinding

class DashboardCoordenador : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityDashboardCoordenadorBinding
    private val dbHelper = UsuariosDB(this)
    private lateinit var nomeDoUsuario: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_coordenador)

        binding = ActivityDashboardCoordenadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("nomeUsuario")) {
            nomeDoUsuario = intent.extras!!.getString("nomeUsuario").toString()
        }

        if (this::nomeDoUsuario.isInitialized) {
            binding.textoInformesDashboardCoordenador.text = "${dbHelper.pegarUsuarioCoordenador(nomeDoUsuario).nome} | Coordenador"
        }

        binding.buttonVoltarDashboardCoordenador.setOnClickListener(this)
        binding.imageProfessoresDashboardCoordenador.setOnClickListener(this)
        binding.imageTurmasDashboardCoordenador.setOnClickListener(this)
        binding.imageAlunosDashboardCoordenador.setOnClickListener(this)
        binding.imageSolicitacoesDashboardCoordenador.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (v!!.id == R.id.buttonVoltarDashboardCoordenador) {
            onBackPressedDispatcher.onBackPressed()
        }
        else if (v.id == R.id.imageProfessoresDashboardCoordenador) {

            val navegarTabelaProfessores = Intent(this, TabelaProfessoresDC::class.java)
            if (this::nomeDoUsuario.isInitialized) navegarTabelaProfessores.putExtra("nomeUsuario", nomeDoUsuario)
            startActivity(navegarTabelaProfessores)

        }
        else if (v.id == R.id.imageTurmasDashboardCoordenador) {

            val navegarTabelaTurmas = Intent(this, TabelaTurmasDC::class.java)
            if (this::nomeDoUsuario.isInitialized) navegarTabelaTurmas.putExtra("nomeUsuario", nomeDoUsuario)
            startActivity(navegarTabelaTurmas)

        }
        else if (v.id == R.id.imageAlunosDashboardCoordenador) {

            val navegarTabelaAlunos = Intent(this, TabelaAlunosDC::class.java)
            if (this::nomeDoUsuario.isInitialized) navegarTabelaAlunos.putExtra("nomeUsuario", nomeDoUsuario)
            startActivity(navegarTabelaAlunos)

        }
        else if (v.id == R.id.imageSolicitacoesDashboardCoordenador) {

            val navegarTabelaSolicitacoes = Intent(this, TabelaSolicitacoesDC::class.java)
            if (this::nomeDoUsuario.isInitialized) navegarTabelaSolicitacoes.putExtra("nomeUsuario", nomeDoUsuario)
            startActivity(navegarTabelaSolicitacoes)

        }

    }

}