package com.example.ob.repo
import com.example.ob.R
import com.example.ob.data.Card
import com.example.ob.utils.Resource

class CardRepositoryImpl: CardRepository {
    override suspend fun getCards(): Resource<List<Card>> {
        return Resource.Success(
            listOf(
                Card(1, R.drawable.aubergine, R.raw.onion, isPhoto = true),
                Card(2, R.drawable.onion, R.raw.onion, isPhoto = true),
                Card(3, R.drawable.carrot, R.raw.onion, isPhoto = true),
                Card(4, R.drawable.maize, R.raw.onion, isPhoto = true),
                Card(5, R.drawable.pumpkin, R.raw.onion, isPhoto = true)
            ).shuffled()
        )
    }

}