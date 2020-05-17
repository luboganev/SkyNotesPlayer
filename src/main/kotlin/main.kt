import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.interactor.StartAdbInteractor
import com.malinskiy.adam.request.devices.ListDevicesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

private val adb by lazy {
    AndroidDebugBridgeClientFactory().apply {
        coroutineContext = Dispatchers.IO
    }.build()
}

fun main() {
    runBlocking {
        //Verify the ADB server is running
        StartAdbInteractor().execute()
    }

    while (true) {
        printInstructions()
        val input = readLine()
        if (!input.isNullOrBlank()) {
            val trackNumberIndex = input.toIntOrNull()?.let { it - 1 }
            if (trackNumberIndex != null) {
                if (trackNumberIndex in Jukebox.tracks.indices) {
                    playSong(Jukebox.tracks[trackNumberIndex])
                }
            } else {
                println("You have typed in the following music sheet:")
                println(input)
                println("Please select the length of a pause in milliseconds or type \"cancel\" to cancel.")
                val command = readLine()
                if (command != "cancel") {
                    val pause = command?.toLongOrNull()
                    if (pause != null) {
                        playSong(
                            SongSheet(
                                artistAndTitle = "Custom sheet with pause length $pause",
                                sheet = input,
                                singlePauseMillis = pause
                            )
                        )
                    } else {
                        println("Invalid pause milliseconds length.")
                    }
                }
            }
        }
    }
}

private fun printInstructions() {
    println()
    println()
    println("----------------------------------------------------")
    println("Available songs:")
    Jukebox.tracks.forEachIndexed { index, track ->
        println("${index + 1}. ${track.artistAndTitle}")
    }
    println("Please select song number or start with custom song.")
    println("----------------------------------------------------")
    println("Or you can play a custom sheet. Just type it in and press enter.")
}

private fun playSong(songSheet: SongSheet) {
    runBlocking {
        //Execute requests using suspendable execute() methods. First list available devices
        val devices = adb.execute(request = ListDevicesRequest())
        if (devices.isNotEmpty()) {
            val serial = devices.first().serial

            println()
            println("Now playing: ${songSheet.artistAndTitle}")

            // Play a song
            SongPlayer(
                adbClient = adb,
                deviceSerial = serial,
                songSheet = songSheet,
                keyboard = Keyboard.Pixel2
            ).play { playbackProgress, currentlyPlaying ->
                if (playbackProgress % 10 == 0) {
                    println()
                    println("Playback ($playbackProgress%)")
                }
                print("$currentlyPlaying ")
            }
        } else {
            println("No device attached. Please attach an Android device before you continue")
            exitProcess(0)
        }
    }
}