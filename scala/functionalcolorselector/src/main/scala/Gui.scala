import java.awt.{Color, Dimension}

import cats.effect.IO
import javax.swing.JFrame

import scala.util.Random

object Gui {

  private def randomColor(): IO[Color] =
    for {
      f <- IO.pure(() => Random nextInt 255)
      r <- IO.apply(f())
      g <- IO.apply(f())
      b <- IO.apply(f())
    } yield new Color(r,g,b)

  private def initFrameProps(f: JFrame, p: ColorChangerPanel): IO[Unit] =
    for {
      _ <- IO.apply(f.getContentPane.add(p))
      _ <- IO.apply(f.addKeyListener(p))
      _ <- IO.apply(f.setDefaultCloseOperation(3)) // EXIT_ON_CLOSE
      _ <- IO.apply(f.pack())
      _ <- IO.apply(f.setVisible(true))
      _ <- IO.apply(f.setFocusable(true))
      _ <- IO.apply(f.setResizable(false))
      _ <- IO.apply(f.validate())
    } yield ()

  def updatePanelBackgroundColor(p: ColorChangerPanel): IO[Unit] =
    for {
      c <- randomColor()
      _ <- IO.apply(p setBackground c)
      _ <- IO.apply(p.repaint())
    } yield ()

  def initPanelAndWaitForKeyPress(width: Int, height: Int): IO[Unit] =
    for {
      f <- IO.pure(new JFrame("Press SPACE key to update background color"))
      d <- IO.pure(new Dimension(width, height))
      p <- IO.pure(new ColorChangerPanel)
      _ <- IO.apply(p setPreferredSize d)
      _ <- initFrameProps(f, p)
    } yield ()

}
