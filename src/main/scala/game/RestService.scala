package game

import akka.actor.Actor
import spray.routing._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class RestServiceActor extends Actor with RestService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait RestService extends HttpService{
  val myRoute={
    import game.RestAPI._
    import game.Skills

    path("add"){
      post{
        entity(as[Login]){tr=>
          complete{
            "Create creature received "+tr.login
          }
        }
      }
    }~path("getSkills"){
      get{
        entity(as[GetSkills]){ s =>
          complete{
          }
        }
      }
    }
  }
}
