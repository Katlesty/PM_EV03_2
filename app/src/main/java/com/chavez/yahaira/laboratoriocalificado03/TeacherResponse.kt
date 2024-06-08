package com.chavez.yahaira.laboratoriocalificado03

data class TeacherResponse(
    val name: String,
    val lastname: String,
    val phone: String,
    val correo: String,
    val url: String
){

    private fun getInitialFirstName(): String = name.split(" ")[0].substring(0, 1).lowercase()

    private fun getFirstLastname(): String = lastname.split(" ")[0].lowercase()

    fun getTeacherImage(): String {
        val initialFirstName = getInitialFirstName()
        val firstLastname = getFirstLastname()
        return "https://raw.githubusercontent.com/victorskatepro/ContactList/master/app/src/main/res/drawable/${initialFirstName}${firstLastname}.jpeg"
    }
}
