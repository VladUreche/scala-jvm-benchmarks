package scala






object ClosureConfig {
  val size = 10000
}


object stm {
  class IntRef {
    private var _value = 0
    def apply() = _value
    def :=(v: Int) = _value = v
  }
  
  def atomic[T](body: =>T) = body
  def intref = new IntRef
}


/** This benchmark tests the VMs ability to inline closures.
 */
object Closure extends Benchmark {
  import stm._
  
  var globalsum = 0
  val value = intref
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = ClosureConfig.size
    while (i < limit) {
      sum += atomic {
        value := value() + 1
        value()
      }
      i += 1
    }
    globalsum = sum
  }
  
}


object ClosureInlined extends Benchmark {
  import stm._
  
  var globalsum = 0
  val value = intref
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = ClosureConfig.size
    while (i < limit) {
      value := value() + 1
      sum += value()
      i += 1
    }
    globalsum = sum
  }
  
}


