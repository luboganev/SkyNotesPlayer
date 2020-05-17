import SheetElement.Note
import SheetElement.Pause
import com.malinskiy.adam.AndroidDebugBridgeClient
import com.malinskiy.adam.request.sync.ShellCommandRequest
import kotlinx.coroutines.delay

class SongPlayer(
    private val adbClient: AndroidDebugBridgeClient,
    private val deviceSerial: String,
    private val songSheet: SongSheet,
    private val keyboard: Keyboard
) {

    suspend fun play() {
        songSheet.elements.forEach { musicElement ->
            when (musicElement) {
                is Note -> {
                    keyboard.keys[musicElement.key]?.let { playNote(it) }
                }
                is Pause -> pause()
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