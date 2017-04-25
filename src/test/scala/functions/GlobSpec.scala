package functions

import org.scalatest.{FunSpec, Matchers}

class GlobSpec extends FunSpec with Matchers {

  describe("GlobSpec") {

    it("should matches") {
      assert(Glob.matches("abc", "abc"))
      assert(Glob.matches("abc", "ab") === false)

      assert(Glob.matches("*abc", "abc"))
      assert(Glob.matches("*abc", "xyzabc"))

      assert(Glob.matches("a*abc", "abc") === false)
      assert(Glob.matches("a*abc", "aabc"))
      assert(Glob.matches("a*abc", "aqabc"))
      assert(Glob.matches("a*abc", "aqtabc"))

      assert(Glob.matches("abc*", "abc"))
      assert(Glob.matches("abc*", "abcd"))
      assert(Glob.matches("abc*", "abcde"))

      assert(Glob.matches("abc*z", "abcde") === false)
    }
  }
}
