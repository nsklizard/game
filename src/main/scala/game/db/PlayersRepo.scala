package game.db

import io.strongtyped.active.slick.{CrudActions, ActiveRecord, EntityActions}
import io.strongtyped.active.slick.Lens._
import slick.ast.BaseTypedType
import slick.driver.PostgresDriver
import slick.driver.PostgresDriver.api._

/**
 * Created by lizard on 02.10.2015.
 */

case class Player(name: String, login: String, password: String, id: Option[Long] = None)

object PlayersRepo extends EntityActions(PostgresDriver) {

  import jdbcProfile.api._

  //    import slick.driver.PostgresDriver.api._


  val baseTypedType = implicitly[BaseTypedType[Id]] //

  type Entity = Player
  type Id = Long
  type EntityTable = PlayerTable

  val tableQuery = TableQuery[PlayerTable] //

  def $id(table: PlayerTable): Rep[Id] = table.id //

  val idLens = lens { player: Player => player.id } { (player, id) => player.copy(id = id) }


  class PlayerTable(tag: Tag) extends Table[Player](tag, "players") {

    def id = column[Id]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def login = column[String]("login")

    def password = column[String]("password")

    def * = (name, login, password, id.?) <>(Player.tupled, Player.unapply)
  }

  /*def findByName(name: String): DBIO[Seq[Player]] = {
    tableQuery.filter(_.name === name).result
  }*/


  implicit class EntryExtensions(val entity: Player) extends ActiveRecord[Player] {
    override val repository: CrudActions = PlayersRepo
  }
}
