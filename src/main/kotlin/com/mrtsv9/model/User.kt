package com.mrtsv9.model

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String = "",
    val password: String = ""
) : Principal
