package com.example.iq_flow_one_zero.ui

import androidx.lifecycle.ViewModel
import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.FlashcardSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DisplayViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DisplayUiState())
    val uiState: StateFlow<DisplayUiState> = _uiState.asStateFlow()


    fun updateCurrentlyLearningFlashcards(flashcardSet: List<Flashcard>){
        _uiState.update { currentState ->
            currentState.copy(
                nameOfCurrentlyLearningFlashcardSet = flashcardSet
            )
        }
    }
    fun updateFlashcardState(){
        if(uiState.value.isBacksideShown){
            hideBackside()
            pickRandomFlashcard()
        }else{
            showBackside()
        }
    }

   private fun pickRandomFlashcard() {
        val lastFlashcardsetIndex = uiState.value.nameOfCurrentlyLearningFlashcardSet.size - 1
        var newFlashcardId: Int = (0..lastFlashcardsetIndex).random()
        while (newFlashcardId == uiState.value.currentFlashcardId){
            newFlashcardId = (0..lastFlashcardsetIndex).random()
        }
        _uiState.update { currentState ->
            currentState.copy(
                currentFlashcardId = newFlashcardId
            )

        }
    }
    private fun hideBackside(){
        _uiState.update { currentState ->
            currentState.copy(isBacksideShown = false)
        }
    }
    private fun showBackside(){
        _uiState.update { currentState ->
            currentState.copy(isBacksideShown = true)
        }
    }

    fun resetApplication(){
        _uiState.value = DisplayUiState(/*currentFlashcardId = pickRandomFlashcard()*/)
    }
    init {
        resetApplication()
    }
}