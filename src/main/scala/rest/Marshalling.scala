package rest

import model.Annotation
import spray.http.{HttpEntity, MediaTypes}
import spray.httpx.marshalling.Marshaller
import spray.httpx.marshalling._
import org.json4s.native.Serialization._
import org.json4s.{DefaultFormats, Formats}
import org.json4s.Xml.{toJson, toXml}

object SpotlightMarshallers {

  implicit def json4sFormats: Formats = DefaultFormats

  val AnnotationMarshallerXml =
    Marshaller.of[Annotation](MediaTypes.`application/xml`) { (value, contentType, ctx) =>
      val Annotation(topics,text) = value
      val topicsText = topics.mkString(" | ")
      val string =  <annotation>  <text>{text}</text> <topics> {topicsText} </topics> </annotation>
      ctx.marshalTo(HttpEntity(contentType, string.toString()))
    }

  val AnnotationMarshallerJson =
    Marshaller.of[Annotation](MediaTypes.`application/json`) { (value, contentType, ctx) =>
      val Annotation(topics,text) = value
      val string =  write(value)
      ctx.marshalTo(HttpEntity(contentType, string))
    }

  implicit val AnnotationMarshaller: ToResponseMarshaller[Annotation] =
    ToResponseMarshaller.oneOf(MediaTypes.`application/json`, MediaTypes.`application/xml`)  (AnnotationMarshallerJson, AnnotationMarshallerXml)





}