package benchmarks

import org.scalatest.FunSpec

class GlobMatcherBenchSpec extends FunSpec {

  describe("globStringMatches") {
    it("should return true") {
      assert(new GlobMatcherBench().globStringMatches)
    }
  }

  describe("regexMatcherMatches") {
    it("should return true") {
      assert(new GlobMatcherBench().regexMatcherMatches)
    }
  }
}


class GlobMatcherMultiBenchSpec extends FunSpec {

  describe("globStringMatches") {
    it("should return false") {
      assert(new GlobMatcherMultiBench().globStringMatches === false)
    }
  }

  describe("regexMatcherMatches") {
    it("should return false") {
      assert(new GlobMatcherMultiBench().regexMatcherMatches === false)
    }
  }

  describe("regexPipeMatches") {
    it("should return false") {
      assert(new GlobMatcherMultiBench().regexPipeMatches === false)
    }
  }
}
