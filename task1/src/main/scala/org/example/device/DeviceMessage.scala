package org.example.device

import io.circe._
import io.circe.generic.JsonCodec
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._

case class DeviceMessage(data: DeviceData, time: String) {
  implicit val DeviceDecoder: Decoder[DeviceMessage] =
    deriveDecoder[DeviceMessage]
  implicit val DeviceEncoder: Encoder[DeviceMessage] =
    deriveEncoder[DeviceMessage]
}

case class DeviceData(deviceId: String, tempeature: Int, localtion: Location) {
  implicit val decoderDeviceData: Decoder[DeviceData] = deriveDecoder
  implicit val encoderDeviceData: Encoder[DeviceData] = deriveEncoder
}

case class Location(latitude: String, longitude: String) {
  implicit val decodeLocation: Decoder[Location] = deriveDecoder
  implicit val encodeLocation: Encoder[Location] = deriveEncoder
}
