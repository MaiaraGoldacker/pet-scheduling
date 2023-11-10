package br.com.pets.schedulling.service

import br.com.pets.schedulling.dao.PetServiceDao
import br.com.pets.schedulling.domain.PetService

class PetServiceServiceImpl {

    private val dao = PetServiceDao()

    fun create(entity: PetService) {
        dao.create(entity)
    }

    fun update(id: Long, entity: PetService) {
        dao.update(id, entity)
    }

    fun delete(id: Long) {
        dao.delete(id)
    }

    fun getById(id: Long): PetService? {
        return dao.getById(id)
    }

}