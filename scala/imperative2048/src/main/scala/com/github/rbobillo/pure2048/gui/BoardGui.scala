package com.github.rbobillo.pure2048.gui

import java.awt.{ Dimension, Font, Graphics2D }

import com.github.rbobillo.pure2048.Config.config
import com.github.rbobillo.pure2048.board.Merging.IndexedTiles
import com.github.rbobillo.pure2048.board.{ Grid, Tile }
import javax.swing.JFrame

object BoardGui {

  val spacing: Int = (config.boardWidth / config.gridWidth + config.boardHeight / config.gridHeight) / 2 / 10
  val wOffset: Int = config.boardWidth / config.gridWidth - spacing / config.gridWidth
  val hOffset: Int = config.boardHeight / config.gridHeight - spacing / config.gridHeight

  private def drawTileText(g: Graphics2D)(v: Int, x: Int, y: Int): Unit = {
    val xx = x * wOffset
    val yy = y * hOffset
    val f = new Font("Helvetica Neue", Font.BOLD, hOffset / 3)
    val n = v.toString.dropWhile(_ == '0')

    g.setColor(config.tileColor(v).font)
    g.setFont(f)
    GuiUtils.drawCenteredString(g)(n, f, xx + spacing, yy + spacing, wOffset - spacing, hOffset - spacing)
  }

  private def drawTileBackGround(g: Graphics2D)(v: Int, x: Int, y: Int): Unit = {
    val xx = x * wOffset
    val yy = y * hOffset

    g.setColor(config.tileColor(v).background)
    g.fillRoundRect(xx + spacing, yy + spacing, wOffset - spacing, hOffset - spacing, 15, 15)
  }

  private def drawTile(g: Graphics2D, p: BoardPanel)(t: Tile, x: Int, y: Int, prev: IndexedTiles): Unit = {
    drawTileBackGround(g)(t.v, x, y)
    drawTileText(g)(t.v, x, y)
  }

  def drawIndexedTiles(g: Graphics2D, boardPanel: BoardPanel)(grid: Grid): Unit = {
    val ts = grid.indexedTiles.toList

    boardPanel.frame.setBackground(config.boardBackgroundColor)
    ts.foreach { case (t, x, y) => drawTile(g, boardPanel)(t, x, y, grid.indexedPrev) }
  }

  private def createFrameAndPanel(width: Int, height: Int): BoardPanel = {
    val f = new JFrame
    val d = new Dimension(width, height)
    val p = new BoardPanel(f)

    p.setPreferredSize(d)

    p
  }

  private def initFrame(frame: JFrame, panel: BoardPanel): JFrame = {
    frame.setDefaultCloseOperation(3) // EXIT_ON_CLOSE
    frame.getContentPane.add(panel)
    frame.pack()
    frame.setVisible(true)
    frame.addKeyListener(panel)
    frame.setFocusable(true)
    frame.setResizable(false)
    frame.setTitle("2048")
    frame.validate()

    frame
  }

  def initGUI: BoardPanel = {
    val p = createFrameAndPanel(config.boardWidth, config.boardHeight)

    initFrame(frame = p.frame, panel = p)

    p
  }

}
