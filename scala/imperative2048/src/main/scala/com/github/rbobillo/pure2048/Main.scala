package com.github.rbobillo.pure2048

import com.github.rbobillo.pure2048.board.BoardHandler
import com.github.rbobillo.pure2048.gui.BoardGui

object Main {

  def main(args: Array[String]): Unit = {
    BoardHandler.initGrid
    BoardGui.initGUI
  }

}
