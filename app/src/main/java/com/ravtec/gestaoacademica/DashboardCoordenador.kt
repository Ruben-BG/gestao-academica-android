package com.ravtec.gestaoacademica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.ravtec.gestaoacademica.databinding.ActivityDashboardCoordenadorBinding

class DashboardCoordenador : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityDashboardCoordenadorBinding
    private val dbHelper = UsuariosDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_coordenador)

        binding = ActivityDashboardCoordenadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}