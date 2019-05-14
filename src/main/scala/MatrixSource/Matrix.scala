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
  def Traverse (func: Double => Unit): Unit - traverses the matrix,
    calling func on each object
  def Transform (func: Double => Double): Matrix - creates a new
    matrix with the values in the current matrix transformed by
    func
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

  def Traverse(func: Double => Unit): Unit = {
    for(row <- 0 until _rows)
      for(col <- 0 until _cols)
        func(_data(row)(col))
  }

  def Transform(func: Double => Double): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_rows, _cols)

    for(row <- 0 until _rows)
      for(col <- 0 until _cols)
        newData(row)(col) = func(_data(row)(col))

    new Matrix(_rows, _cols)(newData)
  }

  def RowSwap(rowA: Int, rowB: Int): Matrix = {
    if (rowA >= _rows || rowA >= _rows || rowB < 0 || rowB < 0) throw new MatrixException("Specified row out of bounds")

    val newData: Array[Array[Double]] = _data.clone()
    newData(rowA) = _data(rowB)
    newData(rowB) = _data(rowA)

    new Matrix(_rows, _cols)(newData)
  }

  def ReduceSum(): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_rows, 1)

    for (row <- 0 until _rows)
      for (i <- 0 until _cols)
        newData(row)(0) += _data(row)(i)

    new Matrix(_rows, 1)(newData)
  }

  def PrintMat(): Unit = for (list <- _data) { for (item <- list) print("[" + item + "]"); println()}

  def Index(row: Int, col: Int): Double = {
    if(row >= _rows || row < 0 || col >= _cols || col < 0) throw new MatrixException("Index out of bounds");
    _data(row)(col)
  }

  def GetSize(): Array[Int] = Array(_rows, _cols)
}

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