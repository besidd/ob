package com.example.ob.repo

import com.example.ob.R
import com.example.ob.data.Card

class CardRepositoryImpl : CardRepository {
    override suspend fun getCards(): List<Card> {
        return listOf(
            Card(1, R.drawable.aubergine, R.raw.aubergine, isPhoto = true),
            Card(2, R.drawable.onion, R.raw.onion, isPhoto = true),
            Card(3, R.drawable.carrot, R.raw.carrot, isPhoto = true),
            Card(4, R.drawable.maize, R.raw.maize, isPhoto = true),
            Card(5, R.drawable.pumpkin, R.raw.pumpkin, isPhoto = true)
        ).shuffled()
    }

    override suspend fun getSfxs(): List<Card> {
        return listOf(
            Card(1, R.drawable.aubergine_photo, R.raw.aubergine),
            Card(2, R.drawable.onion_photo, R.raw.onion),
            Card(3, R.drawable.carrot_photo, R.raw.carrot),
            Card(4, R.drawable.maize_photo, R.raw.maize),
            Card(5, R.drawable.pumpkin_photo, R.raw.pumpkin)
        ).shuffled()
    }
}