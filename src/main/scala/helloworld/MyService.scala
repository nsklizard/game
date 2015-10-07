package helloworld

import akka.actor.Actor
import spray.httpx.SprayJsonSupport
import spray.json._
import spray.routing._
//import TestRequestJSON._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


case class TestRequest(id: Int)

object TestRequestJSON extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val barFormat = jsonFormat1(TestRequest)
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {


  val myRoute ={
    import TestRequestJSON._

      path("add") {
        post {
          entity(as[TestRequest]) { tr =>
            complete {
              "Order received" + tr.id;
            }
          }
        }
      }
  }
}
