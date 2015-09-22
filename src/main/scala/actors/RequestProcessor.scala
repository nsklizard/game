package actors

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * Created by lizard on 20.09.2015.
 */

case class Coords(x:Int, y:Int)
case class Player(id:Int)

case class Settle(c : Coords, p: Player)
case class CreatePlayer(name:String, skillMap : Map[String, Int])
case class GetSkills()

class RequestProcessor extends Actor{
  override def receive: Receive = {
    case Settle(c,p) => ???
  }
}
