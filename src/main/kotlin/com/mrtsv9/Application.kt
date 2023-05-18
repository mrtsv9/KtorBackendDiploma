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
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
