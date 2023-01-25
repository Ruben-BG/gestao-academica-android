package com.ravtec.gestaoacademica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.ravtec.gestaoacademica.databinding.ActivityTabelaAlunosDcBinding

class TabelaAlunosDC : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityTabelaAlunosDcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabela_alunos_dc)

        binding = ActivityTabelaAlunosDcBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}