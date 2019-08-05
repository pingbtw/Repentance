package listeners

import com.google.common.eventbus.Subscribe
import net.dv8tion.jda.core.events.guild.GuildUnbanEvent
import utilities.checkNewJoinHasAppealed
import utilities.removeAppeal

class UnbanListener {

    @Subscribe
    fun onGuildUnbanEvent(event: GuildUnbanEvent) {
        if (event.guild.idLong == RepentanceConfig.primaryGuild && checkNewJoinHasAppealed(event.user.id.toString())) {
            removeAppeal(event.user.id.toString())
            event.jda.getGuildById(RepentanceConfig.appealGuild).getTextChannelById(RepentanceConfig.appealResponseChannel)
                    .sendMessage("${event.user.asMention} you have been unbanned! Click here to re-join - ${RepentanceConfig.inviteLink}")
        }
    }
}