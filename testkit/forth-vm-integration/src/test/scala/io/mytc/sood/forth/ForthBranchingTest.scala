package pravda.forth

import utest._
import ForthTestUtils._

object ForthBranchingTest extends TestSuite {

  def tests = Tests {

    "if must execute block if true is on top of the stack" - {
      assert( runWithoutEnviroment[Int]("""
        0 0
        eq
        if 5 then
      """) == Right(
          List(5)
      ))
    }

    "if must not execute block if false is on top of the stack" - {
      assert( runWithoutEnviroment[Int]("""
        0 1
        eq
        if 5 then
      """) == Right(
          List()
      ))
    }

  }

}