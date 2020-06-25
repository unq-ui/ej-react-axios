package ar.edu.unq

import ar.edu.unq.model.getApp
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main() {
    val app = getApp()

    val userController = UserController(app)
    val contentController = ContentController(app)

    val javalinApp = Javalin.create {
        it.defaultContentType = "application/json"
        it.enableCorsForAllOrigins()
    }.start(7000)

    javalinApp.routes {
        path("/login") {
            post(userController::login)
        }
        path("/register") {
            post(userController::register)
            }
        path("/notes") {
            get(contentController::getAll)
            path("/:id") {
                delete(contentController::deleteNote)
                put(contentController::updateNote)
            }
            post(contentController::createNote)
        }
    }
}