package functionalcolorselector

import arrow.fx.IO
import java.awt.Color
import java.awt.Dimension
import javax.swing.JFrame
import kotlin.random.Random

object Gui {

    private fun randomColor(): IO<Color> {
        return IO { { Random.nextInt(255) } }.flatMap { f ->
            IO { f() }.flatMap { r ->
                IO { f() }.flatMap { g ->
                    IO { f() }.map { b ->
                        Color(r, g, b)
                    }
                }
            }
        }
    }

    private fun initFrameProps(f: JFrame, p: ColorChangerPanel): IO<Unit> {
        return IO { f.contentPane.add(p) }
            .flatMap { IO { f.addKeyListener(p) } }
            .flatMap { IO { f.setDefaultCloseOperation(3) } }
            .flatMap { IO { f.pack() } }
            .flatMap { IO { f.setVisible(true) } }
            .flatMap { IO { f.setFocusable(true) } }
            .flatMap { IO { f.setResizable(false) } }
            .flatMap { IO { f.validate() } }
    }

    fun updatePanelBackgroundColor(p: ColorChangerPanel): IO<Unit> {
        return randomColor().flatMap { c -> IO { p.setBackground(c) }.map { p.repaint() } }
    }

    fun initPanelAndWaitForKeyPress(width: Int, height: Int): IO<Unit> {
        return IO.just(JFrame("Press SPACE key to update background color")).flatMap { f ->
            IO.just(Dimension(width, height)).flatMap { d ->
                IO.just(ColorChangerPanel()).flatMap { p ->
                    IO { p.setPreferredSize(d) }.flatMap { initFrameProps(f, p) }
                }
            }
        }
    }

}