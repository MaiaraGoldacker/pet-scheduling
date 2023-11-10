package br.com.pets.schedulling.dao

import br.com.pets.schedulling.domain.PetService
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PetServiceDao {

    object PetServiceTable : Table("public.pet_service") {
        var id = long("id").autoIncrement()
        var name = varchar("name", 50)
        var description = varchar("description", 500)
        var serviceType = varchar("service_type", 20)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        Database.connect(
            "jdbc:postgresql://localhost:5432/postgres?currentSchema=public;",
            "org.postgresql.Driver",
            "postgres",
            "postgres")

        transaction {
            SchemaUtils.create(PetServiceTable)
        }
    }

    fun getById(id: Long): PetService? {
        return transaction {
            PetServiceTable.select { PetServiceTable.id eq id }
                .map { PetService(it[PetServiceTable.name], it[PetServiceTable.description]) }
                .singleOrNull()
        }
    }

    fun create(entity: PetService) {
        transaction {
            PetServiceTable.insert {
                it[name] = entity.name
                it[description] = entity.description
                it[serviceType] = entity.type.toString()
            }
        }
    }

    fun update(id: Long, entity: PetService) {
        transaction {
            PetServiceTable.update({PetServiceTable.id eq id}) {
                it[name] = entity.name
                it[description] = entity.description
                it[serviceType] = entity.type.toString()
            }
        }
    }

    fun delete(id: Long) {
        transaction {
            PetServiceTable.deleteAll()
        }
    }
}