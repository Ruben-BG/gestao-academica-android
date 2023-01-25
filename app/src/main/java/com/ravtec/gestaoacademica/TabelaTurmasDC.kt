package com.ravtec.gestaoacademica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.ravtec.gestaoacademica.databinding.ActivityTabelaTurmasDcBinding

class TabelaTurmasDC : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityTabelaTurmasDcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabela_turmas_dc)

        binding = ActivityTabelaTurmasDcBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}