package listeners

import com.google.common.eventbus.Subscribe
import net.dv8tion.jda.core.events.guild.GuildUnbanEvent
import utilities.checkNewJoinHasAppealed
import utilities.removeAppeal

class UnbanListener {

    @Subscribe
    fun onGuildUnbanEvent(event: GuildUnbanEvent) {
        if (event.guild.idLong == 607669856925515808 && checkNewJoinHasAppealed(event.user.id.toString())) {
            removeAppeal(event.user.id.toString())
            event.jda.getGuildById(438424678630162453).getTextChannelById(554101865210445855)
                    .sendMessage("<@${event.user.id} you have been unbanned! Click here to re-join - ${RepentanceConfig.inviteLink}")
        }
    }
}