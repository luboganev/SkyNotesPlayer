import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.interactor.StartAdbInteractor
import com.malinskiy.adam.request.devices.ListDevicesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main(vararg args: String) {
    val song = if (args.isEmpty()) {
        println("Available songs:")
        jukeBox.keys.forEach {
            println(it)
        }
        null
    } else {
        val songName = args[0]
        if (jukeBox[songName] != null) {
            println("Available songs:")
            jukeBox.keys.forEach {
                println(it)
            }
            jukeBox[songName]
        } else {
            null
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
                keyboard = Keyboard.Pixel2,
                song = song
            ).play()
        }
    }
}