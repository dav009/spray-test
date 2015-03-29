package annotation

import model.{Topic, Annotation}

/**
 * Created by dav009 on 29/03/15.
 */
object Extractor {

  def annotate(text:String, confidence:String): Annotation =  {
    new Annotation(List(new Topic("fakeId", "FakeSurfaceForm")), text)
  }

}
