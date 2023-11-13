package br.com.pets.schedulling.dao

import br.com.pets.schedulling.domain.PetService
import br.com.pets.schedulling.domain.PetServiceType
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PetServiceDao : DAO<PetService> {

    object PetServiceTable : Table("pet_service") {
        val id = integer("id").uniqueIndex().autoIncrement()
        val name = varchar("name", 50)
        val description = varchar("description", 500)
        val serviceType = varchar("service_type", 20)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        Database.connect(
            "jdbc:postgresql://localhost:5432/postgres?currentSchema;",
            "org.postgresql.Driver",
            "postgres",
            "postgres")

        transaction {
            SchemaUtils.create(PetServiceTable)
        }
    }

    override fun update(id: Int, entity: PetService) {
        transaction {
            PetServiceTable.update({PetServiceTable.id eq id}) {
                it[name] = entity.name
                it[description] = entity.description
                it[serviceType] = entity.type.toString()
            }
        }
    }

    override fun getById(id: Int): PetService? {
        return transaction {

            PetServiceTable.select { PetServiceTable.id eq id }
                .map {
                    PetService(
                        it[PetServiceTable.name],
                        it[PetServiceTable.description],
                        convert(it[PetServiceTable.serviceType])
                    ) }
                .singleOrNull()
        }
    }

    override fun save(entity: PetService) {
        transaction {
            PetServiceTable.insert {
                it[name] = entity.name
                it[description] = entity.description
                it[serviceType] = entity.type.toString()
            }
        }
    }

    override fun deleteAll() {
        transaction {
            PetServiceTable.deleteAll()
        }
    }

    private fun convert( type:String ) : PetServiceType{
        return  enumValueOf<PetServiceType>(type)
    }
}