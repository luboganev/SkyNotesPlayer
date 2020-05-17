/**
 * A point on the device screen corresponding to the tap area of the note key
 */
data class TapPoint(val x: Int, val y: Int)

/**
 * The available keys in the game
 */
enum class Key(val sheetSymbol: String) {
    C1("C-"),
    D1("D-"),
    E1("E-"),
    F1("F-"),
    G1("G-"),
    A1("A-"),
    B1("B-"),
    C2("C"),
    D2("D"),
    E2("E"),
    F2("F"),
    G2("G"),
    A2("A"),
    B2("B"),
    C3("C+")
}

/**
 * Available keyboards. Extend for more devices.
 */
sealed class Keyboard(val keys: Map<Key, TapPoint>) {

    object Pixel2 : Keyboard(
        mapOf(
            Key.C1 to TapPoint(577, 134),
            Key.D1 to TapPoint(770, 134),
            Key.E1 to TapPoint(953, 134),
            Key.F1 to TapPoint(1160, 134),
            Key.G1 to TapPoint(1360, 134),
            Key.A1 to TapPoint(577, 303),
            Key.B1 to TapPoint(770, 303),
            Key.C2 to TapPoint(953, 303),
            Key.D2 to TapPoint(1160, 303),
            Key.E2 to TapPoint(1360, 303),
            Key.F2 to TapPoint(577, 500),
            Key.G2 to TapPoint(770, 500),
            Key.A2 to TapPoint(953, 500),
            Key.B2 to TapPoint(1160, 500),
            Key.C3 to TapPoint(1360, 500)
        )
    )
}