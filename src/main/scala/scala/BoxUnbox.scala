package scala






object BoxUnboxConfig {
  val size = 10000
}


trait NumOps[T] {
  def plus(a: T, b: T): T
}


/** This benchmark tests the VMs ability to generate specialized versions
 *  of generic methods which require boxing and unboxing.
 */
object BoxUnbox extends Benchmark {
  
  var globalsum = 0
  
  implicit val intops = new NumOps[Int] {
    def plus(a: Int, b: Int) = a + b
  }
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = ArrayAccessConfig.size
    while (i < limit) {
      sum += twice(i)
      i += 1
    }
    globalsum = sum
  }
  
  def twice[T](x: T)(implicit ops: NumOps[T]) = ops.plus(x, x)
  
}


object BoxUnboxSpecial extends Benchmark {
  
  var globalsum = 0
  
  implicit val intops = new NumOps[Int] {
    def plus(a: Int, b: Int) = a + b
  }
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = ArrayAccessConfig.size
    while (i < limit) {
      sum += twice(i)
      i += 1
    }
    globalsum = sum
  }
  
  def twice(x: Int) = x + x
  
}








