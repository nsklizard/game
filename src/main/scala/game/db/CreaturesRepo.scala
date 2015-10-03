package game.db

import io.strongtyped.active.slick.{CrudActions, ActiveRecord, EntityActions}
import io.strongtyped.active.slick.Lens._
import slick.ast.BaseTypedType
import slick.driver.PostgresDriver

/**
 * Created by lizard on 03.10.2015.
 */

case class Creature(name: String, playerId: Long, id: Option[Long] = None)

object CreaturesRepo extends EntityActions(PostgresDriver) {

  import jdbcProfile.api._

  val baseTypedType = implicitly[BaseTypedType[Id]] //

  type Entity = Creature
  type Id = Long
  type EntityTable = CreatureTable

  val tableQuery = TableQuery[CreatureTable] //

  def $id(table: CreatureTable): Rep[Id] = table.id //

  val idLens = lens { creature: Creature => creature.id } { (creature, id) => creature.copy(id = id) }


  class CreatureTable(tag: Tag) extends Table[Creature](tag, "creatures") {

    def id = column[Id]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def playerId = column[Long]("player_id")

    def * = (name, playerId, id.?) <>(Creature.tupled, Creature.unapply)

    val players = TableQuery[PlayersRepo.PlayerTable]

    def player = foreignKey("creatures_player_fk", playerId, players)(_.id)
  }


  implicit class EntryExtensions(val entity: Creature) extends ActiveRecord[Creature] {
    override val repository: CrudActions = CreaturesRepo
  }

}





