package com.ravtec.gestaoacademica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.ravtec.gestaoacademica.databinding.ActivityTabelaProfessoresDcBinding

class TabelaProfessoresDC : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityTabelaProfessoresDcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabela_professores_dc)

        binding = ActivityTabelaProfessoresDcBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}