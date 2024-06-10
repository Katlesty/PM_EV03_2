package com.chavez.yahaira.laboratoriocalificado03

data class TeacherResponse(
    val name: String,
    val last_name: String,
    val phone: String,
    val email: String,
    val imageUrl: String
){

    private fun getInitialFirstName(): String = name.split(" ")[0].substring(0, 1).lowercase()

    private fun getFirstLastname(): String = last_name.split(" ")[0].lowercase()

    fun getTeacherImage(): String {
        val initialFirstName = getInitialFirstName()
        val firstLastname = getFirstLastname()
        return "https://raw.githubusercontent.com/victorskatepro/ContactList/master/app/src/main/res/drawable/${initialFirstName}${firstLastname}.jpeg"
    }
}
