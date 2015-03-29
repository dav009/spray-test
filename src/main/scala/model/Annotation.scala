package model

case class Topic(identifier:String, surfaceForm:String)
case class Annotation(topics:Seq[Topic], text:String)
