package game

import akka.actor.{ActorSystem, Props}


import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}
/**
 * Created by lizard on 22.09.2015.
 */
object GameStarter extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("game")

  // create and start our service actor
  val service = system.actorOf(Props[RestServiceActor], "rest-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}
