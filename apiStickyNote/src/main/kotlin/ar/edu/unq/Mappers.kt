package ar.edu.unq

data class LoginUser(val email: String, val password: String)
data class RegisterUser(val email: String, val password: String, val name: String)
data class Note(val text: String, val color: String)