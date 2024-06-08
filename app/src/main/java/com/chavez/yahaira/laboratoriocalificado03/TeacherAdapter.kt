package com.chavez.yahaira.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chavez.yahaira.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    var list: List<TeacherResponse>
): RecyclerView.Adapter<TeacherAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemTeacherBinding.bind(view)

        fun bind (teacher: TeacherResponse) {
            binding.fullnameText.text = teacher.name + teacher.lastname
            binding.emailText.text = teacher.correo
            Glide.with(itemView).load(teacher.getTeacherImage()).into(binding.profileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemTeacher = list[position]
        holder.bind(itemTeacher)
    }

    override fun getItemCount(): Int = list.size

}