package com.pingbtw.repentance.listeners

import com.google.common.eventbus.Subscribe
import com.pingbtw.repentance.services.Configuration
import com.pingbtw.repentance.utilities.DatabaseUtility
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import me.aberrantfox.kjdautils.api.annotation.Service


@Service
class JoinListener(
        val config: Configuration
) {

    @Subscribe
    fun onGuildMemberJoinEvent(event: GuildMemberJoinEvent) {
        val primaryGuild = config.getPrimaryGuildConfig(config.primaryGuild)!!
        val appealGuild = config.getAppealGuildConfig(config.appealGuild)!!
        val dataUtil = DatabaseUtility(config)
        val joinAppealGuild: Boolean = event.guild.idLong == appealGuild.appealGuild
        val userId = event.user.id.toString()
        val hasMadeAppeal: Boolean = dataUtil.checkNewJoinHasAppealed(userId)

        if (joinAppealGuild && hasMadeAppeal) {
            event.jda.getGuildById(primaryGuild.primaryGuild).getTextChannelById(primaryGuild.appealChannel)
                    .sendMessage("${event.user.asMention} has requested an un-ban").queue()
        }

        if (joinAppealGuild && hasMadeAppeal) {
            event.jda.getGuildById(primaryGuild.primaryGuild)
                    .getTextChannelById(primaryGuild.appealChannel)
                        .sendMessage("User has joined without an appeal and has been removed").queue()
            event.jda.getGuildById(appealGuild.appealGuild).controller.kick(event.user.id.toString()).queue()
        }

        if (event.guild.idLong == primaryGuild.primaryGuild && userInGuild(event, event.user)) {
            event.jda.getGuildById(appealGuild.appealGuild).controller.kick(event.user.id.toString()).queue()
            event.jda.getGuildById(appealGuild.appealGuild)
                    .getTextChannelById(appealGuild.appealResponseChannel)
                        .sendMessage("${event.user.asMention} has been un-banned and removed from the guild.")
                            .queue()
        }
    }

    fun userInGuild(event: GuildMemberJoinEvent, user: User): Boolean {
        val appealGuild = config.getAppealGuildConfig(config.appealGuild)
        if (event.jda.getGuildById(appealGuild!!.appealGuild).isMember(user)) {
            return true
        }
        return false
    }
}
