import matrix_source._
import scala.math

object Main {
  def main(args: Array[String]): Unit = {
    val m1: Matrix = new Matrix(2, 2)(Array(Array(1, 2), Array(3, 4)))
    val expected: Matrix = new Matrix(2, 2)(Array(Array(1, 2), Array(3, 4)))

    Matrix.add(m1, expected)
  }
}