package com.chavez.yahaira.laboratoriocalificado03

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chavez.yahaira.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    var list: List<TeacherResponse>,
    private val onDialClick: (String) -> Unit,
    private val onEmailLongClick: (String) -> Unit
): RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemTeacherBinding.bind(view)

        fun bind(teacher: TeacherResponse) {
            binding.fullnameText.text = teacher.name + " " + teacher.last_name
            binding.emailText.text = teacher.email
            Glide.with(itemView).load(teacher.imageUrl).into(binding.profileImage)

            binding.root.setOnClickListener {
                onDialClick(teacher.phone)
            }

            binding.root.setOnLongClickListener {
                onEmailLongClick(teacher.email)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemTeacher = list[position]
        holder.bind(itemTeacher)
    }

    override fun getItemCount(): Int = list.size
}