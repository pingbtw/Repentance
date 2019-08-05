package com.pingbtw.repentance.services

import me.aberrantfox.kjdautils.api.annotation.Data


data class GuildConfiguration(
        var staffRoleName: String = "Staff",
        val primaryGuild: Long = 0,
        val appealGuild: Long = 0,
        val appealChannel: Long = 0,
        val appealResponseChannel: Long = 0,
        val inviteLink: String = "insert-link")

@Data("config/config.json")
data class Configuration(
        val primaryGuild: Long = 0,
        val appealGuild: Long = 0,
        val ownerId: String = "insert-id",
        val prefix: String = "insert-prefix",
        val database: String = "insert-database-name",
        val username: String = "root",
        val password: String = "insert-password",
        val generateDocsAtRuntime: Boolean = false,
        var guildConfigurations: MutableList<GuildConfiguration> = mutableListOf(GuildConfiguration())) {
    fun hasPrimaryGuildConfig(guildId: Long) = getPrimaryGuildConfig(guildId) != null
    fun hasAppealGuildConfig(guildId: Long) = getAppealGuildConfig(guildId) != null
    fun getPrimaryGuildConfig(guildId: Long) = guildConfigurations.firstOrNull { it.primaryGuild == guildId }
    fun getAppealGuildConfig(guildId: Long) = guildConfigurations.firstOrNull { it.appealGuild == guildId }
}



