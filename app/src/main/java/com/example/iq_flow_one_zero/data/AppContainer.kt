package com.example.iq_flow_one_zero.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val flashcardRepository: FlashcardRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    override val flashcardRepository: FlashcardRepository by lazy {
        OfflineFlashcardRepository(AppDatabase.getDatabase(context).flashcardDao())
    }
}