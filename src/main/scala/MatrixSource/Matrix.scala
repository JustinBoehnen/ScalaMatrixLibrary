package MatrixSource

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
class Matrix(val _rows: Int, val _cols: Int)(val _data: Array[Array[Double]] = Array.ofDim(_rows, _cols)) {
  def Rows: Int = _rows
  def Cols: Int = _cols

  def Data:Array[Array[Double]] = _data

  def Transpose: Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_cols, _rows)

    for (row <- 0 until _rows)
      for (col <- 0 until _cols)
        newData(col)(row) = _data(row)(col)

    new Matrix(_cols, _rows)(newData)
  }

  def Traverse(visit: Double => Unit): Unit = {
    for(row <- 0 until _rows)
      for(col <- 0 until _cols)
        visit(_data(row)(col))
  }

  def Transform(visit: Double => Double): Matrix = new Matrix(_rows, _cols)(_data.map(_.map(f => visit(f))))

  def TransformRow(rowPassed: Int, visit: Double => Double): Matrix = {
    new Matrix(_rows, _cols)(_data.zipWithIndex.map(ell => if (ell._2 == rowPassed) ell._1.map(f => visit(f)) else ell._1))
  }

  def RowSwap(rowA: Int, rowB: Int): Matrix = {
    if (rowA >= _rows || rowA >= _rows || rowB < 0 || rowB < 0) throw new MatrixException("Specified row out of bounds")

    val newData: Array[Array[Double]] = _data.clone()
    newData(rowA) = _data(rowB)
    newData(rowB) = _data(rowA)

    new Matrix(_rows, _cols)(newData)
  }

  def ReduceSum(): Double = _data.flatten.sum

  def PrintMat(): Unit = for (list <- _data) { for (item <- list) print("[" + item + "]"); println()}

  def Index(row: Int, col: Int): Double = {
    if(row >= _rows || row < 0 || col >= _cols || col < 0) throw new MatrixException("Index out of bounds")
    _data(row)(col)
  }

  def GetSize(): Array[Int] = Array(_rows, _cols)
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