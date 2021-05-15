package org.example.service

import io.circe.{Json, JsonObject}
import org.example.device.{DevGenerator, MessageProducer}
import java.io._
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.Socket
import java.net.UnknownHostException
import java.util.Scanner

object JsonSender {
  def main(args: Array[String]): Unit = {
    println("Hello")
  }
  def sendJson(message: Json): Unit = {
    val jsObject = MessageProducer().getDeviceMessage()

  }

}
