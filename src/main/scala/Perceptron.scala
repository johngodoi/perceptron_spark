import org.apache.commons.math3.analysis.function.Sigmoid

/**
  * @author john
  * @since 25/05/17
  */
class Perceptron (n:Double, weight:List[Double], teta:Double) {

  val activationFunction = new Sigmoid()

  def train(sample:List[Double])={
    activationFunction.value(cartesianProduct(sample,weight)-teta)
  }

  def cartesianProduct(sample: List[Double], weight:List[Double]): Double = {
    (sample, weight) match {
      case (s::ss, w::ww) => s*w+cartesianProduct(ss,ww)
      case (Nil,Nil) => 0
    }
  }
}
