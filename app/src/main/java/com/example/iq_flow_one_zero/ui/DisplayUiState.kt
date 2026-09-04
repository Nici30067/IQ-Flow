package com.example.iq_flow_one_zero.ui

import com.example.iq_flow_one_zero.data.Flashcard

data class DisplayUiState (
    val currentFlashcardId: Int = 0,
    val isBacksideShown: Boolean = false
)