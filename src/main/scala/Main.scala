/*
На вход поступает набор костяшек (“базар”) в количестве 2< N <28,
представляющие собой костяшки из  стандартного набора домино.
Т.е., имеющие 2 части с кол-вом точек от 0 до 6, включительно, на каждой стороне.
Для полученного «базара» нужно ответить на 3 вопроса:
• Можно ли соединить все костяшки «базара» по правилам домино, без остатка.
Костяшки соединяются сторонами, имеющими одинаковое количество точек.
К каждой стороне обычной костяшки можно подставить только одну соответствующую костяшку.
К дублю можно подставить 4 костяшки.
• Можно ли выстроить костяшки в ряд
• Можно ли замкнуть костяшки в кольцо
Кроме решения задачи, нужно реализовать Unit Test –ы и генератор “Базара” с заданным количеством костяшек N.
 Решение нужно реализовать на scala.
 */
object Main {
  def main(args: Array[String]): Unit = {
    ArgsConfig.parser.parse(args, ArgsConfig.Config()) match {
      case Some(config) =>
        if (config.bazaarLength > 0) {
          val bazaars = DominoesBox.getBazaars(config.bazaarLength)
          bazaars.foreach(b => println(b))
          println(s"Всего базаров ${bazaars.length}")
        }

        if (config.tiles.length > 0) {
          val graph = new DominoesGraph(config.tiles)
          val bazaarText = if (graph.isBazaar) " это базар"
            else " это не базар"
          println(config.tiles, bazaarText)
        }
      case None =>
    }
  }
}
