package helloworld

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import slick.driver.PostgresDriver.api._
import spray.can.Http

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
 * Created by lizard on 14.09.2015.
 */
object Hello extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], "demo-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)




  val connectionUrl = "jdbc:postgresql://localhost/scala?user=postgres&password=root"
  val db = Database.forURL(connectionUrl, driver = "org.postgresql.Driver")

  try {
    val users: TableQuery[Users] = TableQuery[Users]

    val user:Users = new Users()

    println("Users:")
    db.run (users.result) onComplete {
      case Failure( t) => // logging
      case Success(res) => println(res)
    }

//    val add = DBIO.seq(
//      users +=(0, "Test2", 20L)
//    )
//    val f = db.run(add.transactionally)

  } finally db.close
}
