name := "perceptron_spark"

version := "1.0"

scalaVersion := "2.10.4"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.1",
  "org.apache.commons" % "commons-math3" % "3.0"
)
        