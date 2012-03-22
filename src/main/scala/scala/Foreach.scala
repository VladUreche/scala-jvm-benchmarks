package scala





object Foreach extends Benchmark {
  
  var globalsum = 0
  
  def snippet() {
    var sum = 0
    for (i <- 0 until 10000) {
      sum += i
    }
    globalsum = sum
  }
  
}
