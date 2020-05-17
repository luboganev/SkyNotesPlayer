import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.interactor.StartAdbInteractor
import com.malinskiy.adam.request.devices.ListDevicesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main(vararg args: String) {
    val song = if (args.isEmpty() || args[0].toIntOrNull() == null) {
        printInstructions()
        null
    } else {
        val number = args[0].toInt()
        if (number - 1 !in Jukebox.tracks.indices) {
            printInstructions()
            null
        } else {
            Jukebox.tracks[number - 1]
        }
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
    }
}

private fun printInstructions() {
    println("Available songs:")
    Jukebox.tracks.forEachIndexed { index, track ->
        println("${index + 1}. ${track.artistAndTitle}")
    }
    println("Please start with song number")
}