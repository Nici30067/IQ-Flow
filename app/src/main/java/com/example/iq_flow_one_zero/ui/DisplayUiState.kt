package com.example.iq_flow_one_zero.ui

import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.tlgi_flashcards

data class DisplayUiState (
    val isBacksideShown: Boolean = false,
    val nameOfCurrentlyLearningFlashcardSet: List<Flashcard> = tlgi_flashcards
)