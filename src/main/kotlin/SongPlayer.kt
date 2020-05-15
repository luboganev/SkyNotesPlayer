import MusicElement.Note
import MusicElement.Pause
import com.malinskiy.adam.AndroidDebugBridgeClient
import com.malinskiy.adam.request.sync.ShellCommandRequest
import kotlinx.coroutines.delay

class SongPlayer(
    private val adbClient: AndroidDebugBridgeClient,
    private val deviceSerial: String,
    private val song: Song,
    private val keyboard: Keyboard
) {

    suspend fun play() {
        song.elements.forEach { musicElement ->
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
        delay(song.singlePauseMillis)
    }
}