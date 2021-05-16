package org.example.device

import io.circe.Json
import io.circe.syntax.EncoderOps
import io.circe.generic.codec.DerivedAsObjectCodec.deriveCodec
import org.joda.time.DateTime

import java.math.MathContext
import scala.concurrent.{Await, Future}
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import org.example.service._

/**
  * This is a just json device generator example
  */
case class MessageProducer() {
  def getDeviceMessage(): Json = {

    def getBigDecimal(limit: Int) = {
      val mc = new MathContext(16)
      val r = new Random().nextInt(limit)
      val d = r + new Random().nextFloat()
      val res = new BigDecimal(new java.math.BigDecimal(d), mc)
      res.round(mc)
    }
    def getLatitude() = {
      val r = getBigDecimal(90)
      r
    }
    def getLongitude() = {
      val r = getBigDecimal(180)
      r
    }
    def getTimeDevice() = DateTime.now().getMillis / 1000

    def getTemperatue() = {
      val temperature = new Random().nextInt(200)
      temperature
    }
    def getDeivecId(): String = {
      val idArr = Array(
        "11c1310e-c0c2-461b-a4eb-f6bf8da2d21c",
        "12c1311e-c0c2-461b-a4eb-f6bf8da2d22c",
        "13c1312e-c0c2-461b-a4eb-f6bf8da2d23c"
      )
      val rnd = new Random().nextInt(3)
      idArr(rnd)
    }
    val deviceMessage = DeviceMessage(
      DeviceData(
        getDeivecId(),
        getTemperatue(),
        Location(getLatitude().toString(), getLongitude().toString())
      ),
      getTimeDevice().toString
    )
    deviceMessage.asJson
  }
}

object DevGenerator {
  def task1(): Future[Unit] = Future {
    //println(MessageProducer().getDeviceMessage())
    ScalaClient.sendToKafka(MessageProducer().getDeviceMessage())
    //ScalaClient.send(MessageProducer().getDeviceMessage())
    //ToDo send to Kafka messages
    Thread.sleep(1000)
  }

  def infiniteLoop(): Future[Unit] = {
    Future
      .sequence(List(task1(), task1(), task1()))
      .flatMap(x => infiniteLoop())
  }
  def main(args: Array[String]): Unit = {
    Await.ready(infiniteLoop(), Duration.Inf)
    //val sampleMessgage = genDeviceMessage().asJson
    //println(sampleMessgage)
  }

}
