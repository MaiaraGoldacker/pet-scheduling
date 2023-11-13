package br.com.pets.schedulling.service

import br.com.pets.schedulling.dao.PetServiceDao
import br.com.pets.schedulling.domain.PetService

class PetServiceServiceImpl {

    private val dao = PetServiceDao()

    fun create(entity: PetService) {
        dao.save(entity)
    }

    fun update(id: Int, entity: PetService) {
        dao.update(id, entity)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    fun getById(id: Int): PetService? {
        return dao.getById(id)
    }

}