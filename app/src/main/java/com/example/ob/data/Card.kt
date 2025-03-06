package com.example.ob.data

data class Card(
    val id: Int,
    val imageRes: Int,
    val audioRes: Int,
    val isPhoto: Boolean = false,
    var isSelected: Boolean = false,
    var isHidden: Boolean = false,
    var isMatched: Boolean = false
)
