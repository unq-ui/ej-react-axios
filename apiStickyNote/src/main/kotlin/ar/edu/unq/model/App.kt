package ar.edu.unq.model

class NotFound(msg: String) : Exception(msg)
class EmailUsed : Exception()

class User(var id: String, var name: String, var email: String, var password: String, val notes: MutableList<StickyNote>) {
    fun addNote(note: StickyNote) {
        this.notes.add(note)
    }

    fun updateNote(newNote: StickyNote) {
        val note = this.notes.find { it.id == newNote.id } ?: throw NotFound("note id not found")
        note.text = newNote.text
        note.color = newNote.color
    }

    fun deleteNote(id: String) {
        this.notes.removeIf { it.id == id }
    }
}

class StickyNote(var id: String, var text: String, var color: String)

class App(val users: MutableList<User>) {

    fun addNote(userId: String, note: StickyNote) {
        val user = findUser(userId)
        user.addNote(note)
    }

    fun updateNote(userId: String, newNote: StickyNote) {
        val user = findUser(userId)
        user.updateNote(newNote)
    }

    fun deleteNote(userId: String, noteId: String) {
        val user = findUser(userId)
        user.deleteNote(noteId)
    }

    fun login(email: String, password: String): User {
        return this.users.find { it.email == email && it.password == password} ?: throw NotFound("user not found")
    }

    fun checkEmail(email: String) {
        if (!this.users.all { it.email != email }) throw EmailUsed()
    }

    private fun  findUser(userId: String): User {
        return this.users.find { it.id == userId } ?: throw NotFound("user id not found")
    }
}

fun getApp() : App {
    val notes = mutableListOf(
        StickyNote("1", "Praesent consequat nibh et augue sollicitudin aliquam. In ultrices risus bibendum magna sagittis pretium. Etiam placerat auctor auctor. Morbi elit purus, cursus in ullamcorper et, feugiat sit amet velit. Praesent egestas purus quis tincidunt interdum.", "orange"),
        StickyNote("2", "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec vel elit porttitor, ultricies turpis at, egestas mi. Sed luctus, nisl vel venenatis ullamcorper, est odio suscipit ante, nec hendrerit orci est in purus.", "blue"),
        StickyNote("3", "Curabitur quis consequat tellus. Aenean lobortis molestie lobortis. Vestibulum nec suscipit diam, quis sodales lorem. Cras sodales, turpis quis pulvinar bibendum, lacus diam tincidunt tortor, ut finibus tellus odio eget lorem.", "orange"),
        StickyNote("4", "Nunc ut justo et sapien efficitur vehicula. Nunc in diam vitae mi iaculis ultrices. Phasellus et metus sit amet mi convallis tincidunt sit amet sed arcu.", "yellow"),
        StickyNote("5", "Proin faucibus urna et arcu eleifend ornare. Praesent augue metus, pretium ut ipsum dictum, consectetur mollis sapien.", "blue"),
        StickyNote("6", "Nulla tincidunt velit vel aliquet semper. Sed eros mauris, semper at purus et, elementum finibus est. Ut pulvinar, justo in iaculis auctor, risus est tristique dolor, tincidunt porta leo sapien eget ipsum.", "yellow"),
        StickyNote("7", "Donec vel massa sed ex condimentum tincidunt. Suspendisse convallis tincidunt justo, non auctor mi finibus in.", "green"),
        StickyNote("8", "Sed fermentum felis eget molestie vulputate. Morbi nec porta dolor. Nam pellentesque nec est eget vestibulum. Sed vel diam vel lorem cursus mollis. In at elit interdum erat dapibus elementum quis at ex.", "yellow"),
        StickyNote("9", "Phasellus sit amet aliquet augue, vitae laoreet nisi. In hac habitasse platea dictumst. Nulla non pharetra eros. Suspendisse orci nisi, pharetra vitae consectetur et, finibus nec nibh. Etiam semper imperdiet dui eget efficitur.", "orange"),
        StickyNote("10", "Curabitur gravida pulvinar ante, vel facilisis lorem porta in. Nam et ultrices massa. Quisque viverra, mi nec mollis finibus, massa libero sollicitudin felis, sit amet pretium justo metus a libero.", "grenn"),
        StickyNote("11", "Mauris purus augue, placerat at vestibulum nec, feugiat at arcu. Sed dapibus velit eu diam malesuada, et dignissim nibh suscipit. Duis massa augue, fringilla eget iaculis a, sodales vitae tellus.", "yellow"),
        StickyNote("12", "Donec sagittis dignissim egestas. Suspendisse consequat condimentum laoreet. Proin in pellentesque lectus. Maecenas eleifend scelerisque dui, sit amet rutrum turpis egestas non.", "green"),
        StickyNote("13", "Vivamus vehicula viverra sollicitudin. Proin molestie volutpat dui, ut volutpat augue. Duis quis euismod est, vel pulvinar purus.", "blue"),
        StickyNote("14", "Curabitur rutrum, urna sit amet mollis dignissim, enim urna iaculis justo, quis condimentum odio felis quis turpis. Sed vel pharetra nulla. In dui lorem, ultricies at fringilla vel.", "blue"),
        StickyNote("15", "Vivamus pulvinar vulputate sapien, nec porttitor ante laoreet id. Quisque id purus ac leo venenatis eleifend. Praesent ornare mattis purus ut placerat.", "pink"),
        StickyNote("16", "Nulla posuere metus vitae nulla semper, eget consequat nisi maximus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae.", "yellow"),
        StickyNote("17", "Vivamus pellentesque interdum lobortis. Ut pulvinar pretium justo, et pharetra enim pellentesque vel. Donec venenatis in dolor nec scelerisque. Cras in mollis diam, sed pharetra tellus.", "pink"),
        StickyNote("18", "Vestibulum convallis diam erat, id elementum diam viverra quis. Suspendisse tempor dui sed lectus suscipit ultricies. Nullam varius eu felis vitae ullamcorper.", "yellow"),
        StickyNote("19", "Nulla eu leo eu est laoreet tincidunt. Sed tincidunt turpis sit amet nunc ultrices, in pretium libero ullamcorper. Mauris et aliquam elit. Pellentesque hendrerit mattis consequat.", "orange"),
        StickyNote("20", "Quisque gravida ac urna a interdum. Phasellus sed dictum neque. Donec porta, tellus id placerat egestas, est lorem ullamcorper justo, eu consectetur ante diam congue eros.", "green"),
        StickyNote("21", "Morbi cursus urna at turpis egestas, ac maximus enim varius. Mauris urna ex, scelerisque ac maximus sit amet, ultrices non sem. Nulla nec magna felis.", "blue"),
        StickyNote("22", "Etiam viverra consequat varius. Quisque condimentum feugiat risus ut faucibus. Nulla et bibendum augue. Nam aliquet luctus urna at vestibulum.", "yellow"),
        StickyNote("23", "Integer turpis nulla, fermentum tincidunt ipsum vitae, ultricies rutrum est. Sed porta, tortor ac vehicula consectetur, ligula velit elementum mi.", "orange"),
        StickyNote("24", "Sed sed luctus leo. Vestibulum nec egestas sapien. Proin accumsan dui ut eleifend sollicitudin. Quisque vel nunc viverra, faucibus nisi ultricies, accumsan ligula.", "yellow"),
        StickyNote("25", "Aliquam vulputate nisl ut fringilla auctor. Etiam in viverra orci. Praesent sollicitudin dui vel vehicula mattis. Aliquam erat volutpat. Mauris.Mauris pretium, neque a posuere congue, quam sapien accumsan dui.", "green")
    )

    val notes2 = mutableListOf(
        StickyNote("1", "Praesent consequat nibh et augue sollicitudin aliquam. In ultrices risus bibendum magna sagittis pretium. Etiam placerat auctor auctor. Morbi elit purus, cursus in ullamcorper et, feugiat sit amet velit. Praesent egestas purus quis tincidunt interdum.", "orange"),
        StickyNote("2", "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec vel elit porttitor, ultricies turpis at, egestas mi. Sed luctus, nisl vel venenatis ullamcorper, est odio suscipit ante, nec hendrerit orci est in purus.", "blue"),
        StickyNote("3", "Curabitur quis consequat tellus. Aenean lobortis molestie lobortis. Vestibulum nec suscipit diam, quis sodales lorem. Cras sodales, turpis quis pulvinar bibendum, lacus diam tincidunt tortor, ut finibus tellus odio eget lorem.", "orange"),
        StickyNote("4", "Nunc ut justo et sapien efficitur vehicula. Nunc in diam vitae mi iaculis ultrices. Phasellus et metus sit amet mi convallis tincidunt sit amet sed arcu.", "yellow"),
        StickyNote("5", "Proin faucibus urna et arcu eleifend ornare. Praesent augue metus, pretium ut ipsum dictum, consectetur mollis sapien.", "blue"),
        StickyNote("6", "Nulla tincidunt velit vel aliquet semper. Sed eros mauris, semper at purus et, elementum finibus est. Ut pulvinar, justo in iaculis auctor, risus est tristique dolor, tincidunt porta leo sapien eget ipsum.", "yellow"),
        StickyNote("7", "Donec vel massa sed ex condimentum tincidunt. Suspendisse convallis tincidunt justo, non auctor mi finibus in.", "green"),
        StickyNote("8", "Sed fermentum felis eget molestie vulputate. Morbi nec porta dolor. Nam pellentesque nec est eget vestibulum. Sed vel diam vel lorem cursus mollis. In at elit interdum erat dapibus elementum quis at ex.", "yellow"),
        StickyNote("9", "Phasellus sit amet aliquet augue, vitae laoreet nisi. In hac habitasse platea dictumst. Nulla non pharetra eros. Suspendisse orci nisi, pharetra vitae consectetur et, finibus nec nibh. Etiam semper imperdiet dui eget efficitur.", "orange"),
        StickyNote("10", "Curabitur gravida pulvinar ante, vel facilisis lorem porta in. Nam et ultrices massa. Quisque viverra, mi nec mollis finibus, massa libero sollicitudin felis, sit amet pretium justo metus a libero.", "grenn"),
        StickyNote("11", "Mauris purus augue, placerat at vestibulum nec, feugiat at arcu. Sed dapibus velit eu diam malesuada, et dignissim nibh suscipit. Duis massa augue, fringilla eget iaculis a, sodales vitae tellus.", "yellow"),
        StickyNote("12", "Donec sagittis dignissim egestas. Suspendisse consequat condimentum laoreet. Proin in pellentesque lectus. Maecenas eleifend scelerisque dui, sit amet rutrum turpis egestas non.", "green"),
        StickyNote("13", "Vivamus vehicula viverra sollicitudin. Proin molestie volutpat dui, ut volutpat augue. Duis quis euismod est, vel pulvinar purus.", "blue"),
        StickyNote("14", "Curabitur rutrum, urna sit amet mollis dignissim, enim urna iaculis justo, quis condimentum odio felis quis turpis. Sed vel pharetra nulla. In dui lorem, ultricies at fringilla vel.", "blue"),
        StickyNote("15", "Vivamus pulvinar vulputate sapien, nec porttitor ante laoreet id. Quisque id purus ac leo venenatis eleifend. Praesent ornare mattis purus ut placerat.", "pink")
    )

    val notes3 = mutableListOf(
        StickyNote("1", "Praesent consequat nibh et augue sollicitudin aliquam. In ultrices risus bibendum magna sagittis pretium. Etiam placerat auctor auctor. Morbi elit purus, cursus in ullamcorper et, feugiat sit amet velit. Praesent egestas purus quis tincidunt interdum.", "orange"),
        StickyNote("2", "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec vel elit porttitor, ultricies turpis at, egestas mi. Sed luctus, nisl vel venenatis ullamcorper, est odio suscipit ante, nec hendrerit orci est in purus.", "blue"),
        StickyNote("3", "Curabitur quis consequat tellus. Aenean lobortis molestie lobortis. Vestibulum nec suscipit diam, quis sodales lorem. Cras sodales, turpis quis pulvinar bibendum, lacus diam tincidunt tortor, ut finibus tellus odio eget lorem.", "orange"),
        StickyNote("4", "Nunc ut justo et sapien efficitur vehicula. Nunc in diam vitae mi iaculis ultrices. Phasellus et metus sit amet mi convallis tincidunt sit amet sed arcu.", "yellow"),
        StickyNote("5", "Proin faucibus urna et arcu eleifend ornare. Praesent augue metus, pretium ut ipsum dictum, consectetur mollis sapien.", "blue"),
        StickyNote("6", "Nulla tincidunt velit vel aliquet semper. Sed eros mauris, semper at purus et, elementum finibus est. Ut pulvinar, justo in iaculis auctor, risus est tristique dolor, tincidunt porta leo sapien eget ipsum.", "yellow"),
        StickyNote("7", "Donec vel massa sed ex condimentum tincidunt. Suspendisse convallis tincidunt justo, non auctor mi finibus in.", "green"),
        StickyNote("8", "Sed fermentum felis eget molestie vulputate. Morbi nec porta dolor. Nam pellentesque nec est eget vestibulum. Sed vel diam vel lorem cursus mollis. In at elit interdum erat dapibus elementum quis at ex.", "yellow"),
        StickyNote("9", "Phasellus sit amet aliquet augue, vitae laoreet nisi. In hac habitasse platea dictumst. Nulla non pharetra eros. Suspendisse orci nisi, pharetra vitae consectetur et, finibus nec nibh. Etiam semper imperdiet dui eget efficitur.", "orange")
    )
    return App(
        mutableListOf(
            User("1","juan", "juan@gmail.com", "juan", notes),
            User("2","lean", "lean@gmail.com", "lean", notes2),
            User("3","facu", "facu@gmail.com", "facu", notes3)
        )
    )
}