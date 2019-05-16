import org.scalatest._
import matrix_source._

class MatrixTests extends FunSuite {
  test("Matrix.add should add matrix b to matrix a") {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val b: Matrix = new Matrix(2, 3)(Array(Array(1,2,3),Array(-10,-9,-8)))
    val expected: Matrix = new Matrix(2,3)(Array(Array(2,4,6),Array(-6,-4,-2)))

    val result: Matrix = Matrix.add(a,b)

    assert(Matrix.areEqual(result, expected))
  }

  test("Matrix.sub should subtract matrix b from matrix a") {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val b: Matrix = new Matrix(2, 3)(Array(Array(1,3,5),Array(-10,-9,-8)))
    val expected: Matrix = new Matrix(2,3)(Array(Array(0,-1,-2),Array(14,14,14)))

    val result: Matrix = Matrix.subtract(a,b)

    assert(Matrix.areEqual(result, expected))
  }

  test("Matrix.multiply should perform matrix multiplication on a and b") {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val b: Matrix = new Matrix(3, 2)(Array(Array(1,2),Array(3,4),Array(5,6)))
    val expected: Matrix = new Matrix(2,2)(Array(Array(22,28),Array(49,64)))

    val result: Matrix = Matrix.multiply(a,b)

    assert(Matrix.areEqual(result, expected))
  }

  test("Matrix.transpose should flip a matrix diagonally") {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val expected: Matrix = new Matrix(3,2)(Array(Array(1,4),Array(2,5),Array(3,6)))

    val result: Matrix = a.Transpose

    assert(Matrix.areEqual(result, expected))
  }

  test("Matrix.transform should operate on all item in a matrix, altering the matrix") {
    val a: Matrix = new Matrix(2,3)(Array(Array(1,2,3),Array(4,5,6)))
    val expected: Matrix = new Matrix(2,3)(Array(Array(3,4,5),Array(6,7,8)))

    val result: Matrix = a.Transform(x => x + 2)

    assert(Matrix.areEqual(result, expected))
  }

  test("Matrix.RowSwap should swap two rows within a matrix") {
    val a: Matrix = new Matrix(3,3)(Array(Array(1,2,3),Array(4,5,6),Array(7,8,9)))
    val expected: Matrix = new Matrix(3,3)(Array(Array(7,8,9),Array(4,5,6),Array(1,2,3)))

    assert(Matrix.areEqual(a.RowSwap(0, 2), expected))
  }

  test("Matrix.GetSize should return the size of the matrix as array") {
    val a: Matrix = new Matrix(5, 6)()
    val expected: Array[Int] = Array(5, 6)

    assert(a.GetSize()(0) == expected(0) && a.GetSize()(1) == expected(1))
  }

  test("Matrix.TransformRow should transform a row with a given function") {
    val a: Matrix = new Matrix(2, 2)()
    val expected: Matrix = new Matrix(2, 2)(Array(Array(1, 1), Array(0, 0)))

    assert(Matrix.areEqual(a.TransformRow(0, x => x + 1), expected))
  }

  test("Matrix.RowSwap should throw MatrixException if rowA >= num rows") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(2, 2)()

      a.RowSwap(2, 1)
    }
  }

  test("Matrix.RowSwap should throw MatrixException if rowA < 0") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(2, 2)()

      a.RowSwap(-1, 1)
    }
  }

  test("Matrix.RowSwap should throw MatrixException if rowB >= num rows") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(2, 2)()

      a.RowSwap(1, 2)
    }
  }

  test("Matrix.RowSwap should throw MatrixException if rowB < 0") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(2, 2)()

      a.RowSwap(1, -1)
    }
  }

  test("Matrix.Index should throw MatrixException if index is out of range") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(2, 2)()

      a.Index(3, 3)
    }
  }

  test("Matrix.add should throw MatrixException if rows aren't the same") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(1, 2)()
      val b: Matrix = new Matrix(2, 2)()

      Matrix.add(a, b)
    }
  }

  test("Matrix.add should throw MatrixException if columns aren't the same") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(2, 1)()
      val b: Matrix = new Matrix(2, 2)()

      Matrix.add(a, b)
    }
  }

  test("Matrix.subtract should throw MatrixException if rows aren't the same") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(1, 2)()
      val b: Matrix = new Matrix(2, 2)()

      Matrix.subtract(a, b)
    }
  }

  test("Matrix.subtract should throw MatrixException if columns aren't the same") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(2, 1)()
      val b: Matrix = new Matrix(2, 2)()

      Matrix.subtract(a, b)
    }
  }

  test("Matrix.multiply should throw MatrixException if lvalue columns and rvalue rows aren't the same") {
    assertThrows[MatrixException]{
      val a: Matrix = new Matrix(3, 3)()
      val b: Matrix = new Matrix(2, 2)()

      Matrix.multiply(a, b)
    }
  }

  test("Matrix constructor should throw MatrixException if rows on data aren't equal to rows") {
    assertThrows[MatrixException] {
      val a: Matrix = new Matrix(2, 2)(Array(Array(1, 2)))
    }
  }

  test("Matrix constructor should throw MatrixException if columns on data aren't equal to cols") {
    assertThrows[MatrixException] {
      val a: Matrix = new Matrix(2, 2)(Array(Array(1, 2), Array(1)))
    }
  }
}
