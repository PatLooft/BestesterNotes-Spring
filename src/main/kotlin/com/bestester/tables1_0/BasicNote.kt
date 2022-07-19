package tables1_0

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction


object BasicNoteTable : IntIdTable() {
//    val sequelId = integer("sequel_id").uniqueIndex().autoIncrement()
    val name = varchar("name", 128)
    val description = varchar("description", 4096)
}

class BasicNote(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BasicNote>(BasicNoteTable)

    //    var sequelId by BasicNoteTable.sequelId // this isn't really needed, as it is just a duplicate of the id in sql
    var name by BasicNoteTable.name
    var description by BasicNoteTable.description

}
                                      ////////////////////////////////////////
                                     //   <>    CREATION FUNCTIONS    <>   //
                                    ////////////////////////////////////////

/** Creates a new basic note in the BasicNote table and returns the ID as an int.
 *  If object is not created, returns -1
 */
public fun createBasicNote(noteName : String, noteDesc : String): Int{
    var id : Int = -1

    transaction(Database.connect(DB.db)) {
        //pass an object into here to create a table
        id = BasicNoteTable.insertAndGetId {
            it[name] = noteName
            it[description] = noteDesc
        }.value
    }

    return id
}