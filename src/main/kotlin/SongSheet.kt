/**
 * A music element, either a note or a pause
 */
sealed class SheetElement {
    /**
     * Each note has a key
     */
    data class Note(val key: Key) : SheetElement()

    /**
     * A pause during which nothing is played
     */
    object Pause : SheetElement()
}

/**
 * A song definition
 *
 * @param sheet Music sheet in string form
 * @param artistAndTitle The human readable artist and title of the song
 * @param singlePauseMillis The length of a single pause in milliseconds
 *
 * The allowed symbols for the sheet are:
 *
 * - Notes (case insensitive, so c- is also valid): C- D- E- F- G- A- B- C D E F G A B C+
 * - Pause: *
 *
 * Any other symbol is ignored.
 *
 */
class SongSheet(
    /**
     * The human readable artist and title of the song
     */
    val artistAndTitle: String,
    sheet: String,
    /**
     * The length of a single pause in milliseconds
     */
    val singlePauseMillis: Long
) {

    /**
     * The list of music elements in this song
     */
    val elements: List<SheetElement>

    init {
        val parsedSong = mutableListOf<SheetElement>()
        var lastSheetSymbol = ' '

        fun addLastBaseNoteIfNecessary() {
            if (lastSheetSymbol.isLetter()) {
                Key.values().firstOrNull {
                    it.sheetSymbol == "$lastSheetSymbol".toUpperCase()
                }?.let {
                    parsedSong.add(SheetElement.Note(it))
                }
            }
        }

        sheet.forEach { sheetSymbol ->
            when (sheetSymbol) {
                '*' -> {
                    addLastBaseNoteIfNecessary()
                    parsedSong.add(SheetElement.Pause)
                }
                '+', '-' -> {
                    if (lastSheetSymbol.isLetter()) {
                        Key.values().firstOrNull {
                            it.sheetSymbol == "$lastSheetSymbol$sheetSymbol".toUpperCase()
                        }?.let {
                            parsedSong.add(SheetElement.Note(it))
                        }
                    }
                }
                else -> {
                    addLastBaseNoteIfNecessary()
                }
            }
            lastSheetSymbol = sheetSymbol
        }

        addLastBaseNoteIfNecessary()
        elements = parsedSong.toList()
    }

}