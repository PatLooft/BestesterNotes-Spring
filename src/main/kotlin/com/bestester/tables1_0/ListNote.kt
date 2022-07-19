package tables1_0

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

/** The root list note object
 *  rootBasicNote - this contains a rootnote with name and description
 *  listNoteEntries - this contains the items on the list
 */
object ListNoteTable : IntIdTable() {
    val rootBasicNote = reference("rootnote", BasicNoteTable)
//    val listNoteEntries = reference("entries", ListNoteEntryTable) //don't need this as the entries will be linked back to the table, we can make a query for it later
    //need a many to one of listNoteEntry
}

/** The setup for the ListNote table
 *
 */
class ListNote(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ListNote>(ListNoteTable)

    //foreign key to the basic note details
    var rootBasicNote by BasicNote referencedOn ListNoteTable.rootBasicNote
}

/** Each individual entry on any given list
 *  rootListNote - the note that the entries will be on
 *  listNoteEntryDesc - the actual note that will be added to the "list"
 */
object ListNoteEntryTable : IntIdTable() {
    val rootListNote = reference("rootListNote", ListNoteTable)
    val listNoteEntryDesc = varchar("description", 1024)
}

/** The setup for the ListNoteEntry table
 *
 */
class ListNoteEntry(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ListNoteEntry>(ListNoteTable)

    //foreign key to the root note (I hope)
    var rootListNote by ListNote referencedOn ListNoteEntryTable.rootListNote
    var listNoteEntryDesc by ListNoteEntryTable.listNoteEntryDesc
}