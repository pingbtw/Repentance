package com.pingbtw.repentance

import com.pingbtw.repentance.services.Configuration
import com.pingbtw.repentance.utilities.DatabaseUtility
import com.pingbtw.repentance.web.RepentanceApi
import me.aberrantfox.kjdautils.api.annotation.Data
import me.aberrantfox.kjdautils.api.annotation.Service
import me.aberrantfox.kjdautils.api.startBot

//import com.pingbtw.repentance.utilities.dbConnection
//import com.pingbtw.repentance.web.initialize


@Service
fun main(args: Array<String>) {

    val token = args.firstOrNull() ?: return

    startBot(token) {
        configure {
            prefix = config.prefix
            globalPath = "com.pingbtw.repentance"
        }
    }
}

@Service
class DatabaseConnection(configuration: Configuration) {
    init {
        DatabaseUtility(configuration).dbConnection()
        RepentanceApi(configuration).initialize()
    }
}

@Service
class PrefixLoader(configuration: Configuration) {
    init {
        configuration.prefix
    }
}



