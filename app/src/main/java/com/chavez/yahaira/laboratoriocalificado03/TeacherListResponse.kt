package com.chavez.yahaira.laboratoriocalificado03

data class TeacherListResponse(
    val count: Int,
    val next: String,
    val results: List<TeacherResponse>
)
