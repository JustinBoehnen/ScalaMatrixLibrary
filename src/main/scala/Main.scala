import MatrixSource._
import scala.math

object Main {
  def main(args: Array[String]): Unit = {
    var weights: Matrix = new Matrix(1, 2)()
    val inputs: Array[Matrix] = Array(
      new Matrix(2, 1)(Array(Array(0), Array(1))),
      new Matrix(2, 1)(Array(Array(0), Array(1))),
      new Matrix(2, 1)(Array(Array(1), Array(0))),
      new Matrix(2, 1)(Array(Array(1), Array(1))))

    val expected: Array[Matrix] = Array(
      new Matrix(1, 1)(Array(Array(0))),
      new Matrix(1, 1)(Array(Array(1))),
      new Matrix(1, 1)(Array(Array(1))),
      new Matrix(1, 1)(Array(Array(1)))
    )

    val learning_rate: Double = 0.01
    var bias: Double = 0

    for (iters <- 0 until 1000) {
      print("iter: " + iters)
      for (i <- 0 until expected.length) {
        print("i: " + i)
        var y_hat = Matrix.multiply(weights, inputs(i))
        println("y_hat: ")
        y_hat.PrintMat()
        y_hat = y_hat.Transform((x) => 1 / 1 + math.exp(-x))
        println("y_hat: ")
        y_hat.PrintMat()

        val err = Matrix.subtract(expected(i), y_hat)
        println("err: ")
        err.PrintMat()
        val deltaW = inputs(i).Transform((x) => x * err.ReduceSum())
        println("deltaW: ")
        deltaW.PrintMat()
        val deltaB = err.ReduceSum()

        weights = deltaW.Transform((x) => x * learning_rate)
        bias = bias + learning_rate * deltaB

        println("weights: ")
        weights.PrintMat()
        weights = weights.Transpose
      }
    }


  }
}