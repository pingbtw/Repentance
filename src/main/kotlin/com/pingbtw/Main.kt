package com.pingbtw

import com.pingbtw.Services.Configuration
import me.aberrantfox.kjdautils.api.annotation.Service
import me.aberrantfox.kjdautils.api.dsl.KJDAConfiguration
import me.aberrantfox.kjdautils.api.startBot
import com.pingbtw.utilities.dbConnection
import com.pingbtw.web.initialize


private lateinit var kjdaConfig: KJDAConfiguration


    fun main() {
    initialize()
    dbConnection()
        startBot("") {
            configure {
                kjdaConfig = this
                globalPath = "com.pingbtw"


            }
        }
    }

    @Service
    class PrefixLoader(kjdaConfiguration: KJDAConfiguration, configuration: Configuration) {
        init {
            kjdaConfiguration.prefix = configuration.prefix
        }
}



