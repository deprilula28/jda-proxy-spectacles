package me.deprilula28.discordproxykt.events

interface GenericEvent {
    val bot: DiscordProxyKt
    
    @Deprecated("JDA Compatibility Field", ReplaceWith("bot"))
    val jda: DiscordProxyKt
        get() = bot
}