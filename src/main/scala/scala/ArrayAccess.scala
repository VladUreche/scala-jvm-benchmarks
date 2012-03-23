package scala






object ArrayAccessConfig {
  val size = 10000
}


/** This benchmark tests the VMs ability to inline the `Runtime.array_apply` call
 *  which performs array application for generic arrays (contains a pattern match
 *  on the array type).
 */
object ArrayAccessGeneric extends Benchmark {
  
  var globalsum = 0
  val array = new Array[AnyRef](ArrayAccessConfig.size)
  
  def snippet() {
    accessloop(array)
  }
  
  def accessloop[T](array: Array[T]) {
    var i = 0
    var sum = 0
    val limit = ArrayAccessConfig.size
    while (i < limit) {
      if (array(i) != null) sum = 1
      i += 1
    }
    globalsum = sum
  }
  
}


object ArrayAccessSpecial extends Benchmark {
  
  var globalsum = 0
  val array = new Array[AnyRef](ArrayAccessConfig.size)
  
  def snippet() {
    accessloop(array)
  }
  
  def accessloop(array: Array[AnyRef]) {
    var i = 0
    var sum = 0
    val limit = ArrayAccessConfig.size
    while (i < limit) {
      if (array(i) != null) sum = 1
      i += 1
    }
    globalsum = sum
  }
  
}





