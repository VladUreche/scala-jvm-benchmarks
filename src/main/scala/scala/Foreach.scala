package scala





object ForeachConfig {
  val size = 10000
}


/** This benchmark tests the VMs ability to inline the `foreach` loop at the callsite,
 *  and subsequently inline the corresponding closure.
 *  The comparison is made against a simple `while` loop which is exactly what the `foreach`
 *  method in the `Range` class contains.
 */
object Foreach extends Benchmark {
  
  var globalsum = 0
  
  def snippet() {
    var sum = 0
    for (i <- 0 until ForeachConfig.size) {
      sum += i
    }
    globalsum = sum
  }
  
}


object While extends Benchmark {
  
  var globalsum = 0
  
  def snippet() {
    var sum = 0
    var i = 0
    val limit = ForeachConfig.size
    while (i < limit) {
      sum += i
      i += 1
    }
    globalsum = sum
  }
  
}
