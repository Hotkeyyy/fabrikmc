package net.axay.fabrik.test.commands

import net.axay.fabrik.core.text.literalText
import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.HoverEvent

private val textExamples = mapOf(
    "welcome" to literalText {
        text {
            color = 0xFF5E13
            text("Welcome on ")
            text("our") {
                underline = true
            }
            text(" Server") {
                color = 0x21FF9E
            }
            text("!")
        }
        text(" ")
        text {
            color = 0xFDFF80
            hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, literalText("nice, you did it") {
                bold = true
                color = 0x39CEFF
            })
            text("[")
            text("hover and click here") {
                color = 0xFFE12C
                clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/me hey :)")
            }
            text("]")
        }
    }
)

val textTestCommand = testCommand("text") {
    argument<String>("textexample") { example ->
        suggestList { textExamples.keys }
        runs {
            val exampleText = textExamples[example()] ?: return@runs
            source.sendSuccess(exampleText, false)
        }
    }
}
