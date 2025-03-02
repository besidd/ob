package com.example.ob.data

data class Card(
    val id: Int,
    val imageRes: Int,
    val audioRes: Int,
    val isPhoto: Boolean,
    var isSelected: Boolean = false,
    var isHidden: Boolean = false
)
