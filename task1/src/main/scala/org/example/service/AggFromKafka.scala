package org.example.service

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.KafkaConsumer

import java.io.PrintStream
import java.net.ServerSocket
import java.time.Duration
import java.util.Properties
import scala.collection.JavaConverters._

object AggFromKafka extends LazyLogging {
  def main(args: Array[String]): Unit = {
    val consumer = new KafkaConsumer[String, String](getKafkaProperties())

    val topics = List("devices")
    consumer.subscribe(topics.asJava)

    while (true) {
      val records = consumer.poll(Duration.ofMinutes(1))
      for (rec <- records.asScala) {
        logger.debug(rec.toString)
        writeToServerSocket(rec.toString)
      }
    }

  }
  def getKafkaProperties(): Properties = {
    val props = new Properties()
    val topic = "devices"
    props.put("bootstrap.servers", "localhost:9094")

    props.put(
      "key.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer"
    )
    props.put(
      "value.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer"
    )
    props.put("group.id", "devices_transfer")
    props.put("topic", topic)
    props
  }
  def writeToServerSocket(s: String) = {
    val ps = getServerOutStream()
    ps.println(s)
    ps.close()
  }
  def getServerOutStream(): PrintStream = {
    val server = new ServerSocket(9999)
    val conn = server.accept()
    val outstream = new PrintStream(conn.getOutputStream)
    outstream
  }
}
