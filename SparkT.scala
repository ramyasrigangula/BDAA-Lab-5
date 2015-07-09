import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by Venu on 7/8/15.
 */
object SparkT {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("Hiii").setMaster("local");

    val sc = new SparkContext(conf)



    val texts = sc.textFile("src/main/resources/Test")

    val text1 = sc.textFile("src/main/resources/Testing")

    val rdd = Seq(texts, text1)

    val TestRDD = sc.union(texts, text1)


    val counts = TestRDD.flatMap(l=> l.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)

    val sortedData=  counts.sortBy(c=> c._2, false)

    val topValues = sortedData.take(5)

    topValues.foreach(println)

    //counts.saveAsTextFile("src/main/resources/testing")
    //val sortedValues = counts.sortBy()


  }

}
