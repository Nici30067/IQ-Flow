package com.example.iq_flow_one_zero.data
import kotlinx.coroutines.flow.Flow

class OfflineFlashcardRepository(private val flashcardDao: FlashcardDao): FlashcardRepository {
    override fun getAllFlashcards(): Flow<List<Flashcard>> = flashcardDao.getAllFlashcards()

    override fun getFlashcard(id: Int): Flow<Flashcard?> = flashcardDao.getFlashcard(id)

    override suspend fun deleteFlashcard(flashcard: Flashcard) = flashcardDao.deleteFlashcard(flashcard)

    override suspend fun insertFlashcard(flashcard: Flashcard) = flashcardDao.saveFlashcard(flashcard)

    override suspend fun updateFlashcard(flashcard: Flashcard) = flashcardDao.updateFlashcard(flashcard)
}

