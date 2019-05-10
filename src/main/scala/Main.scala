import MatrixSource._

object Main {
  def main(args: Array[String]): Unit = {
    val m: Matrix = new Matrix(2, 3)
    val a: Matrix = new Matrix(2, 3)

    m.data_=(Array(Array(1, 2, 3), Array(4, 5, 6)))
    a.data_=(Array(Array(1, 2, 3), Array(4, 5, 6)))

    var res: Matrix = Matrix.add(m, a)
    res = res.transform(x => x - 1)
    res.printMat()

    var newMat = m.transpose
    newMat = newMat.transform(x => x + 100)
    newMat.printMat()

    try {
      newMat.data_=(Array(Array(1, 2, 3), Array(4, 5, 6)))
    }
    catch {
      case m: MatrixException => println(m.getMessage)
    }

    try {
      newMat.data_=(Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8)))
    }
    catch {
      case m: MatrixException => println(m.getMessage)
    }
  }
}
