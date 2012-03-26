package scala






object GettersConfig {
  val size = 100000
}


class ScalaBaseClass {
  var x: Int = 12
}


class ScalaInheritedClass extends ScalaBaseClass


/** Getters should be inlined. Compare this benchmark to that with an equivalent Java class.
 */
object Getters extends Benchmark {
  
  var globalsum = 0
  var globalobj = new ScalaInheritedClass
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = GettersConfig.size
    val obj = globalobj
    while (i < limit) {
      sum += obj.x
      i += 1
    }
    globalsum = sum
  }
  
}


object JavaGetters extends Benchmark {
  
  var globalsum = 0
  var globalobj = new JavaBaseClass
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = GettersConfig.size
    val obj = globalobj
    while (i < limit) {
      sum += obj.x
      i += 1
    }
    globalsum = sum
  }
  
}





