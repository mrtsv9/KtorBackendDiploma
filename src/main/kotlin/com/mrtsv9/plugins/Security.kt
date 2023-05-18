package com.mrtsv9.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.mrtsv9.jwtConfig.JwtConfig
import com.mrtsv9.model.User
import io.ktor.server.application.*

fun Application.configureSecurity() {

    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = "com.imran"
            validate {
                val login = it.payload.getClaim("name").asString()
                val password = it.payload.getClaim("password").asString()
                if (login != null && password != null) {
                    User(login, password)
                } else {
                    null
                }
            }
        }
    }

//    val secret = environment.config.property("jwt.secret").getString()
//    val issuer = environment.config.property("jwt.issuer").getString()
//    val audience = environment.config.property("jwt.audience").getString()
//    val myRealm = environment.config.property("jwt.realm").getString()
//    install(Authentication) {
//        jwt {
//            realm = myRealm
//            verifier(
//                JWT
//                    .require(Algorithm.HMAC256(secret))
//                    .withAudience(audience)
//                    .withIssuer(issuer)
//                    .build()
//            )
//            validate { credential ->
//                if (credential.payload.getClaim("login").asString() != "") {
//                    JWTPrincipal(credential.payload)
//                } else {
//                    null
//                }
//            }
//        }
//    }

//    install(Authentication) {
//        jwt {
//            realm = "com.mrtsv9"
//            validate {
//                val login = it.payload.getClaim("login").asString()
//                val password = it.payload.getClaim("password").asString()
//                if (login != null && password != null) {
//                    User(login, password)
//                } else {
//                    null
//                }
//            }
//        }
//    }

//    authentication {
//            jwt {
//                val jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
//                realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
//                verifier(
//                    JWT
//                        .require(Algorithm.HMAC256("secret"))
//                        .withAudience(jwtAudience)
//                        .withIssuer(this@configureSecurity.environment.config.property("jwt.domain").getString())
//                        .build()
//                )
//                validate { credential ->
//                    if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
//                }
//            }
//        }
}
