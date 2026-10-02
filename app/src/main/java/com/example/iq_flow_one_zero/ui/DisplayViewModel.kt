package com.example.iq_flow_one_zero.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.iq_flow_one_zero.R
import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.FlashcardRepository
import com.example.iq_flow_one_zero.data.FlashcardSet
import com.example.iq_flow_one_zero.data.tlgi_flashcards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DisplayViewModel(private val flashcardRepository: FlashcardRepository): ViewModel() {
    private val _uiState = MutableStateFlow(DisplayUiState())
    val uiState: StateFlow<DisplayUiState> = _uiState.asStateFlow()

    val homeUiState: StateFlow<DisplayUiState> = flashcardRepository.getAllFlashcards().map { DisplayUiState(flashcardList = it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = DisplayUiState()
    )

    var isAddDeckDialogShown by  mutableStateOf(false)
        private set
    fun onAddDecksClicked(){
        isAddDeckDialogShown = true
    }

    fun onAddDecksDismissed(){
        isAddDeckDialogShown = false
    }
    suspend fun saveFlashcard(front: String, back: String){

        flashcardRepository.insertFlashcard(Flashcard(front = front, back = back) )
    }
    fun getFullFlashcardSet(): Flow<List<FlashcardSet>> = flowOf(
        listOf(
            //sample data: Need to be replaced
            FlashcardSet(flashcardListName = R.string.tlgi_flashcardlist_name, flashcardList = tlgi_flashcards)
        )
    )

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
        private const val TIMEOUT_MILLIS = 5_000L
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlashcardApplication)
                DisplayViewModel(application.container.flashcardRepository)
                //DisplayViewModel(flashcardApplication().container.flashcardRepository)       wollte das turtorial
            }
        }
    }
}