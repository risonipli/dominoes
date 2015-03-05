import scala.annotation.tailrec

object DominoesBox {
  lazy val tiles = getTiles()

  def getTiles() = {
    val max = 6

    @tailrec
    def helper(tile: (Int, Int), acc: List[(Int, Int)]): List[(Int, Int)] = {
      if (tile._1 == max && tile._2 == max) acc
      else {
        val newTile =
          if (tile._2 == max) (tile._1 + 1, max - (max - (tile._1 + 1)))
          else (tile._1, tile._2 + 1)
        helper(newTile, newTile :: acc)
      }
    }

    val startTile = (0, 0)
    helper(startTile, startTile :: List[(Int, Int)]())
  }

  def subsets(length: Int) =
    Sets.combinations[(Int, Int)](tiles.toSet, length).toList.map(_.toList)

  def getBazaars(length: Int): List[List[(Int, Int)]] = subsets(length)
    .map(s => new DominoesGraph(s))
    .filter(g => g.hasEulerianPath)
    .map(_.tiles)
}