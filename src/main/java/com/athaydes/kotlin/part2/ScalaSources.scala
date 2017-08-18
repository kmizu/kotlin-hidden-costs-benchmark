package com.athaydes.kotlin.part2

import org.openjdk.jmh.logic.BlackHole

object ScalaSources {

  def runLocalFunctionCapturingLocalVariable(a: Int): Int = {
    someMath(a)
  }

  def runLocalFunctionWithoutCapturingLocalVariable(a: Int): Int = {
    someMath2(a)
  }

  def someMath(a: Int): Int = {
    def sumSquare(b: Int) = (a + b) * (a + b)

    sumSquare(1) + sumSquare(2)
  }

  def someMath2(a: Int): Int = {
    def sumSquare(a: Int, b: Int) = (a + b) * (a + b)

    sumSquare(a, 1) + sumSquare(a, 2)
  }

  def sayHello(who: String, blackHole: BlackHole) = blackHole.consume(f"Hello $who")

  def runPrintDouble(blackHole: BlackHole)(values: Array[Int]): Unit = {
    printDouble(blackHole, values:_*)
  }

  def printDouble(blackHole: BlackHole, values: Int*): Unit = {
    for (value <- values) {
      blackHole.consume(value)
    }
  }
}
