package com.mrtsv9.plugins

import com.auth0.jwt.JWT
import com.mrtsv9.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("name", user.name)
        .withClaim("password", user.password)
        .sign(algorithm)

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        post("/register") {
            val user = call.receive<User>()
            print("${user.name}, pwd= ${user.password}")
            val token = generateToken(user)
            call.respond(token)
        }

        authenticate {
            get("/login") {
                val user = call.authentication.principal<User>()
                call.respond(
                    "get authenticated value from token "
                            + "name = ${user?.name}, password= ${user?.password}"
                )
            }
        }
    }
}