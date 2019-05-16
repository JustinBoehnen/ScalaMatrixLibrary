package matrix_source

/*
Class: Matrix

Getters / Setters:
  def Rows: Int - getter for _rows
  def Cols: Int - getter for _cols
  def Data: Array[Array[Double]] - getter for _data

Methods:
  def Transpose: Matrix - transposes the matrix, turning
    all rows into columns and columns into rows
  def Traverse (visit: Double => Unit): Unit - traverses the matrix,
    calling visit on each object
  def Transform (visit: Double => Double): Matrix - creates a new
    matrix with the values in the current matrix transformed by
    visit
  def RowSwap (rowA: Int, rowB: Int): Matrix - returns a new matrix
    with the specified rows swapped
  def ReduceSum: Matrix - adds the values in each row up and returns
    a new matrix
  def PrintMat: Unit - prints the matrix
  def Index (row: Int, col: Int): Double - returns the element at
    the given index
  def GetSize: Array[Int] - returns the size of the matrix as
    an array of Int
*/
class Matrix(val rows: Int, val cols: Int)(val data: Array[Array[Double]] = Array.ofDim(rows, cols)) {
  def Rows: Int = rows
  def Cols: Int = cols

  def Data:Array[Array[Double]] = data

  def Transpose: Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(cols, rows)

    for (row <- 0 until rows)
      for (col <- 0 until cols)
        newData(col)(row) = data(row)(col)

    new Matrix(cols, rows)(newData)
  }

  def Traverse(visit: Double => Unit): Unit = {
    for(row <- 0 until rows)
      for(col <- 0 until cols)
        visit(data(row)(col))
  }

  def Transform(visit: Double => Double): Matrix = new Matrix(rows, cols)(data.map(_.map(f => visit(f))))

  def TransformRow(rowPassed: Int, visit: Double => Double): Matrix = {
    val newData: Array[Array[Double]] = data.zipWithIndex.map(ell => if (ell._2 == rowPassed) { val row = ell._1.map(f => visit(f)); row} else ell._1)
    new Matrix(rows, cols)(newData)
  }

  def RowSwap(rowA: Int, rowB: Int): Matrix = {
    if (rowA >= rows || rowA >= rows || rowB < 0 || rowB < 0) throw new MatrixException("Specified row out of bounds")

    val newData: Array[Array[Double]] = data.clone()
    newData(rowA) = data(rowB)
    newData(rowB) = data(rowA)

    new Matrix(rows, cols)(newData)
  }

  def ReduceSum(): Double = data.flatten.sum

  def PrintMat(): Unit = for (list <- data) { for (item <- list) print("[" + item + "]"); println()}

  def Index(row: Int, col: Int): Double = {
    if(row >= rows || row < 0 || col >= cols || col < 0) throw new MatrixException("Index out of bounds")
    data(row)(col)
  }

  def GetSize(): Array[Int] = Array(rows, cols)
}

/*
Object: Matrix - companion class

Methods:
  def add(lvalue: Matrix, rvalue: Matrix): Matrix - adds two
    matrices together, returning the resulting matrix
  def subtract(lvalue: Matrix, rvalue: Matrix): Matrix - subtracts
    rvalue matrix from lvalue matrix, returning the resulting
    matrix
  def multiply(lvalue: Matrix, rvalue: Matrix): Matrix - multiplies
    two matrices together, returning the cross product of the
    matrices
  def areEqual(lvalue: Matrix, rvalue: Matrix): Boolean - evaluates
    whether lvalue and rvalue are identical matrices and returns the
    verdict
  def identity(dim: Int): Matrix - returns and identity matrix of
    size dim
*/
object Matrix {
  def add(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.Rows != rvalue.Rows || lvalue.Cols != rvalue.Cols)
      throw new MatrixException("Cannot add matrices of differing degrees")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.Rows, lvalue.Cols)

    for(row <- 0 until lvalue.Rows)
      for (col <- 0 until lvalue.Cols)
        newData(row)(col) = lvalue.Data(row)(col) + rvalue.Data(row)(col)

    new Matrix(lvalue.Rows, lvalue.Cols)(newData)
  }

  def subtract(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.Rows != rvalue.Rows || lvalue.Cols != rvalue.Cols)
      throw new MatrixException("Cannot subtract matrices of differing degrees")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.Rows, lvalue.Cols)

    for(row <- 0 until lvalue.Rows)
      for (col <- 0 until lvalue.Cols)
        newData(row)(col) = lvalue.Data(row)(col) - rvalue.Data(row)(col)

    new Matrix(lvalue.Rows, lvalue.Cols)(newData)
  }

  def multiply(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.Cols != rvalue.Rows) throw new MatrixException("cross product lvalue columns must equal rvalue rows")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.Rows, rvalue.Cols)

    for (lrow <- 0 until lvalue.Rows)
      for (rcol <- 0 until rvalue.Cols)
        for (lcol <- 0 until lvalue.Cols)
          newData(lrow)(rcol) += lvalue.Data(lrow)(lcol) * rvalue.Data(lcol)(rcol)

    new Matrix(lvalue.Rows, rvalue.Cols)(newData)
  }

  def areEqual(lvalue: Matrix, rvalue: Matrix): Boolean = {
    var isEqual: Boolean = true

    if(lvalue.Rows != rvalue.Rows || lvalue.Cols != rvalue.Cols) isEqual = false
    else
      for (row <- 0 until lvalue.Rows)
        for (col <- 0 until lvalue.Cols)
          if (lvalue.Data(row)(col) != rvalue.Data(row)(col)) isEqual = false

    isEqual
  }

  def identity(dim: Int): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(dim, dim)

    for (i <- 0 until dim) newData(i)(i) = 1

    new Matrix(dim, dim)(newData)
  }
}