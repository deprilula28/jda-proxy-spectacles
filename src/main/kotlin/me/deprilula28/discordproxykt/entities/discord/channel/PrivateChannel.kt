package me.deprilula28.discordproxykt.entities.discord.channel

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import me.deprilula28.discordproxykt.DiscordProxyKt
import me.deprilula28.discordproxykt.entities.Entity
import me.deprilula28.discordproxykt.entities.IPartialEntity
import me.deprilula28.discordproxykt.entities.Snowflake
import me.deprilula28.discordproxykt.entities.Timestamp
import me.deprilula28.discordproxykt.rest.*

interface PartialPrivateChannel: PartialMessageChannel, IPartialEntity {
    companion object {
        fun new(id: Snowflake, bot: DiscordProxyKt): Upgradeable
            = object: Upgradeable,
                    RestAction<PrivateChannel>(bot, RestEndpoint.CREATE_DM.path(),
                                               { PrivateChannel(this as JsonObject, bot) }, { Json.encodeToString(
                             "recipient_id" to JsonPrimitive(id.id)
                         ) }
                    ) {
                override val snowflake: Snowflake = id
                override val bot: DiscordProxyKt = bot
        }
    }
    
    interface Upgradeable: PartialPrivateChannel, PartialMessageChannel.Upgradeable, IRestAction<PrivateChannel>
}

/**
 * a direct message between users
 * <br>
 * Channel documentation:
 * https://discord.com/developers/docs/resources/channel
 */
class PrivateChannel(map: JsonObject, bot: DiscordProxyKt): Entity(map, bot), MessageChannel {
    override val lastPinTimestamp: Timestamp? by map.delegateJsonNullable(JsonElement::asTimestamp, "last_pin_timestamp")
    override val lastMessageId: Snowflake by map.delegateJson(JsonElement::asSnowflake, "last_message_id")
}