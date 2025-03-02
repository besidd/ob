package com.example.ob.data
import com.example.ob.R

class CardRepositoryImpl: CardRepository {
    override suspend fun getCards(): List<Card> {
        return listOf(
            Card(1, R.drawable.onion_photo, R.raw.onion, true),
            Card(1, R.drawable.onion_photo, R.raw.onion, true),
            Card(1, R.drawable.onion_photo, R.raw.onion, true),
            Card(1, R.drawable.onion_photo, R.raw.onion, true)
        )
    }

}