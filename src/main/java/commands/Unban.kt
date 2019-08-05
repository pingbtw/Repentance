package commands

import me.aberrantfox.kjdautils.api.dsl.CommandSet
import me.aberrantfox.kjdautils.api.dsl.commands
import me.aberrantfox.kjdautils.internal.command.arguments.WordArg
import net.dv8tion.jda.core.JDA
import utilities.removeAppeal


@CommandSet("Repentance")
fun unbanUser(event: JDA) = commands {
    command("unban") {
        expect(WordArg)
        description = "Un-ban user"
        execute {
            val userToUnban = it.args[0] as String
            event.getGuildById(RepentanceConfig.primaryGuild).controller.unban(userToUnban).queue()
            removeAppeal(userToUnban)
            event.getGuildById(RepentanceConfig.appealGuild).getTextChannelById(RepentanceConfig.appealResponseChannel)
                    .sendMessage("<@$userToUnban> you have been unbanned! Click here to re-join - https://discord.gg/K48XPMB").queue()
        }
    }
}
