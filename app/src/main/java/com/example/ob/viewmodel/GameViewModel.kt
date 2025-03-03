package com.example.ob.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ob.data.Card
import com.example.ob.repo.CardRepository
import com.example.ob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor( private var repository: CardRepository) : ViewModel() {
    private var _cards: MutableLiveData<Resource<List<Card>>> = MutableLiveData()
    val cards: LiveData<Resource<List<Card>>>
        get() = _cards

    private val _selectedCards = mutableStateListOf<Card>()
    val selectedCards: List<Card> get() = _selectedCards

    fun getCards() {
        viewModelScope.launch {
            _cards.value = repository.getCards()
        }
    }

    fun selectCards(id: Int) {

    }
}