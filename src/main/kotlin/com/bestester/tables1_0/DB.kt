package tables1_0

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

public object DB {

    var db: DataSource = connect();

    /** Connecting to local db Library */
    public fun connect(): DataSource {
        val ds = HikariDataSource()
        ds.driverClassName = "com.mysql.cj.jdbc.Driver"
        ds.jdbcUrl = "jdbc:mysql://localhost:3306/tables1point0"
        ds.username = "root"
        ds.password = "admin"

        return ds
    }
}

fun main(){
    transaction(Database.connect(DB.db)) {
        //pass an object into here to create a table
        SchemaUtils.create(BasicNoteTable)
        SchemaUtils.create(ListNoteTable)
        SchemaUtils.create(ListNoteEntryTable)
    }

}

