package org.example.service

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession

import java.io.PrintStream
import java.net.ServerSocket

object SparkStreamingToSocket extends LazyLogging {

  def main(args: Array[String]): Unit = {

    val ssc = SparkSession
      .builder()
      .master("local[*]")
      .appName("Stream-Writer")
      .getOrCreate()

    import ssc.implicits._
    val sample = Seq("adf", "sdfsd", "fsdf").toDF()

    sample.toJSON.collect().foreach(s => println(s))
    println(sample)
//    val server = new ServerSocket(9999)
//    val conn = server.accept()
//    val outstream = new PrintStream(conn.getOutputStream)
//    sample.toJSON.collect().foreach(rec => outstream.println(rec))

  }
}
