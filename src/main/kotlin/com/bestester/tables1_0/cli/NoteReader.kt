package tables1_0.cli

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import tables1_0.BasicNote
import tables1_0.DB
import javax.sql.DataSource

public class NoteReader {
    //TODO: 12/20/2021 10:30pm: find a way to abstract this so we can call any class given the note type
    public fun retrieveBasicNoteList(){
        var db: DataSource = DB.connect();
        transaction(Database.connect(DB.db)) {
            val basicNoteTableContents = BasicNote.all()
            println("ID:\t\tNAME:")
            basicNoteTableContents.forEach {
                //TODO: 12/20/2021 10:30pm: string formatting so notes look pretty
                println("${it.id}\t\t${it.name}")
            }
        }
        println("")

    }

}