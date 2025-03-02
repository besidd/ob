package com.example.ob.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ob.data.Card
import com.example.ob.data.CardRepository
import kotlinx.coroutines.launch

class GameViewModel(private val repository: CardRepository): ViewModel() {
    private var _cards: MutableLiveData<List<Card>> = MutableLiveData()
    val cards: LiveData<List<Card>>
        get() = _cards

    private val _selectedCards = mutableStateListOf<Card>()
    val selectedCards: List<Card> get() = _selectedCards

    fun getCards() {
        viewModelScope.launch {
            repository.getCards()
        }
    }
}