package com.pingbtw.repentance.utilities



import com.pingbtw.repentance.services.Configuration
import me.aberrantfox.kjdautils.api.annotation.Service
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object BanAppeals : Table() {
    val id = varchar("id", 255)
}

@Service
class DatabaseUtility(val configuration: Configuration) {

    fun dbConnection() {
        Database.connect("jdbc:mysql://localhost:3306/${configuration.database}?serverTimezone=UTC", driver = "com.mysql.cj.jdbc.Driver",
                user = configuration.username, password = configuration.password)
        transaction {
            SchemaUtils.create(BanAppeals)
        }
    }

    fun submitAppeal(userId: String) {
        transaction {
            BanAppeals.insert { it[id] = userId }
        }
    }

    fun checkNewJoinHasAppealed(userId: String) =
            transaction {
                BanAppeals.select(Op.build { BanAppeals.id eq userId }).count() > 0
            }

    fun removeAppeal(userId: String) {
        transaction {
            BanAppeals.deleteWhere { BanAppeals.id eq userId }
        }
    }
}