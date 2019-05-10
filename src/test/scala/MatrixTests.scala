import org.scalatest._
import MatrixSource._

class MatrixTests extends FlatSpec {
  "Matrix" should "be able to add two matrices together using Matrix.add" in {
    val a: Matrix = new Matrix(2,3)
    val b: Matrix = new Matrix(2, 3)
    val expected: Matrix = new Matrix(2,3)

    a.data_=(Array(Array(1,2,3),Array(4,5,6)))
    b.data_=(Array(Array(1,2,3),Array(-10,-9,-8)))
    expected.data_=(Array(Array(2,4,6),Array(-6,-4,-2)))

    val result: Matrix = Matrix.add(a,b)

    assert(Matrix.areEqual(result, expected))
  }
  "Matrix" should "be able to be transposed" in {
    val a: Matrix = new Matrix(2,3)
    val expected: Matrix = new Matrix(3,2)

    a.data_=(Array(Array(1,2,3),Array(4,5,6)))
    expected.data_=(Array(Array(1,4),Array(2,5),Array(3,6)))

    val result: Matrix = a.transpose

    assert(Matrix.areEqual(result, expected))
  }
}
