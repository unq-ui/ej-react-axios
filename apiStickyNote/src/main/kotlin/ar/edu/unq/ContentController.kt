package ar.edu.unq

import ar.edu.unq.model.App
import ar.edu.unq.model.NotFound
import ar.edu.unq.model.StickyNote
import ar.edu.unq.model.User
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import io.javalin.http.UnauthorizedResponse

class ContentController(val app: App) {

    fun getAll(ctx: Context) {
        val user = authHeader(ctx)
        ctx.json(user.notes)
    }

    fun deleteNote(ctx: Context) {
        val user = authHeader(ctx)
        val noteId = ctx.pathParam("id")
        user.deleteNote(noteId)
        ctx.json(user.notes)
    }

    fun updateNote(ctx: Context) {
        val user = authHeader(ctx)
        val noteId = ctx.pathParam("id")
        try {
            val noteBody = ctx.bodyAsClass(Note::class.java)
            val note = StickyNote(noteId, noteBody.text, noteBody.color)
            user.updateNote(note)
            ctx.json(user.notes)
        } catch (e: NotFound) {
            throw NotFoundResponse(e.message!!)
        }
    }

    fun createNote(ctx: Context) {
        val user = authHeader(ctx)
        val noteBody = ctx.bodyAsClass(Note::class.java)
        var id = 1
        if (!user.notes.isEmpty()) id += user.notes.last().id.toInt()
        val note = StickyNote("$id", noteBody.text, noteBody.color)
        user.addNote(note)
        ctx.json(user.notes)
    }

    private fun authHeader(ctx: Context): User {
        val token = ctx.header("Authorization")
        if(token != null) {
            return app.users.find { it.id == token } ?: throw UnauthorizedResponse("Invalid token")
        }
        throw UnauthorizedResponse("Token not found")
    }
}