package MatrixSource

import scala.runtime.Nothing$

/*
Class: Matrix

Methods:
  def rows: Int - getter for _rows
  def cols: Int - getter for _cols
  def data_= (value: Array[Array[Int]]): Unit - setter for _data
  def transpose: Matrix - does the transpose operation
    on the matrix; returns a new matrix
*/
class Matrix(val _rows: Int, val _cols: Int)( var _data: Array[Array[Double]] = Array.ofDim(_rows, _cols)) {
  def rows: Int = _rows
  def cols: Int = _cols

  def data:Array[Array[Double]] = _data
  def data_= (value: Array[Array[Double]]): Unit = {
    var rowWrong: Int = 0;
    var colWrong: Int = 0;
    if(value.length != _rows) rowWrong = 1;
    for (list <- value) if (list.length != _cols) colWrong = 1;

    if(rowWrong == 1 && colWrong == 1)
      throw new MatrixException("Incorrect row and column assignment dimensions: expected row:" + _rows + ", col:" + _cols);
    else if (rowWrong == 1)
      throw new MatrixException("Incorrect row assignment dimension: expected " + _rows);
    else if (colWrong == 1)
      throw new MatrixException("Incorrect column assignment dimension: expected " + _cols);

    _data = value
  }

  def transpose: Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_cols, _rows)

    for (row <- 0 until _rows)
      for (col <- 0 until _cols)
        newData(col)(row) = _data(row)(col)

    new Matrix(_cols, _rows)(newData)
  }

  def traverse (func: Double => Unit): Unit = {
    for(row <- 0 until _rows)
      for(col <- 0 until _cols)
        func(_data(row)(col))
  }

  def transform (func: Double => Double): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_rows, _cols)

    for(row <- 0 until _rows)
      for(col <- 0 until _cols)
        newData(row)(col) = func(_data(row)(col))

    new Matrix(_rows, _cols)(newData)
  }

  def rowSwap (lrow: Int, rrow: Int): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_rows, _cols)

    if (lrow >= _rows || rrow >= _rows || lrow < 0 || rrow < 0) throw new MatrixException("Specified row out of bounds")
    newData(rrow) = data(lrow)
    newData(lrow) = data(rrow)

    for (row <- 0 until _rows) if (row != lrow && row != rrow) newData(row) = data(row)

    new Matrix(_rows, _cols)(newData)
  }

  def printMat(): Unit = for (list <- _data) { for (item <- list) print("[" + item + "]"); println()}

  def index(row: Int, col: Int): Double = {
    if(row >= _rows || row < 0 || col >= _cols || col < 0) throw new MatrixException("Index out of bounds");
    _data(row)(col)
  }

  def getSize(): Array[Int] = Array(_rows, _cols)
}

object Matrix {
  def add(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols)
      throw new MatrixException("Cannot add matrices of differing degrees")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.rows, lvalue.cols)

    for(row <- 0 until lvalue.rows)
      for (col <- 0 until lvalue.cols)
        newData(row)(col) = lvalue.data(row)(col) + rvalue.data(row)(col)

    new Matrix(lvalue.rows, lvalue.cols)(newData)
  }

  def subtract(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols)
      throw new MatrixException("Cannot subtract matrices of differing degrees")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.rows, lvalue.cols)

    for(row <- 0 until lvalue.rows)
      for (col <- 0 until lvalue.cols)
        newData(row)(col) = lvalue.data(row)(col) - rvalue.data(row)(col)

    new Matrix(lvalue.rows, lvalue.cols)(newData)
  }

  def multiply(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.cols != rvalue.rows) throw new MatrixException("cross product lvalue columns must equal rvalue rows")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.rows, rvalue.cols)

    for (lrow <- 0 until lvalue.rows)
      for (rcol <- 0 until rvalue.cols)
        for (lcol <- 0 until lvalue.cols)
          newData(lrow)(rcol) += lvalue.data(lrow)(lcol) * rvalue.data(lcol)(rcol)

    new Matrix(lvalue.rows, rvalue.cols)(newData)
  }

  def areEqual(lvalue: Matrix, rvalue: Matrix): Boolean = {
    var isEqual: Boolean = true

    if(lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols) isEqual = false
    else
      for (row <- 0 until lvalue.rows)
        for (col <- 0 until lvalue.cols)
          if (lvalue.data(row)(col) != rvalue.data(row)(col)) isEqual = false

    isEqual
  }

  def identity(dim: Int): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(dim, dim)

    for (i <- 0 until dim) newData(i)(i) = 1

    new Matrix(dim, dim)(newData)
  }
}