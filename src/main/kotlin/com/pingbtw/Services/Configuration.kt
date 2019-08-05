package com.pingbtw.Services

import me.aberrantfox.kjdautils.api.annotation.Data


data class GuildConfiguration(var guildId: String = "insert-id",
                              var staffRoleName: String = "Staff",
                              val ownerId: String = "insert-id",
                              val prefix: String = "insert-prefix",
                              val primaryGuild: Long = 0,
                              val appealGuild: Long = 0,
                              val appealChannel: Long = 0,
                              val appealResponseChannel: Long = 0,
                              val inviteLink: String = "insert-link",
                              val database: String = "insert-database-name",
                              val username: String = "root",
                              val password: String = "insert-password",
                              val token: String = "insert-bot-token" )

@Data("config/config.json")
data class Configuration(val prefix: String = "--",
                         val generateDocsAtRuntime: Boolean = false,
                         var guildConfigurations: MutableList<GuildConfiguration> = mutableListOf(GuildConfiguration()))

