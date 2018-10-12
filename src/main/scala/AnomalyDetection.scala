//import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession

object AnomalyDetection {

  def main(args: Array[String]) {

    //Create a SparkContext to initialize Spark
    val spark = SparkSession
      .builder
      .appName("AnomalyDetection")
      .config("spark.master", "local")
      .getOrCreate()

//    import spark.implicits._
    val sc = spark.sparkContext

    // Load the text into a Spark RDD, which is a distributed representation of each line of text
    val textFile = sc.textFile("src/main/resources/sample_twitter_posts_20180910.json")

    //word count
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.foreach(println)
    System.out.println("Total words: " + counts.count());
    counts.saveAsTextFile("/tmp/sample_twitter_posts_20180910_word_count.txt")
  }

}