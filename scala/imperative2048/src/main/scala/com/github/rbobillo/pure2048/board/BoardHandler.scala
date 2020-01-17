package com.github.rbobillo.pure2048.board

import java.awt.{ Color, Font, Graphics2D }

import com.github.rbobillo.pure2048.Config
import com.github.rbobillo.pure2048.gui.{ BoardGui, BoardPanel, GuiUtils }

import scala.util.Try

object BoardHandler {

  var grid: Grid = _

  private def updateGrid(newGrid: Grid): Grid = Try { grid = newGrid }.map(_ => newGrid).getOrElse(grid)

  private def updateBoard(newGrid: Grid, isReset: Boolean = false)(boardPanel: BoardPanel, g: Graphics2D): Unit = {
    val n = newGrid.addTile()

    updateGrid(if (isReset) newGrid else n)
    BoardGui.drawIndexedTiles(g, boardPanel)(grid)
    GuiUtils.changeTitle(boardPanel.frame)(s"Score: ${grid.score}")
    boardPanel.repaint()
  }

  def merge(direction: Direction.Value)(p: BoardPanel): Unit = {
    val g = p.getGraphics.asInstanceOf[Graphics2D]
    val m = if (grid.isPlayable) grid.merge(direction) else (grid, grid)

    if (m._1 differs m._2) updateBoard(m._2)(p, g)
    if (m._2.isGameLost) GuiUtils.changeTitle(p.frame)(s"Game Over - Score: ${grid.score}")
    if (m._2.isGameWon) GuiUtils.changeTitle(p.frame)(s"Game Won - Score: ${grid.score}")
  }

  def reset(boardPanel: BoardPanel): Unit = {
    val n = initGrid
    val g = boardPanel.getGraphics.asInstanceOf[Graphics2D]

    updateBoard(n, isReset = true)(boardPanel, g)
  }

  def initGrid: Grid = {
    val cc = Config.config
    val it = Array.fill(cc.gridHeight)(Array.fill(cc.gridWidth)(Tile.empty))
    val g0 = Grid(tiles = it, prev = it)
    val g1 = g0.addTile().addTile()

    updateGrid(g1)
  }

}
