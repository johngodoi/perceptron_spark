import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by jgodoi on 5/11/2017.
  */
object Starter extends App {

  val conf = new SparkConf().setAppName("workdCount").setMaster("local")
  val sc = new SparkContext(conf)
    sc.setLogLevel("OFF")
  val nums = sc.parallelize(List(1,2,3,4))
  val squared = nums.map(x=>x*x)

  println(squared.collect().mkString(","))
  val file = sc.textFile("train.csv")


  val list = file.map(line => convertToDoubleList(line)).collect().toList
  //list.foreach(line => println(line.mkString(",")))
  val tuples = list.filter(line => line != Nil)
    .map(line => line match {
    case (y :: xs) => (y, xs)
  }).toList

  val perceptron = new Perceptron(learningRate = 0.01,weights=Array(0.1,-0.1,0.1,0.1,-0.1,0.1,0.1,-0.1,-0.1,0.1,0.1),teta=0.01,acceptedError=0.000002,iterations=1000)
  perceptron.train(tuples)

  def convertToDoubleList(line: String): List[Double] = {
    val split = line.split(",")
    var doubleList = List[Double]()
    for(i <- 0 to split.length-1){
      try {
        val d = split(i).toDouble
        doubleList=d::doubleList
      } catch {
        case nfe: NumberFormatException => {
          println(nfe.toString)
        }
      }
    }
    println(doubleList.length)
    println(doubleList.mkString(","))
    doubleList
  }

}
