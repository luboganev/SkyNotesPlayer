import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.interactor.StartAdbInteractor
import com.malinskiy.adam.request.devices.ListDevicesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main(vararg args: String) {
    val song: SongSheet? = when {
        args.size == 1 -> {
            val trackNumberIndex = args[0].toIntOrNull()?.let { it - 1 }
            if (trackNumberIndex == null || trackNumberIndex !in Jukebox.tracks.indices) {
                null
            } else {
                Jukebox.tracks[trackNumberIndex]
            }
        }
        args.size == 4 && args[0] == "-p" && args[2] == "-s" -> {
            val pause = args[1].toIntOrNull()
            val songSheet = args[3]
            pause?.let {
                SongSheet(
                    artistAndTitle = "Custom sheet",
                    sheet = songSheet,
                    singlePauseMillis = it.toLong()
                )
            }
        }
        else -> null
    }

    if (song != null) {
        runBlocking {
            //Verify the ADB server is running
            StartAdbInteractor().execute()
            //Create AndroidDebugBridgeServer instance
            val adb = AndroidDebugBridgeClientFactory().apply {
                coroutineContext = Dispatchers.IO
            }.build()
            //Execute requests using suspendable execute() methods. First list available devices
            val devices = adb.execute(request = ListDevicesRequest())
            val serial = devices.first().serial

            // Play a song
            SongPlayer(
                adbClient = adb,
                deviceSerial = serial,
                songSheet = song,
                keyboard = Keyboard.Pixel2
            ).play()
        }
    } else {
        printInstructions()
    }
}

private fun printInstructions() {
    println("Available songs:")
    Jukebox.tracks.forEachIndexed { index, track ->
        println("${index + 1}. ${track.artistAndTitle}")
    }
    println("Please start with song number or start with cusom song.")
    println("To start a custom song type -p <pause length milliseconds> -s <sheet string>")
}