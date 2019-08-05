package listeners

import com.google.common.eventbus.Subscribe
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent
import utilities.checkNewJoinHasAppealed
import utilities.removeAppeal

class LeaveListener {
    @Subscribe
    fun onGuildMemberLeaveEvent(event: GuildMemberLeaveEvent) {
        if (event.guild.idLong == RepentanceConfig.appealGuild && checkNewJoinHasAppealed(event.user.id.toString())) {
            removeAppeal(event.user.id.toString())
            event.jda.getGuildById(RepentanceConfig.primaryGuild).getTextChannelById(RepentanceConfig.appealChannel)
                    .sendMessage("<@${event.user.id}>" +
                    " has left and their appeal has been removed.").queue()
        }
    }
}