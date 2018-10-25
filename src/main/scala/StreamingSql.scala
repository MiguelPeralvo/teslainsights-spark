//import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{ForeachWriter, Row}

object StreamingSql {

  def main(args: Array[String]) {

    //Create a SparkContext to initialize Spark
    val spark = SparkSession
      .builder
      .appName("StreamingSql")
      .config("spark.master", "local")
      .getOrCreate()


    import spark.implicits._

    // Create DataFrame representing the stream of input lines from connection to host:port
    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()


    print(spark.sparkContext.version)

    val df = spark.read.format("csv").option("header", "true").load("data/initial/*.csv.gz")

    val converted_df = df.withColumn("N0", df["N0"].cast('float')).withColumn(
      "N1", df["N1"].cast('double')).withColumn("N2", df["N2"].cast('double')).withColumn(
      "N3", df["N3"].cast('double')).withColumn("N4", df["N4"].cast('double')).withColumn(
      "N5", df["N5"].cast('double')).withColumn("N6", df["N6"].cast('double')).withColumn(
      "N7", df["N7"].cast('double')).withColumn("N8", df["N8"].cast('double')).withColumn(
      "N9", df["N9"].cast('double'))

    schema = converted_df.schema

    val query = lines.writeStream.foreach(
      new ForeachWriter[Row] {

        def open(partitionId: Long, version: Long): Boolean = {
          // Open connection
          true
        }

        def process(record: Row) = {
          // Write string to connection
          println(record.getString(0))
        }

        def close(errorOrNull: Throwable): Unit = {
          // Close the connection
        }
      }
    ).start()


    query.awaitTermination()
  }

}