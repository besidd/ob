package com.example.ob.repo

import com.example.ob.data.Card

interface CardRepository {
    suspend fun getCards(): List<Card>
    suspend fun getSfxs(): List<Card>
}