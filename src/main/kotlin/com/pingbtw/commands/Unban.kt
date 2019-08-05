package com.pingbtw.commands

import com.pingbtw.RepentanceConfig
import me.aberrantfox.kjdautils.api.dsl.CommandSet
import me.aberrantfox.kjdautils.api.dsl.commands
import me.aberrantfox.kjdautils.internal.command.arguments.WordArg
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.User
import com.pingbtw.utilities.removeAppeal


@CommandSet("Repentance")
fun unbanUser(event: JDA) = commands {
    command("unban") {
        expect(WordArg)
        description = "Un-ban user"
        execute {
            val userToUnban = it.args[0] as User
            event.getGuildById(RepentanceConfig.primaryGuild).controller.unban(userToUnban).queue()
            removeAppeal(userToUnban.id)
            event.getGuildById(RepentanceConfig.appealGuild).getTextChannelById(RepentanceConfig.appealResponseChannel)
                    .sendMessage("${userToUnban.asMention} you have been unbanned! Click here to re-join - ${RepentanceConfig.inviteLink}").queue()
            it.unsafeRespond("${userToUnban.asMention} has been successfully un-banned!")
        }
    }
}
