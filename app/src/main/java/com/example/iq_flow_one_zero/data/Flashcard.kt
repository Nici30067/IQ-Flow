package com.example.iq_flow_one_zero.data

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.iq_flow_one_zero.R

@Entity(tableName = "flashcard_table")
data class Flashcard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val front: String,
    val back: String,
    var backsideIsVisible: Boolean = false
)
data class FlashcardSet(
     val flashcardListName: Int,
    val flashcardList: List<Flashcard>
)

val tlgi_flashcards = listOf<Flashcard>(
    Flashcard(front = R.string.frontside_string_eins.toString(), back = R.string.backside_string_eins.toString()),
    Flashcard(front = R.string.frontside_string_zwei.toString(), back = R.string.backside_string_zwei.toString()),
    Flashcard(front = R.string.frontside_string_drei.toString(), back = R.string.backside_string_drei.toString())
)

val mathe_flashcards = listOf<Flashcard>(
    Flashcard(front = R.string.frontside_string_eins.toString(), back = R.string.backside_string_eins.toString()),
    Flashcard(front = R.string.frontside_string_zwei.toString(), back = R.string.backside_string_zwei.toString()),
    Flashcard(front = R.string.frontside_string_drei.toString(), back = R.string.backside_string_drei.toString())
)

val englisch_flashcards = listOf<Flashcard>(
    Flashcard(front = R.string.frontside_string_eins.toString(), back = R.string.backside_string_eins.toString()),
    Flashcard(front = R.string.frontside_string_zwei.toString(), back = R.string.backside_string_zwei.toString()),
    Flashcard(front = R.string.frontside_string_drei.toString(), back = R.string.backside_string_drei.toString())
)

val mainFlashcardList = listOf<FlashcardSet>(
    FlashcardSet(flashcardListName = R.string.tlgi_flashcardlist_name, flashcardList = tlgi_flashcards),
//    FlashcardSet(flashcardListName = R.string.mathe_flashcardlist_name, flashcardList = mathe_flashcards),
//    FlashcardSet(flashcardListName = R.string.englisch_flashcardlist_name, flashcardList = englisch_flashcards),
)