package com.github.rbobillo.pure2048.board

object Merging {

  type TilesRow = Array[Tile]
  type TilesGrid = Array[TilesRow]
  type IndexedTiles = Array[(Tile, Int, Int)]

  /*
   * This function is very limited: only handing a 4 sized row
   */
  private def mergeRowLeft(rw: TilesRow): TilesRow =
    rw.filter(_.nonEmpty).padTo(4, Tile.empty) match {
      case Array(a, b, c, d) if a.v == b.v && c.v == d.v => Array(Tile(b.v * 2, b.id), Tile(d.v * 2, d.id)).padTo(4, Tile.empty)
      case Array(a, b, c, d) if a.v == b.v               => Array(Tile(b.v * 2, b.id), c, d).padTo(4, Tile.empty)
      case Array(a, b, c, d) if b.v == c.v               => Array(a, Tile(c.v * 2, c.id), d).padTo(4, Tile.empty)
      case Array(a, b, c, d) if c.v == d.v               => Array(a, b, Tile(d.v * 2, d.id)).padTo(4, Tile.empty)
      case _                                             => rw
    }

  val mergeLeft: TilesGrid => TilesGrid = ts =>
    ts.map(mergeRowLeft)

  /*
   * Transpose:
   * a = [[1, 2],   -> transpose ->  b = [[1, 1],
   *      [1, 2]]                         [2, 2]]
   */
  val mergeRight: TilesGrid => TilesGrid = ts =>
    ts.map(_.reverse)
      .map(mergeRowLeft)
      .map(_.reverse)

  val mergeUp: TilesGrid => TilesGrid = ts =>
    ts.transpose
      .map(mergeRowLeft)
      .transpose

  val mergeDown: TilesGrid => TilesGrid = ts =>
    ts.transpose
      .map(_.reverse)
      .map(mergeRowLeft)
      .map(_.reverse)
      .transpose

}
