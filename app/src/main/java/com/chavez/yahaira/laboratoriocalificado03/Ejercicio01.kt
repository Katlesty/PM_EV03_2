package com.chavez.yahaira.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chavez.yahaira.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio01 : AppCompatActivity() {

    private var listTeacher: List<TeacherResponse> = emptyList()

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TeacherAdapter(
            listTeacher,
            onDialClick = { phone ->
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phone")
                }
                startActivity(intent)
            },
            onEmailLongClick = { email ->
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:$email")
                }
                startActivity(intent)
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.recyclerView.adapter = adapter
        getAllTeachers(adapter)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllTeachers(adapter: TeacherAdapter) {
        CoroutineScope(Dispatchers.IO).launch {
            val request = getRetrofit().create(TeacherAPI::class.java).getTeachers()
            if (request.isSuccessful) {
                val responseBody = request.body()
                val teacherList = responseBody?.teachers
                if (teacherList != null) {
                    runOnUiThread {
                        adapter.list = teacherList
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}