package br.com.pets.schedulling.domain
import kotlinx.serialization.Serializable

@Serializable
data class PetService(val name: String, val description: String, val type: PetServiceType){

    override fun toString(): String {
        return "PetService(name='$name', description='$description', type=$type)"
    }
}

