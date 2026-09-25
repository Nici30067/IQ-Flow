package com.example.iq_flow_one_zero.data
import kotlinx.coroutines.flow.Flow

interface FlashcardRepository {

    /**
     * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
     */
        /**
         * Retrieve all the items from the the given data source.
         */
        fun getAllFlashcards(): Flow<List<Flashcard>>

        /**
         * Retrieve an item from the given data source that matches with the [id].
         */
        fun getFlashcard(id: Int): Flow<Flashcard?>

        /**
         * Insert item in the data source
         */
        suspend fun insertFlashcard(flashcard: Flashcard)

        /**
         * Delete item from the data source
         */
        suspend fun deleteFlashcard(flashcard: Flashcard)

        /**
         * Update item in the data source
         */
        suspend fun updateFlashcard(flashcard: Flashcard)
    }
