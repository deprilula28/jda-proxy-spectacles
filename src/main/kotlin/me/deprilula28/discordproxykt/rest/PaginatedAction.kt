package me.deprilula28.discordproxykt.rest

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import me.deprilula28.discordproxykt.DiscordProxyKt

open class PaginatedAction<T: Any>(
    bot: DiscordProxyKt,
    constructor: JsonElement.(DiscordProxyKt) -> T,
    endpoint: RestEndpoint,
    vararg pathParts: String,
): RestAction<List<T>>(bot, { (this as JsonArray).map { constructor(it, bot) } }, endpoint, *pathParts) {
// TODO Actual pagination
}