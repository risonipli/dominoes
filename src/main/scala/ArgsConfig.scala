
object ArgsConfig {
  case class Config(tiles: List[(Int, Int)] = List())

  implicit val tilesRead: scopt.Read[List[(Int, Int)]] =
    scopt.Read.reads((tilesStr) => {
      tilesStr.trim.split(' ')
      .map(_.replace("(", "").replace(")", ""))
      .map(_.split(',')).map(e => (e.head.toInt, e.last.toInt)).toList
    })

  val parser = new scopt.OptionParser[Config]("pinpong") {
    head("pinpong", "0.0.1")
    opt[List[(Int, Int)]]('t', "tiles") valueName "(side1,side2) (side1,side2)..." action { case (x, c) =>
      c.copy(tiles = x)
    } validate { x =>
        if (x.length > 2 && x.length < 28) success else failure("Костяшек должно быть от 3 до 27")
    } validate { x =>
        if (x.exists(t => t._1 < 0 || t._1 > 6 || t._2 < 0 || t._2 > 6))
          failure("Значение на стороне костяшки должно быть от 0 до 6")
        else success
      } text "dominoes tiles"
    }
}
