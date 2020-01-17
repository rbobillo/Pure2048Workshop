import java.awt.{Graphics, Graphics2D}
import java.awt.event.{KeyEvent, KeyListener}

import cats.effect.IO
import javax.swing.JPanel

class ColorChangerPanel extends JPanel with KeyListener {

  override def keyPressed(e: KeyEvent): Unit =
    e.getKeyCode match {
      case KeyEvent.VK_SPACE => Gui.updatePanelBackgroundColor(this).unsafeRunSync()
      case k => IO.apply(println(s"key pressed : $k"))
    }

  override def keyTyped(e: KeyEvent): Unit = ()
  override def keyReleased(e: KeyEvent): Unit = ()

}
