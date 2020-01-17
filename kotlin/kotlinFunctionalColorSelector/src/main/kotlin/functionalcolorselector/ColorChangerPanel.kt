package functionalcolorselector

import arrow.fx.IO
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

import javax.swing.JPanel

class ColorChangerPanel : JPanel(), KeyListener {

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_SPACE -> Gui.updatePanelBackgroundColor(this)
            else -> IO { println("Key pressed: ${e.keyCode}") }
        }.unsafeRunSync()
    }

    override fun keyTyped(e: KeyEvent) {}
    override fun keyReleased(e: KeyEvent) {}

}