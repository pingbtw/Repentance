package com.pingbtw.repentance.commands


import com.pingbtw.repentance.services.Configuration
import me.aberrantfox.kjdautils.api.dsl.CommandSet
import me.aberrantfox.kjdautils.api.dsl.commands
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.User

import com.pingbtw.repentance.utilities.DatabaseUtility
import me.aberrantfox.kjdautils.internal.command.arguments.UserArg


@CommandSet("Repentance")
fun unbanUser(configuration: Configuration) = commands {
    command("unban") {
        requiresGuild = true
        expect(UserArg)
        description = "Un-ban user"
        execute {
            val guildConfig = configuration.getAppealGuildConfig(it.guild!!.idLong)!!
            if (it.channel.idLong == guildConfig.appealChannel) {
                val userToUnban = it.args[0] as User
                it.guild!!.controller.unban(userToUnban)
                DatabaseUtility(configuration).removeAppeal(userToUnban.id)
                it.jda.getGuildById(guildConfig.appealGuild).getTextChannelById(guildConfig.appealResponseChannel)
                        .sendMessage("${userToUnban.asMention} you have been unbanned! " + "" +
                                "Click here to re-join - ${guildConfig.inviteLink}").queue()
                it.unsafeRespond("${userToUnban.asMention} has been successfully un-banned!")
            }
        }
    }
}