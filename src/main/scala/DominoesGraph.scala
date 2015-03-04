import scala.annotation.tailrec

object Sets {

  /**
   * Generates combinations of given set 's' with given length 'k'.
   * The order doesn't matter.
   *
   * NOTES: To count number of combinations the following formula can be used:
   *
   * C_k,n = n!/(k!(n - k)!
   *
   * Time - O(C_k,n)
   * Space - O(C_k,n)
   */
  def combinations[A](s: Set[A], k: Int): Set[Set[A]] =
    if (k > s.size) Set()
    else if (k == 1) s.map(Set(_))
    else combinations(s.tail, k - 1).map(_ + s.head) ++ combinations(s.tail, k)
}

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

  def subsets(length: Int) = {
    Sets.combinations[(Int, Int)](tiles.toSet, length)
//    val sTiles = tiles.toSet.subsets.toStream.filter(t => t.size == length)
//    @tailrec
//    def helper(tilesSet: Stream[Set[(Int, Int)]], acc: List[List[(Int, Int)]]):List[List[(Int, Int)]] = {
//      if (tilesSet.isEmpty) acc
//      else {
//        tilesSet match {
//          case head #:: tail => helper(tail, acc :+ head.toList)
//        }
//      }
//    }
//
//    helper(sTiles, List[List[(Int, Int)]]())
  }
}

class DominoesGraph(val tiles: List[(Int, Int)]) {
  val vertices = tiles
    .map(t => List(t._1, t._2))
    .flatten.distinct.sorted

  def getTilesByVertex(vertex: Int): List[(Int, Int)] = tiles
    .filter(t => t._1 == vertex || t._2 == vertex)

  def numberVertexEdges(vertex: Int): Int = tiles
    .filterNot(t => t._1 == t._2)
    .count(t => t._1 == vertex || t._2 == vertex)

  def hasIsolatedVertex: Boolean = tiles
    .exists(t => numberVertexEdges(t._1) == 0 && numberVertexEdges(t._2) == 0)

  def hasEulerianCycle: Boolean = !hasIsolatedVertex && tiles.length % 2 == 0
    vertices.forall(v => numberVertexEdges(v) % 2 == 0)

  def hasEulerianPath: Boolean = !hasIsolatedVertex && {
    vertices.count(v => numberVertexEdges(v) % 2 != 0) match {
      case 2 => true
      case 0 => true
      case _ => false
    }
  }
}
