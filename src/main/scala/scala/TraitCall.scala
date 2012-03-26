package scala






object TraitCallConfig {
  val size = 100000
}


trait Base {
  private var x = 0
  def foo = {
    x += 1
    x
  }
}


trait Overridden extends Base {
  private var z = 0
  override def foo = {
    z += 1
    z
  }
}


class One extends Overridden


class Two extends Overridden {
  private var y = 0
  def bar = {
    y += 1
    y
  }
}


/** Static calls to the implementations in the implementation
 *  trait of the class should be inlined.
 *  Hotspot does a good job here.
 */
object TraitCall extends Benchmark {
  
  var globalsum = 0
  var obj: Base = new One
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = TraitCallConfig.size
    while (i < limit) {
      sum += obj.foo
      i += 1
    }
    globalsum = sum
  }
  
}


object DirectCall extends Benchmark {
  
  var globalsum = 0
  var obj = new Two
  
  def snippet() {
    var i = 0
    var sum = 0
    val limit = TraitCallConfig.size
    while (i < limit) {
      sum += obj.bar
      i += 1
    }
    globalsum = sum
  }
  
}






