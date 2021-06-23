package org.example.service

import com.typesafe.scalalogging.LazyLogging
import io.circe.Json
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.example.device.MessageProducer

import java.io.DataOutputStream
import java.net.Socket
import java.util.Properties
import scala.util.{Failure, Success, Try}

object ScalaClient extends LazyLogging {

  def main(args: Array[String]): Unit = {

    send(MessageProducer().getDeviceMessage())
    //sendToKafka(MessageProducer().getDeviceMessage())

  }
  def send(message: Json): Unit = {
    val res = Try {
      val s = new Socket("localhost", 9999)
      val dout = new DataOutputStream(s.getOutputStream)
      dout.writeUTF(message.toString())
      dout.flush()
    }
    res match {
      case Success(value) => logger.info("Successfully send message " + value)
      case Failure(exception) =>
        logger.error("Error sending message " + exception)
    }
  }
  def sendToKafka(message: Json): Unit = {
    val res = Try {
      val props = new Properties()
      val topic = "devices"
      props.put("bootstrap.servers", "localhost:9094")

      props.put(
        "key.serializer",
        "org.apache.kafka.common.serialization.StringSerializer"
      )
      props.put(
        "value.serializer",
        "org.apache.kafka.common.serialization.StringSerializer"
      )
      props.put("group.id", "devices_transfer")

      val producer = new KafkaProducer[String, String](props)
      val record =
        new ProducerRecord[String, String](topic, "key", message.toString())
      producer.send(record)
      producer.flush()
      producer.close()
    }
    res match {
      case Success(value) => logger.info("Message send to kafka " + value)
      case Failure(ex)    => logger.info("Fail to send message to kafka " + ex)
    }
  }
}
