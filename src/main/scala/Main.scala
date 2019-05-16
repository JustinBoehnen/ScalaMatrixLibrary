import matrix_source._
import scala.math

object Main {
  def main(args: Array[String]): Unit = {
    val m1: Matrix = new Matrix(2, 2)(Array(Array(1, 2), Array(3, 4)))
    println(s"m1:\n ${m1.PrintMat()}")
    println(s"m1 (after TransformRow):\n ${m1.TransformRow(0, x => x * 2).PrintMat}")
  }
}