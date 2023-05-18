package com.mrtsv9

import com.mrtsv9.plugins.configureMonitoring
import com.mrtsv9.plugins.configureRouting
import com.mrtsv9.plugins.configureSecurity
import com.mrtsv9.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

//    routing {
//        get("/") {
//            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
//        }
//
//        get("/json/jackson") {
//            call.respond(mapOf("hello" to "world"))
//        }
//
//        post("/generate_token"){
//            val user = call.receive<User>()
//            print("${user.name} , pwd= ${user.password}")
//            val token = JwtConfig.generateToken(user)
//            call.respond(token)
//
//        }
//
//        authenticate{
//            get("/authenticate"){
//                call.respond("get authenticated value from token "
////                         + "name = ${call.user?.name}, password= ${call.user?.password}"
//                )
//            }
//        }
//    }

    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()


}