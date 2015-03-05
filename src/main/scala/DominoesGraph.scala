class DominoesGraph(val tiles: List[(Int, Int)]) {
  val vertices = tiles
    .map(t => List(t._1, t._2))
    .flatten.distinct.sorted

  def numberVertexEdges(vertex: Int): Int = tiles
    .filterNot(t => t._1 == t._2)
    .count(t => t._1 == vertex || t._2 == vertex)

  def numberTileEdges(tile: (Int, Int)): Int =
    if (isDoubleTile(tile)) numberVertexEdges(tile._1)
    else numberVertexEdges(tile._1) + numberVertexEdges(tile._2)

  private def hasIsolatedTiles: Boolean = tiles
    .exists(t => numberTileEdges(t) == 0)

  def getTilesByVertex(vertex: Int): List[(Int, Int)] = tiles
    .filter(t => t._1 == vertex || t._2 == vertex)

  def isDoubleTile(tile: (Int, Int)): Boolean = tile._1 == tile._2

  def hasEulerianPath: Boolean = vertices
    .count(v => numberVertexEdges(v) % 2 != 0) match {
      case 2 => true
      case 0 => true
      case _ => false
    }

  def isBazaar: Boolean = {
    !hasIsolatedTiles && (hasEulerianPath || canPlaceAllTiles)
  }

  def canPlaceAllTiles: Boolean = {
    val doubleTiles = tiles.filter(isDoubleTile)

    tiles.count(t => numberTileEdges(t) % 2 == 2) == 2 ||
      doubleTiles.forall(t => {
        getTilesByVertex(t._1).count(numberTileEdges(_) == 1) <= 4
      })
  }
}
