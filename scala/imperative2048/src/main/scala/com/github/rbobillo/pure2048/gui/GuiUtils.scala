package com.github.rbobillo.pure2048.gui

import java.awt.{ Color, Font, Graphics2D, Point, Rectangle }
import java.awt.event.ActionEvent

import com.github.rbobillo.pure2048.Config.config
import com.github.rbobillo.pure2048.gui.BoardGui.hOffset
import javax.swing.{ JComponent, JFrame, Timer }

object GuiUtils {

  def changeTitle(frame: JFrame)(msg: String): Unit =
    frame.setTitle(s"$msg - Press 'r' to reset")

  def drawCenteredString(g: Graphics2D)(s: String, f: Font, x: Int, y: Int, w: Int, h: Int): Unit = {
    val m = g.getFontMetrics(f)
    val i = x + (w - m.stringWidth(s)) / 2
    val j = y + ((h - m.getHeight) / 2) + m.getAscent

    g.drawString(s, i, j)
  }

  def showGameStopMessage(g: Graphics2D)(msg: String): Unit = {
    val f = new Font("Helvetica Neue", Font.BOLD, hOffset / 3)

    g.setColor(new Color(187, 173, 160, 30))
    g.fillRect(0, 0, config.boardWidth, config.boardHeight)
    g.setColor(Color.DARK_GRAY)
    g.setFont(f)
    drawCenteredString(g)(msg, f, 0, 0, config.boardWidth, config.boardHeight)
  }

  def slide(component: JComponent, newPoint: Point): Unit = {
    val frames = 5
    val bounds = component.getBounds()
    val source = new Point(bounds.x, bounds.y)
    val target = new Point((newPoint.x - source.x) / frames, (newPoint.y - source.y) / frames)

    new Timer(10, (e: ActionEvent) => (0 to frames).map { currentFrame =>
      component.setBounds(
        source.x + (target.x * currentFrame),
        source.y + (target.y * currentFrame),
        bounds.width,
        bounds.height)
    }.headOption.foreach(_ => e.getSource.asInstanceOf[Timer].stop())).start()
  }

}
