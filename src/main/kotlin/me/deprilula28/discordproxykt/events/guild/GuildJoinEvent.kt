package me.deprilula28.discordproxykt.events.guild

import kotlinx.serialization.json.JsonObject
import me.deprilula28.discordproxykt.DiscordProxyKt
import me.deprilula28.discordproxykt.entities.Snowflake
import me.deprilula28.discordproxykt.entities.discord.Guild

class GuildJoinEvent(map: JsonObject, override val bot: DiscordProxyKt): GuildEvent {
    override val guild: Guild = Guild(map, bot)
    
    override val guildSnowflake: Snowflake by guild::snowflake
}