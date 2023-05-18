package com.mrtsv9.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mrtsv9.model.User
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*

val Application.config: ApplicationConfig
    get() = environment.config
val Application.algorithm: Algorithm
    get() = Algorithm.HMAC512(config.property("jwt.secret").getString())
val Application.issuer: String
    get() = config.property("jwt.issuer").getString()

fun Application.configureSecurity() {

    install(Authentication) {
        jwt {
            verifier(
                JWT.require(this@configureSecurity.algorithm).withIssuer(this@configureSecurity.issuer).build()
            )
            realm = "com.mrtsv9"
            validate {
                val name = it.payload.getClaim("name").asString()
                val password = it.payload.getClaim("password").asString()
                if (name != null && password != null) {
                    User(name, password)
                } else {
                    null
                }
            }
        }
    }

}
