package com.example.ob.data

interface CardRepository {
    suspend fun getCards(): List<Card>
}