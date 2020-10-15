package me.deprilula28.discordproxykt.events.guild.invite

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import me.deprilula28.discordproxykt.DiscordProxyKt
import me.deprilula28.discordproxykt.entities.Snowflake
import me.deprilula28.discordproxykt.entities.UnavailableField
import me.deprilula28.discordproxykt.entities.discord.channel.PartialGuildChannel
import me.deprilula28.discordproxykt.rest.asSnowflake
import me.deprilula28.discordproxykt.rest.asString
import me.deprilula28.discordproxykt.rest.delegateJson

class GuildInviteDeleteEvent(map: JsonObject, override val bot: DiscordProxyKt): GuildInviteEvent {
    /**
     * @throws [UnavailableField] If this wasn't done in a guild
     */
    override val snowflake: Snowflake by map.delegateJson(JsonElement::asSnowflake, "guild_id")
    override val channel: PartialGuildChannel by map.delegateJson({ PartialGuildChannel.new(guild, asSnowflake()) }, "channel_id")
    override val code: String by map.delegateJson(JsonElement::asString, "code")
}
