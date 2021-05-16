package org.example.service

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, explode, from_json}
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

object SparkStreamFromSocket extends LazyLogging{
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().master("local[3]").appName("SocketReciver").getOrCreate()
    ss.sparkContext.setLogLevel("DEBUG")
    val read_df = ss.readStream.format("socket").option("host","127.0.0.1").option("port","7777").load()
    read_df.printSchema()
    //read_df.show()
    val schema = new StructType()
    schema.add("time", StringType,true)
      .add("deviceID", StringType,true)
      .add("temperature",IntegerType, true)
      .add("time", StringType,true)
      .add("latitude", StringType,true)
      .add("longitude",StringType,true)

    val text_df = read_df.select("value").alias("json")
    //val js_df = text_df.select(from_json(col("value"),schema))

    text_df.writeStream.format("console").start().awaitTermination()


  }
  def recive() : Unit = {
    val res = Try {

    }
    res match {
      case Success(value) => logger.info("Success recise from socket " + value)
      case Failure(exception) => logger.error("could not recive message " + exception)
    }


  }
}
