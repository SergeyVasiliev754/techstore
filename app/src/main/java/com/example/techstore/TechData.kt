package com.example.techstore

class TechData {
    private val techs = mutableListOf<Tech>()

    fun addTech(tech: Tech) {
        techs.add(tech)
    }

    fun deleteTech(tech: Tech) {
        techs.remove(tech)
    }

    fun getAllTechs(): List<Tech> = techs
}
