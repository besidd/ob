package com.example.ob.repo

import com.example.ob.data.Card
import com.example.ob.utils.Resource

interface CardRepository {
    suspend fun getCards(): Resource<List<Card>>
}