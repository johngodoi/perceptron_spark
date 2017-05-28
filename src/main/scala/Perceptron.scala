import org.apache.commons.math3.analysis.function.Sigmoid

/**
  * @author john
  * @since 25/05/17
  */
class Perceptron (learningRate:Double, weights:Array[Double], teta:Double, acceptedError:Double, iterations:Int) {

  val activationFunction = new Sigmoid()
  var error=Double.MaxValue

  def train(samples:List[(Double,List[Double])]):AnyRef={
    var internalWeights=weights
    var i = 1
    do {
      println(s"epoch ${i}")
      println(s"error ${error} before epoch")
      println("weights before epoch: "+internalWeights.mkString(","))
      samples.foreach(sample => sample match {
        case (eo, ss) => {
          internalWeights=trainForOneSample(ss, eo, internalWeights)
        }
      })
      println(s"epoch ${i}")
      println(s"error ${error} after epoch")
      println("weights after epoch: "+internalWeights.mkString(","))
      i=i+1
    } while (i<=iterations && Math.abs(error) >= acceptedError)
    AnyRef
  }

  def trainForOneSample(sample:List[Double], expectedOutput:Double, weightsP:Array[Double]):Array[Double]={

    val activationValue = activationFunction.value(cartesianProduct(sample,weightsP.toList)-teta)
    println(s"expectedOutput: ${expectedOutput}")
    println(s"activationValue: ${activationValue}")
    println("weights before update: "+weightsP.mkString(","))
    error = expectedOutput-activationValue
    if(Math.abs(error)>=acceptedError){
      for(i <- 0 to weightsP.length-1) {
        weightsP(i) = {
          weightsP(i)+learningRate*error*expectedOutput
        }
      }
    }
    println("Updated weights: "+weightsP.mkString(","))
    weightsP
  }

  def cartesianProduct(sample: List[Double], weight:List[Double]): Double = {
    (sample, weight) match {
      case (s::ss, w::ww) => s*w+cartesianProduct(ss,ww)
      case (Nil,Nil) => 0
    }
  }


}
