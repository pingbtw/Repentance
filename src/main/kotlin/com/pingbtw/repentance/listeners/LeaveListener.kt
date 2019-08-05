package com.pingbtw.repentance.listeners

import com.google.common.eventbus.Subscribe
import com.pingbtw.repentance.services.Configuration
import com.pingbtw.repentance.utilities.DatabaseUtility
import me.aberrantfox.kjdautils.api.annotation.Service
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent

@Service
class LeaveListener(
        val config: Configuration
) {
    @Subscribe
    fun onGuildMemberLeaveEvent(event: GuildMemberLeaveEvent) {
        val primaryGuild = config.getPrimaryGuildConfig(config.primaryGuild)!!
        val appealGuild = config.getAppealGuildConfig(config.appealGuild)!!
        val appealChannel = appealGuild.appealChannel
        val dataUtility = DatabaseUtility(config)
        val userHasAppealed: Boolean = dataUtility.checkNewJoinHasAppealed(event.user.id.toString())
        val isAppealGuild: Boolean = event.guild.idLong == appealGuild.appealGuild

        if (isAppealGuild && userHasAppealed) {
            dataUtility.removeAppeal(event.user.id.toString())
            event.jda.getGuildById(primaryGuild.primaryGuild)
                    .getTextChannelById(appealChannel)
                         .sendMessage("$event.user.asMention" +
                            " has left and their appeal has been removed.").queue()
        }
    }
}