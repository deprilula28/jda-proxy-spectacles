package me.deprilula28.discordproxykt.events

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.deprilula28.discordproxykt.DiscordProxyKt
import java.lang.Exception

class EventConsumer<T>(
    private val bot: DiscordProxyKt,
    private val event: Events.Event<T>,
    private val handler: suspend (T) -> Unit,
    channel: Channel,
): DefaultConsumer(channel) {
    override fun handleDelivery(
        consumerTag: String?,
        envelope: Envelope,
        properties: AMQP.BasicProperties?,
        body: ByteArray,
    ) {
        bot.scope.launch {
            try {
                handler(event.constructor(Json.decodeFromString(body.toString(Charsets.UTF_8)), bot))
            } catch (e: Exception) {
                bot.defaultExceptionHandler(e)
            }
        }
        channel.basicAck(envelope.deliveryTag, false)
    }
}