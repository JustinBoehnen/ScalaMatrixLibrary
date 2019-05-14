import MatrixSource._
import scala.math;

object Main {
  def main(args: Array[String]): Unit = {
    val weights: Matrix = new Matrix(2, 2)(Array(Array(1, 2), Array(3, 4)))

    weights.Transpose.ReduceSum.PrintMat
  }
}