package br.com.pets.schedulling.dao

interface DAO<T>  {
    fun getById(id: Int): T?
    fun save(entity: T)
    fun update(id: Int, entity: T)
    fun deleteAll()
}