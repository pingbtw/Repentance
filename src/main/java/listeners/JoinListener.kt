package listeners

import com.google.common.eventbus.Subscribe
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import utilities.checkNewJoinHasAppealed

class JoinListener {
    @Subscribe
    fun onGuildMemberJoinEvent(event: GuildMemberJoinEvent) {

        val userId = event.user.id.toString()

        if (event.guild.idLong == RepentanceConfig.appealGuild && checkNewJoinHasAppealed(userId)) {
            event.jda.getGuildById(RepentanceConfig.primaryGuild).getTextChannelById(RepentanceConfig.appealChannel)
                    .sendMessage("<@${event.user.id}> has requested an un-ban").queue()
        }

        if (event.guild.idLong == RepentanceConfig.appealGuild && !checkNewJoinHasAppealed(userId)) {
            event.jda.getGuildById(RepentanceConfig.primaryGuild).getTextChannelById(RepentanceConfig.appealChannel)
                    .sendMessage("User has joined without an appeal and has been removed").queue()
            event.jda.getGuildById(RepentanceConfig.appealGuild).controller.kick(event.user.id.toString()).queue()
        }

        if (event.guild.idLong == RepentanceConfig.primaryGuild && userInGuild(event, event.user)) {
            event.jda.getGuildById(RepentanceConfig.appealGuild).controller.kick(event.user.id.toString()).queue()
            event.jda.getGuildById(RepentanceConfig.appealGuild)
                    .getTextChannelById(RepentanceConfig.appealResponseChannel)
                    .sendMessage("<@${event.user.id}> has been un-banned and removed from guild.").queue()
        }
    }

    fun userInGuild(event: GuildMemberJoinEvent, user: User): Boolean {

        if (event.jda.getGuildById(RepentanceConfig.appealGuild).isMember(user)) {
            return true
        }

        return false
    }
}
