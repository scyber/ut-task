package org.example.service

import org.example.device.MessageProducer

import java.io.DataOutputStream
import java.net.Socket
import scala.util.Try

object ScalaClient {
  def main(args: Array[String]): Unit = {

    val s = new Socket("localhost", 7777)
    val dout = new DataOutputStream(s.getOutputStream)
    dout.writeUTF(MessageProducer().getDeviceMessage().toString())
    dout.flush()

  }
}
