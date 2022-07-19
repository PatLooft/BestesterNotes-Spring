package tables1_0.cli

import tables1_0.DB
import tables1_0.createBasicNote


class CLI {
}

//The testing interface for creating notes (and deleting and modifying, but that is down the line)
fun main(){
    //TODO: 12/19/2021 08:30ish pm: get this basic note creating process into a loop

    var db = DB.connect()

    //Ask the user what type of note they would like and store it asn int
//    var userEntry = selectNoteType()
    //TODO: 12/20/2021 10:38pm: find way to make this less ugly, maybe use notereader to store the table you want? or figure out how to make a util class

    //TODO: 12/19/2021 08:56pm (1/2): gonna need a method to get the name and description for this note as well. Interface might be good for this on each of the entities
    //TODO: 12/19/2021 08:56pm (2/2): could make an CaptureInput object with the helper methods (prompts) in that class
            //that being said I can't say I am that eager to get the prompts working, I'd rather get something in the db tbh

/*    //FIRST ATTEMPT AT TESTING THIS BITCH
    var noteParamsMap = HashMap<String, String>()
    val sampleNoteName = "Name - testing"
    val sampleNoteDesc = "Description - testing"
    val sampleNoteTags = "tag 1, testing, tag 2"
    noteParamsMap.put("name", sampleNoteName)
    noteParamsMap.put("description", sampleNoteDesc)
    noteParamsMap.put("tags", sampleNoteTags)

    var result = createNote(1, noteParamsMap)

    print(result)*/
}


                                ////////////////////////////////////////
                               //          HELPER FUNCTIONS          //
                              ////////////////////////////////////////

/** Request the user to enter a note type, and return the int that it is*/
fun selectNoteType() : Int{
    //TODO: this should be an enumeration of all the notes, could automate with any newclass having note in the name gets added or some shiz
    val noteTypeList = mutableListOf("1) Basic Note", "2) List Note")
    val userInputNoteType : String
    println("Please enter the number of the note type you would like to create: ")
    noteTypeList.forEach {
        println(it)
    }
    userInputNoteType = readLine()!!
    return userInputNoteType.toInt()
}

/* TODO: 12/19/2021 08:46pm: this is the first time we will be running into an issue with extending classes.
    If I can not return a parent object of all notes, then this will probably just return an id or something similar
 */
fun createNote(noteSelection : Int, noteCreationArgs : HashMap<String, String>) : Int {
    /* starting with a basicNote and moving on from there*/
    val noteName = noteCreationArgs.get("name")                     ?: "test name"
    val noteDescription = noteCreationArgs.get("description")       ?: "test desc"
//    val noteTags = noteCreationArgs.get("tags")                     ?: "test tag"

    when(noteSelection){
        1 -> return createBasicNote(noteName, noteDescription)
    }

    return -1
}