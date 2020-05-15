/**
 * A music element, either a note or a pause
 */
sealed class MusicElement {
    /**
     * Each note has a key
     */
    data class Note(val key: Key) : MusicElement()

    /**
     * A pause during which nothing is played
     */
    object Pause : MusicElement()
}

/**
 * A song definition
 *
 * @param songString The song defined by using the following symbols:
 *
 * Notes
 * - C1, D1, E1, F1, G1, A1, B1, C2, D2, E2, F2, G2, A2, B2, C3
 * Pause
 * - *
 *
 */
class Song(songString: String,
           /**
            * The length of a single pause in milliseconds
            */
           val singlePauseMillis: Long) {

    /**
     * The list of music elements in this song
     */
    val elements: List<MusicElement>

    init {
        val parsedSong = mutableListOf<MusicElement>()
        var lastChar = '*'
        songString.forEach {

            when {
                it == '*' -> {
                    parsedSong.add(MusicElement.Pause)
                }
                it.isLetter() -> {
                    lastChar = it
                }
                it.isDigit() -> {
                    val key: Key? = try {
                        Key.valueOf("$lastChar$it")
                    } catch (e: IllegalArgumentException) {
                        null
                    }
                    if (key != null) {
                        parsedSong.add(MusicElement.Note(key))
                    }
                }
            }
        }
        elements = parsedSong.toList()
    }
}