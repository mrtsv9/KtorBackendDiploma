package com.mrtsv9.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mrtsv9.jwtConfig.JwtConfig
import com.mrtsv9.jwtConfig.JwtConfig.generateToken
import com.mrtsv9.model.User
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import java.util.Date

fun Application.configureRouting() {

    routing {

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        post("/generate_token") {
            val user = call.receive<User>()
            print("${user.name} , pwd= ${user.password}")
            val token = JwtConfig.generateToken(user)
            call.respond(token)

        }

        authenticate {
            get("/authenticate") {
                call.respond(
                    "get authenticated value from token "
                         + "name = ${call.parameters["name"]}, password= ${call.parameters["password"]}"
                )
            }
        }
    }
}


//routing {
//    get("/") {
//        call.respondText("Hello World!")
//    }
//
//    post("/register") {
//        val environment = this@configureRouting.environment
//        val user = call.receive<User>()
//        print(user)
//        val token = JWT.create()
//            .withAudience(environment.config.property("jwt.audience").getString())
//            .withIssuer(environment.config.property("jwt.issuer").getString())
//            .withClaim("username", user.name)
//            .sign(Algorithm.HMAC256(environment.config.property("jwt.secret").getString()))
//        call.respond(token)
//    }
//
//    post("/get_user") {
//        val user = call.receive<User>()
//        print("${user.name}, ${user.password}")
//        val token = generateToken(user)
//        call.respond(token)
//    }
//    authenticate {
//        get("/authenticate") {
//            call.respond(
//                "get authenticated value from token: \n${call.receive(User::class).name}, ${
//                    call.receive(
//                        User::class
//                    ).password
//                }"
//            )
//        }
//    }
//}
