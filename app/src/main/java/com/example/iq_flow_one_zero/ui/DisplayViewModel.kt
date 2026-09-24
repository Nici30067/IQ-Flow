package com.example.iq_flow_one_zero.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.iq_flow_one_zero.R
import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.FlashcardSet
import com.example.iq_flow_one_zero.data.tlgi_flashcards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class DisplayViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DisplayUiState())
    val uiState: StateFlow<DisplayUiState> = _uiState.asStateFlow()

    fun getFullFlashcardSet(): Flow<List<FlashcardSet>> = flowOf(
        listOf(
            //sample data: Need to be replaced
            FlashcardSet(flashcardListName = R.string.tlgi_flashcardlist_name, flashcardList = tlgi_flashcards)
        )
    )
    fun getSingleFlashcardSet(): Flow<List<Flashcard>> = flowOf(
        listOf(
            Flashcard(front = R.string.mathe_backside_string_drei, back = R.string.mathe_backside_string_drei)
        )
    )

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
//            pickRandomFlashcard()
        }else{
            showBackside()
        }
    }

//   private fun pickRandomFlashcard() {
//        val lastFlashcardsetIndex = uiState.value.nameOfCurrentlyLearningFlashcardSet.size - 1
//        var newFlashcardId: Int = (0..lastFlashcardsetIndex).random()
//        while (newFlashcardId == uiState.value.currentFlashcardId){
//            newFlashcardId = (0..lastFlashcardsetIndex).random()
//        }
//        _uiState.update { currentState ->
//            currentState.copy(
//                currentFlashcardId = newFlashcardId
//            )
//
//        }
//    }
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
    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DisplayViewModel()
            }
        }
    }
}