import org.scalatest._
import MatrixSource._

class MatrixTests extends FlatSpec {
  "Matrix.add" should "add two matrices together" in {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val b: Matrix = new Matrix(2, 3)(Array(Array(1,2,3),Array(-10,-9,-8)))
    val expected: Matrix = new Matrix(2,3)(Array(Array(2,4,6),Array(-6,-4,-2)))

    val result: Matrix = Matrix.add(a,b)

    assert(Matrix.areEqual(result, expected))
  }
  "Matrix.sub" should "subtract matrix b from matrix a" in {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val b: Matrix = new Matrix(2, 3)(Array(Array(1,3,5),Array(-10,-9,-8)))
    val expected: Matrix = new Matrix(2,3)(Array(Array(0,-1,-2),Array(14,14,14)))

    val result: Matrix = Matrix.subtract(a,b)

    assert(Matrix.areEqual(result, expected))
  }
  "Matrix.crossProduct" should "subtract matrix b from matrix a" in {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val b: Matrix = new Matrix(3, 2)(Array(Array(1,2),Array(3,4),Array(5,6)))
    val expected: Matrix = new Matrix(2,2)(Array(Array(22,28),Array(49,64)))

    val result: Matrix = Matrix.multiply(a,b)

    assert(Matrix.areEqual(result, expected))
  }
  "Matrix.transpose" should "flip a matrix diagonally" in {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val expected: Matrix = new Matrix(3,2)(Array(Array(1,4),Array(2,5),Array(3,6)))

    val result: Matrix = a.transpose

    assert(Matrix.areEqual(result, expected))
  }
  "Matrix.transform" should "operate on all item in a matrix, altering the matrix" in {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val expected: Matrix = new Matrix(2,3)(Array(Array(3,4,5),Array(6,7,8)))

    val result: Matrix = a.transform(x => x + 2)

    assert(Matrix.areEqual(result, expected))
  }
  "Matrix.rowSwap" should "swap two rows within a matrix" in {
    val a: Matrix = new Matrix(3,3)(Array(Array(1,2,3),Array(4,5,6),Array(7,8,9)))
    val expected: Matrix = new Matrix(3,3)(Array(Array(7,8,9),Array(4,5,6),Array(1,2,3)))

    assert(Matrix.areEqual(a.rowSwap(0, 2), expected))
  }
}
