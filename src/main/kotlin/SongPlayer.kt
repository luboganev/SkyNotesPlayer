import SheetElement.Note
import SheetElement.Pause
import com.malinskiy.adam.AndroidDebugBridgeClient
import com.malinskiy.adam.request.sync.ShellCommandRequest
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

class SongPlayer(
    private val adbClient: AndroidDebugBridgeClient,
    private val deviceSerial: String,
    private val songSheet: SongSheet,
    private val keyboard: Keyboard
) {

    suspend fun play(playCallback: ((playbackProgress: Int, currentlyPlaying: String) -> Unit)) {
        val totalElementsToPlay = songSheet.elements.size
        songSheet.elements.forEachIndexed { index, element ->
            val progress = ((index.toDouble() / totalElementsToPlay.toDouble()) * 100.0).roundToInt()
            when (element) {
                is Note -> {
                    playCallback(
                        progress,
                        element.key.sheetSymbol
                    )
                    keyboard.keys[element.key]?.let { playNote(it) }
                }
                is Pause -> {
                    playCallback(
                        progress,
                        "*"
                    )
                    pause()
                }
            }
        }
    }

    private suspend fun playNote(tapPoint: TapPoint) {
        adbClient.execute(ShellCommandRequest("input tap ${tapPoint.x} ${tapPoint.y}"), serial = deviceSerial)
    }

    private suspend fun pause() {
        delay(songSheet.singlePauseMillis)
    }
}