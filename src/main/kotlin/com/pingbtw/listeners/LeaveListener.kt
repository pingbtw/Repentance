package com.pingbtw.listeners

import com.google.common.eventbus.Subscribe
import com.pingbtw.RepentanceConfig
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent
import com.pingbtw.utilities.checkNewJoinHasAppealed
import com.pingbtw.utilities.removeAppeal

class LeaveListener {
    @Subscribe
    fun onGuildMemberLeaveEvent(event: GuildMemberLeaveEvent) {
        if (event.guild.idLong == RepentanceConfig.appealGuild && checkNewJoinHasAppealed(event.user.id.toString())) {
            removeAppeal(event.user.id.toString())
            event.jda.getGuildById(RepentanceConfig.primaryGuild).getTextChannelById(RepentanceConfig.appealChannel)
                    .sendMessage("$event.user.asMention" +
                    " has left and their appeal has been removed.").queue()
        }
    }
}