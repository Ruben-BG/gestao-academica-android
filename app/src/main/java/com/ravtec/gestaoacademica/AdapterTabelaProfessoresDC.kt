package com.ravtec.gestaoacademica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ravtec.gestaoacademica.databinding.ActivityTabelaSolicitacoesDcBinding
import com.ravtec.gestaoacademica.databinding.ItemTabelaProfessoresDcBinding

class AdapterTabelaProfessoresDC(private val myList: List<UsuarioProfessor>): Adapter<AdapterTabelaProfessoresDC.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tabela_professores_dc, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textoNome = itemView.findViewById<TextView>(R.id.textoNomeProfessorItem)
        val textoQuantidadeTurma = itemView.findViewById<TextView>(R.id.textoTurmasItem)
    }

}