package scala






object TypeclassInliningConfig {
  val size = 10000
}


/** A typeclass pattern. The `Utility` below takes the implicit value and uses it to implement its own methods generically.
 *  The performance gain exists on the server HotSpot VM, but is not huge.
 */
object Typeclass extends Benchmark {
  
  trait TypeClass[A] {
    def zero: A
    def lessThan(x: A, y: A): Boolean
  }

  class Utility[A](implicit val comparable: TypeClass[A]) {
    def doSomethingWith(a: A) = if (comparable.lessThan(a, comparable.zero)) a else comparable.zero
  }

  implicit val stringEvidence = new TypeClass[String] {
    def zero = ""
    def lessThan(x: String, y: String) = x.compareTo(y) < 0
  }
  
  val util = new Utility[String]
  var global = ""
  
  def snippet() {
    var i = 0
    val limit = TypeclassInliningConfig.size
    while (i < limit) {
      global = util.doSomethingWith(global)
      i += 1
    }
  }
  
}


object TypeclassInlined extends Benchmark {
  
  class StringUtility {
    def doSomethingWith(a: String) = if (a.compareTo("") < 0) a else ""
  }
  
  val util = new StringUtility
  var global = ""
  
  def snippet() {
    var i = 0
    val limit = TypeclassInliningConfig.size
    while (i < limit) {
      global = util.doSomethingWith(global)
      i += 1
    }
  }
  
}





