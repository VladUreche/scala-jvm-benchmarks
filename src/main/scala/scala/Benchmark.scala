package scala






/** Runs the snippet the specified number of times. */
abstract class Benchmark {
  
  val iterations = sys.props("iterations").toInt
  
  def main(args: Array[String]) {
    val reps = if (args.length == 0) 1 else args(0).toInt
    
    val times = for (i <- 0 until reps) yield {
      val startTime = System.currentTimeMillis()
      run()
      val stopTime = System.currentTimeMillis()
      
      stopTime - startTime
    }
    
    println(this.getClass.getSimpleName + ": " + times.mkString("\t"))
  }
  
  def run() {
    var i = 0
    while (i < iterations) {
      snippet()
      i += 1
    }
  }
  
  def snippet(): Unit
  
}
