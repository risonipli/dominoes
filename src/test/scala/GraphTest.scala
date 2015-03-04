import org.scalatest.FunSuite

class GraphTest extends FunSuite {
  test("""Vertices 0, 1, 2, 3 and 4 in graph:
      |(0,0)(1,1),(1,2),(1,3) has 4, 4, 2, 2, 0 edges""".stripMargin) {
    val tiles = List((0,0),(1,1),(1,2),(1,3))
    val graph = new DominoesGraph(tiles)
    assert(graph.numberVertexEdges(0) == 0)
    assert(graph.numberVertexEdges(1) == 2)
    assert(graph.numberVertexEdges(2) == 1)
    assert(graph.numberVertexEdges(3) == 1)
    assert(graph.numberVertexEdges(4) == 0)
  }

  test("Graph (0,0)(1,1),(1,2),(1,3) has not eulerian cycle") {
    val tiles = List((0,0),(1,1),(1,2),(1,3))
    val graph = new DominoesGraph(tiles)
    assert(!graph.hasEulerianCycle)
  }

  test("Graph (1,1),(1,2),(1,3) has eulerian path") {
    val tiles = List((1,1),(1,2),(1,3))
    val graph = new DominoesGraph(tiles)
    assert(graph.hasEulerianPath)
  }

  test("Dominoes box contain 28 tiles") {
    assert(DominoesBox.getTiles().length == 28)
  }

  test("Generate 1 subsets 28 length") {
    val subsets = DominoesBox.subsets(28)
//    println(subsets)
    println(subsets.size)
    assert(subsets.size == 1)
  }
}
