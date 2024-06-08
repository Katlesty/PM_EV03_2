package com.chavez.yahaira.laboratoriocalificado03

import retrofit2.Response
import retrofit2.http.GET

interface TeacherAPI {
    @GET("/list/teacher")
    suspend fun getTeachers(): Response<TeacherListResponse>
}