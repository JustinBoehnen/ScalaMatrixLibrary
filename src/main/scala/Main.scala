import MatrixSource._

object Main {
  def main(args: Array[String]): Unit = {
    val m: Matrix = new Matrix(2, 3)
    val a: Matrix = new Matrix(3, 2)

    m.data_=(Array(Array(1, 2, 3), Array(4, 5, 6)))
    a.data_=(Array(Array(1, 2), Array(3, 4), Array(5, 6)))

    var res: Matrix = Matrix.crossProduct(m, a)
    res.printMat()

    val x: Matrix = new Matrix(2, 3)
    val y: Matrix = new Matrix(2, 3)

    x.data_=(Array(Array(1, 2, 3), Array(4, 5, 6)))
    y.data_=(Array(Array(4, 5, 6), Array(7, 8, 9)))
    res = Matrix.dotProduct(x, y)
    res.printMat()
  }
}
