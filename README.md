# ScalaMatrixLibrary
A matrix library for Scala that supports various matrix operations

Documentation
======

**Transpose: Matrix** - this method transposes the Matrix, turning all rows into columns and columns into rows; these changes are returned in a new Matrix

**Traverse: (visit: Double => Unit): Unit** - calls a visit function over every element in the Matrix

**Transform(func: Double => Double): Matrix** - transforms every element in the Matrix with a given function; these changes are returned in a new Matrix

**RowSwap(rowA: Int, rowB: Int): Matrix** - swaps the elements of two rows; these changes are returned in a new Matrix

**ReduceSum(): Matrix** - adds up the values of all elements in each rows, making and returning a new Matrix with one column

**PrintMat(): Unit** - prints the entire matrix to stdout

**Index(row: Int, col: Int): Double** - returns the value of the data stored at the given indices

**GetSize: Array[Int]** - returns the size of the Matrix as an Array
