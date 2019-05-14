import MatrixSource._
import scala.math;

object Main {
  def main(args: Array[String]): Unit = {
    val m1: Matrix = new Matrix(1, 2)(Array(Array(1, 2)))
    val m2: Matrix = new Matrix(2, 2)(Array(Array(1, 2), Array(3, 4)))

    println("m2:")
    m2.printMat()

    println("m2 (swapped):")
    m2.rowSwap(0, 1).printMat()
  }
}