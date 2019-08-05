import me.aberrantfox.kjdautils.api.startBot
import org.slf4j.Logger
import utilities.dbConnection
import web.initialize

object RepentanceConfig {
    const val primaryGuild: Long = 0
    const val appealGuild: Long = 0
    const val appealChannel: Long = 0
    const val appealResponseChannel: Long = 0
    const val inviteLink: String = ""
    const val database: String = ""
    const val username: String = ""
    const val password: String = ""
    const val token: String = ""
    const val prefix: String = ""

}



fun main() {
    initialize()
    dbConnection()
    startBot(RepentanceConfig.token) {
        configure {
            prefix = RepentanceConfig.prefix
        }
    }
}




