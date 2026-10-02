package com.example.iq_flow_one_zero.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFlashcard(flashcard: Flashcard)
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun saveFlashcardSet(name: String)

//    @Transaction
//    @Query("SELECT * FROM flashcard_table WHERE idOfParentList = :id")
//    suspend fun getFlashcardAndFlashcardSetWithId(id: Int)

    @Update
    suspend fun updateFlashcard(flashcard: Flashcard)

    @Delete
    suspend fun deleteFlashcard(flashcard: Flashcard)

    @Query("SELECT * FROM flashcard_table WHERE id = :id")
    fun getFlashcard(id: Int): Flow<Flashcard>

    @Query("SELECT * FROM flashcard_table")
    fun getAllFlashcards(): Flow<List<Flashcard>>
}