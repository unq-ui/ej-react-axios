package ar.edu.unq

import ar.edu.unq.model.*
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse

class UserController(val app: App) {

    fun login(ctx: Context) {
        val loginUser = ctx.bodyAsClass(LoginUser::class.java)
        try {
            val user = app.login(loginUser.email, loginUser.password)
            ctx.header("Authorization", user.id)
            ctx.json(user)
        } catch (e: NotFound) {
            throw NotFoundResponse("Wrong email or password")
        }
    }

    fun register(ctx: Context) {
        val registerUser = ctx.bodyAsClass(RegisterUser::class.java)
        try {
            app.checkEmail(registerUser.email)
            val user = User("${app.users.size + 1}", registerUser.name, registerUser.email, registerUser.password, mutableListOf<StickyNote>())
            app.users.add(user)
            ctx.header("Authorization", user.id)
            ctx.json(user)
        } catch (e: EmailUsed) {
            throw NotFoundResponse("Email used")
        }
    }

}
