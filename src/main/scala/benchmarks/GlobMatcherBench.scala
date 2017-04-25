package benchmarks

import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

import functions.Glob
import org.openjdk.jmh.annotations._

@State(Scope.Thread)
abstract class SimpleData {

  val name = "ajojfewaojepaejahfiwaoejablablahaha"

  lazy val patternGlob = "a*blablahaha"
  lazy val patternString = "a.*blablahaha"
  lazy val patternPattern = Pattern.compile(patternString)
}

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 3)
@BenchmarkMode(Array(Mode.Throughput))
@Fork(1)
class GlobMatcherBench extends SimpleData {

  @Benchmark
  def globStringMatches: Boolean = Glob.matches(patternGlob, name)

  @Benchmark
  def regexStringMatches: Boolean = name.matches(patternString)

  @Benchmark
  def regexMatcherMatches: Boolean = patternPattern.matcher(name).matches()
}

@State(Scope.Thread)
abstract class MultiData {

  val name = "jjojfewaojepaejahfiwaoejablablahaha"

  lazy val patternsGlob = Seq(
    "a*z",
    "b*z",
    "c*z",
    "d*z",
    "e*z",
    "f*z",
    "g*z",
    "h*z",
    "i*z",
    "j*z"
  )

  lazy val patternsString = Seq(
    "a.*z",
    "b.*z",
    "c.*z",
    "d.*z",
    "e.*z",
    "f.*z",
    "g.*z",
    "h.*z",
    "i.*z",
    "j.*z"
  )

  lazy val patternsPattern = Seq(
    Pattern.compile("a.*z"),
    Pattern.compile("b.*z"),
    Pattern.compile("c.*z"),
    Pattern.compile("d.*z"),
    Pattern.compile("e.*z"),
    Pattern.compile("f.*z"),
    Pattern.compile("g.*z"),
    Pattern.compile("h.*z"),
    Pattern.compile("i.*z"),
    Pattern.compile("j.*z")
  )

  lazy val patternsPipe = Pattern.compile("a.*z|b.*z|c.*z|d.*z|e.*z|f.*z|g.*z|h.*z|i.*z|j.*z")
}

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 3)
@BenchmarkMode(Array(Mode.Throughput))
@Fork(1)
class GlobMatcherMultiBench extends MultiData {

  @Benchmark
  def globStringMatches: Boolean = patternsGlob.exists(Glob.matches(_, name))

  @Benchmark
  def regexStringMatches: Boolean = patternsString.exists(name.matches)

  @Benchmark
  def regexMatcherMatches: Boolean = patternsPattern.exists(_.matcher(name).matches())

  @Benchmark
  def regexPipeMatches: Boolean = patternsPipe.matcher(name).matches()
}
