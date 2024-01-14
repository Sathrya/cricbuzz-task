package com.example.sneakerapp.domain

import com.example.sneakerapp.data.Sneaker
import com.example.sneakerapp.data.Sneakeritem

class GetSneakersUseCase (private val repo: SneakerRepo) {

    suspend fun execute() : Sneaker {
        return repo.getAllSneakers()
    }
}