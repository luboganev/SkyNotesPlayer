/**
 * The beginning of Camila Cabello and Shawn Mendes - Señorita
 */
val senorita = Song(
    songString = "C3****A2****E2****C3B2****" +
            "G2****G2C3B2A2B2C3****G2****E2****C3B2****G2****" +
            "G2C3B2A2G2F2**C3*C3*C3************" +
            "F2****C3*C3*C3************" +
            "B2****C3B2C3B2G2********" +
            "B2****C3B2C3B2G2A2A2",
    singlePauseMillis = 50
)

/**
 * Twinkle little star
 */
val twinkleStar = Song(
    songString = "G1*G1*D2*D2*E2*E2*D2********" +
            "C2*C2*B1*B1*A1*A1*G1********"+
            "D2*D2*C2*C2*B1*B1*A1********"+
            "D2*D2*C2*C2*B1*B1*A1********"+
            "G1*G1*D2*D2*E2*E2*D2********"+
            "C2*C2*B1*B1*A1*A1*G1",
    singlePauseMillis = 50
)

val jukeBox = mapOf<String, Song>(
    "senorita" to senorita,
    "twinkle" to twinkleStar
)