package net.axay.fabrik.igui.elements

import kotlinx.coroutines.cancel
import net.axay.fabrik.core.task.coroutineTask
import net.axay.fabrik.igui.GuiCompound
import net.axay.fabrik.igui.GuiIcon

class GuiButtonCompoundScroll(
    icon: GuiIcon,
    val compound: GuiCompound<*>,
    val reverse: Boolean,
    val speed: Long,
    val scrollDistance: Int,
    val scrollTimes: Int,
) : GuiButton(
    icon,
    {
        suspend fun scroll() = if (reverse) compound.scroll(-scrollDistance) else compound.scroll(scrollDistance)

        if (scrollTimes > 1) {
            coroutineTask(
                period = speed,
                howOften = scrollTimes.toLong()
            ) {
                if (!scroll()) cancel()
            }
        } else scroll()
    }
)
