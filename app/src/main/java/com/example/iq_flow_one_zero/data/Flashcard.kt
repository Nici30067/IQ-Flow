package com.example.iq_flow_one_zero.data

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.iq_flow_one_zero.R

data class Flashcard(
    @StringRes val front: Int,
    @StringRes val back: Int,
    var backsideIsVisible: Boolean = false
)
data class FlashcardSet(
     val flashcardListName: Int,
    val flashcardList: List<Flashcard>
)

val tlgi_flashcards = listOf<Flashcard>(
    Flashcard(front = R.string.frontside_string_eins, back = R.string.backside_string_eins),
    Flashcard(front = R.string.frontside_string_zwei, back = R.string.backside_string_zwei),
    Flashcard(front = R.string.frontside_string_drei, back = R.string.backside_string_drei)
)

val mathe_flashcards = listOf<Flashcard>(
    Flashcard(front = R.string.frontside_string_eins, back = R.string.backside_string_eins),
    Flashcard(front = R.string.frontside_string_zwei, back = R.string.backside_string_zwei),
    Flashcard(front = R.string.frontside_string_drei, back = R.string.backside_string_drei)
)

val englisch_flashcards = listOf<Flashcard>(
    Flashcard(front = R.string.frontside_string_eins, back = R.string.backside_string_eins),
    Flashcard(front = R.string.frontside_string_zwei, back = R.string.backside_string_zwei),
    Flashcard(front = R.string.frontside_string_drei, back = R.string.backside_string_drei)
)

val mainFlashcardList = listOf<FlashcardSet>(
    FlashcardSet(flashcardListName = R.string.tlgi_flashcardlist_name, flashcardList = tlgi_flashcards),
    FlashcardSet(flashcardListName = R.string.mathe_flashcardlist_name, flashcardList = mathe_flashcards),
    FlashcardSet(flashcardListName = R.string.englisch_flashcardlist_name, flashcardList = englisch_flashcards),
)