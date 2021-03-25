package net.axay.fabrik.core.text

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.LiteralText

/**
 * Sends the given [LiteralText] to the player.
 */
fun PlayerEntity.sendText(text: LiteralText) {
    sendMessage(text, false)
}

/**
 * Converts this string to a [LiteralText] instance.
 */
val String.literal get() = LiteralText(this)
