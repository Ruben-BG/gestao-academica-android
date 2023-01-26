package com.ravtec.gestaoacademica

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import androidx.core.view.isVisible
import com.ravtec.gestaoacademica.databinding.ActivityTabelaProfessoresDcBinding

class TabelaProfessoresDC : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityTabelaProfessoresDcBinding
    private val dbHelper = UsuariosDB(this)
    private lateinit var campoNome: EditText
    private lateinit var campoCodigo: EditText
    private lateinit var campoDisciplina: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabela_professores_dc)

        binding = ActivityTabelaProfessoresDcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        campoNome = binding.campoNomeTabelaProfessores
        campoCodigo = binding.campoCodigoTabelaProfessores
        campoDisciplina = binding.campoDisciplinaTabelaProfessores

        binding.buttonVoltarTabelaProfessores.setOnClickListener(this)
        binding.cardFiltragemTabelaProfessores.setOnClickListener(this)
        binding.cardFiltragemTabelaProfessores.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.buttonPesquisarTabelaProfessores.setOnClickListener(this)
        binding.buttonLimparTabelaProfessores.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (v!!.id == R.id.buttonVoltarTabelaProfessores) {
            onBackPressedDispatcher.onBackPressed()
        }
        else if (v.id == R.id.cardFiltragemTabelaProfessores) {

            val visibilidade = if (!campoNome.isVisible) View.VISIBLE else View.GONE

            if (!campoNome.isVisible) TransitionManager.beginDelayedTransition(binding.cardFiltragemTabelaProfessores)

            campoNome.visibility = visibilidade
            campoCodigo.visibility = visibilidade
            campoDisciplina.visibility = visibilidade
            binding.buttonPesquisarTabelaProfessores.visibility = visibilidade
            binding.buttonLimparTabelaProfessores.visibility = visibilidade

            if (campoNome.isVisible) TransitionManager.beginDelayedTransition(binding.cardFiltragemTabelaProfessores)

        }
        else if (v.id == R.id.buttonPesquisarTabelaProfessores) {

        }
        else if (v.id == R.id.buttonLimparTabelaProfessores) {

        }

    }
}