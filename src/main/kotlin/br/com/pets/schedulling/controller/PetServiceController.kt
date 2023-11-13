package br.com.pets.schedulling.controller

import br.com.pets.schedulling.domain.PetService
import br.com.pets.schedulling.service.PetServiceServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val service = PetServiceServiceImpl()

    routing {
        post("/pet-services") {
            val objectReceived = call.receive<PetService>()
            service.create(objectReceived)
            call.respond(HttpStatusCode.Created, 1)
        }

        put("/pet-services/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val objectReceived = call.receive<PetService>()
            service.update(id, objectReceived)
            call.respond(HttpStatusCode.OK)
        }

        delete("/pet-services") {
            service.deleteAll()
            call.respond(HttpStatusCode.OK)
        }

        get("/pet-services/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            try {
                val objectReceived = service.getById(id)
                call.respond(HttpStatusCode.OK, objectReceived.toString())
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound)
            }
        }

    }
}


