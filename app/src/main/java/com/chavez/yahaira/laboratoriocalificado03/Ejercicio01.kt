package com.chavez.yahaira.laboratoriocalificado03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chavez.yahaira.laboratoriocalificado03.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio01 : AppCompatActivity() {

    private var listTeacher: List<TeacherResponse> = emptyList()

    private val adapter by lazy { TeacherAdapter(listTeacher)}

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        getAllTeachers()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllTeachers() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = getRetrofit().create(TeacherAPI::class.java).getTeachers()
            if (request.isSuccessful) {
                request.body()?.let {
                    runOnUiThread {
                        adapter.list = it.results
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}