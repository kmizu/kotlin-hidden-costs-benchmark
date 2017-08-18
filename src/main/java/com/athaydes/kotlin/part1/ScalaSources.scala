package com.athaydes.kotlin.part1

object ScalaSources {

  def runScalaLambda(db: Database): Int = {
    val deletedRows = transaction(db) {it =>
      it.delete("Customers", null, null)
    }

    return deletedRows
  }

  @inline
  def runScalaInlinedFunction(db: Database): Int = {
    val deletedRows = inlineTransaction(db) {it =>
      it.delete("Customers", null, null)
    }
    deletedRows
  }

  def transaction(db: Database)(body: Database => Int): Int = {
    db.beginTransaction()
    try {
      val result = body(db)
      db.setTransactionSuccessful()
      result
    } finally {
      db.endTransaction()
    }
  }

  @inline
  def inlineTransaction(db: Database)(body: Database => Int): Int = {
    db.beginTransaction()
    try {
      val result = body(db)
      db.setTransactionSuccessful()
      return result
    } finally {
      db.endTransaction()
    }
  }

  def runCompanionObjectCallToPrivateConstructor(): String = {
    val myClass = MyClass.newInstance()
    myClass.helloWorld()
  }

  class MyClass private () {
    def helloWorld() = MyClass.TAG
  }
  object MyClass {
    private val TAG = "TAG"

    def newInstance() = new MyClass()
  }
}

