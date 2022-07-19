package tables1_0.TestDataAndParsing

import tables1_0.createBasicNote
import java.io.File
import java.io.FileNotFoundException

class TestDataParser(fileToParsePath: String) {
    val file: File = File(fileToParsePath)
    var contents = this.fileToString()
    val noteMap = HashMap<String, String>()

    //I'm thinking creating the objects as the file moves along would be the best bet
    //first we gotta make sure it is parsing right tho, then we can send it off to the db
    //well do this the easy way by loading the file into a string
    /** call this to grab the contents of the file in the variable 'file'.
     * @Returns content of file as a String */
    fun fileToString() : String{
        if(!file.exists()){
            throw FileNotFoundException()
        }

        return file.readText()
    }

    fun parseFileStringIntoHashMap() : HashMap<String, String>{
        var rows = contents.split("\n")

        var splitRow : List<String>
        var name : String
        var desc : String
        println("Row count = ${rows.size}")

        rows.forEach{
            //this will split between the note name and description, often by concert location and year for the dead
            //TODO: fix this as it is only picking up one char at a time, figure out how to pass a string
            splitRow = it.split("|")
            if(splitRow.size >= 2){
                name = splitRow[0]
                desc = splitRow[1]
                println("NAME: ${name}\n\tDESC: ${desc}")
                noteMap.put(name, desc)
            }
        }
        return noteMap
    }

    fun createNoteFromNoteMap(){
        val noteMapKeys = noteMap.keys
        var id = 0
        noteMapKeys.forEach{
            id = createBasicNote(it, noteMap.getOrDefault(it, "desc not found"))
            println("ID: ${id}")
        }
    }
}

fun main(){
    //code for parsing GratefulDeadTestData.txt, uses the TestDataParser object
    var filePath = "./src/main/kotlin/tables1_0/TestDataAndParsing/testData/GratefulDeadTestData.txt"
    val parser = TestDataParser(filePath)

    println(parser.fileToString())
    println("Split map size: "+parser.parseFileStringIntoHashMap().size)
    parser.createNoteFromNoteMap()
}