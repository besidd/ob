package com.example.ob.viewmodel

import android.app.Application
import androidx.annotation.RawRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.example.ob.R
import com.example.ob.data.Card
import com.example.ob.repo.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private var repository: CardRepository,
    context: Application
) : AndroidViewModel(context) {
    private var _cards: MutableStateFlow<List<Card>> = MutableStateFlow(emptyList())
    val cards: StateFlow<List<Card>>
        get() = _cards

    private var _sfxs: MutableStateFlow<List<Card>> = MutableStateFlow(emptyList())
    val sfxs: StateFlow<List<Card>>
        get() = _sfxs

    private var selectedCards: MutableList<Card> = mutableListOf()

    val exoPlayer = ExoPlayer.Builder(context).build()

    private var _isGameCompleted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isGameCompleted: StateFlow<Boolean>
        get() = _isGameCompleted

    fun playSound(@RawRes resource: Int) {
        val mediaItem = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(resource))
        with(exoPlayer) {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    fun selectCard(item: Card) {
        if (item.isPhoto) {
            _cards.value.let { currentList ->
                val updatedList = currentList.map { it ->
                    if (item.id == it.id) {
                        it.copy(isSelected = !it.isSelected)
                    } else {
                        it
                    }
                }
                _cards.value = updatedList
            }
        } else {
            _sfxs.value.let { currentList ->
                val updatedList = currentList.map { it ->
                    if (item.id == it.id) {
                        it.copy(isSelected = !it.isSelected)
                    } else {
                        it
                    }
                }
                _sfxs.value = updatedList
            }
        }
        selectedCards.add(item)
        playSound(item.audioRes)
        matchCards()
    }

    private fun matchCards() {
        if (selectedCards.size > 1) {
            if (selectedCards[0].id == selectedCards[1].id) {

                _cards.value.let { currentList ->
                    val updatedList = currentList.map { it ->
                        if (selectedCards[0].id == it.id) {
                            it.copy(isMatched = true)
                        } else {
                            it
                        }
                    }
                    _cards.value = updatedList
                }

                _sfxs.value.let { currentList ->
                    val updatedList = currentList.map { it ->
                        if (selectedCards[0].id == it.id) {
                            it.copy(isMatched = true)
                        } else {
                            it
                        }
                    }

                    _sfxs.value = updatedList
                }

                playSound(R.raw.correct_sfx)
            } else {

                _cards.value.let { currentList ->
                    val updatedList = currentList.map { it ->
                        if (!it.isMatched) {
                            it.copy(isSelected = false)
                        } else {
                            it
                        }
                    }
                    _cards.value = updatedList
                }

                _sfxs.value.let { currentList ->
                    val updatedList = currentList.map { it ->
                        if (!it.isMatched) {
                            it.copy(isSelected = false)
                        } else {
                            it
                        }
                    }

                    _sfxs.value = updatedList
                }
                playSound(R.raw.incorrect_sfx)
            }
            selectedCards.clear()
            if (_cards.value.all { it.isMatched == true } && _sfxs.value.all { it.isMatched == true }) {
                playSound(R.raw.end_sfx)
                _isGameCompleted.value = true
            }
        }
    }

    fun getCards() {
        viewModelScope.launch {
            _cards.value = repository.getCards()
        }
    }

    fun getSfxs() {
        viewModelScope.launch {
            _sfxs.value = repository.getSfxs()
        }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }

    fun hideCard(item: Card) {
        _cards.value.let { currentList ->
            val updatedList = currentList.map { it ->
                if (item.id == it.id) {
                    it.copy(isHidden = true)
                } else {
                    it
                }
            }
            _cards.value = updatedList
        }

        _sfxs.value.let { currentList ->
            val updatedList = currentList.map { it ->
                if (item.id == it.id) {
                    it.copy(isHidden = true)
                } else {
                    it
                }
            }
            _sfxs.value = updatedList
        }
    }


}