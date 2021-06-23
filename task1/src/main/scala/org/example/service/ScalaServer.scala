package org.example.service

import java.net.{ServerSocket, Socket}

object ScalaServer {
  def main(args: Array[String]): Unit = {
    val ss = new ServerSocket(7777)
    while (true) {
      ss.accept()

    }
  }

}
