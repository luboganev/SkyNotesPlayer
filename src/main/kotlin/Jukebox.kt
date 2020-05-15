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
            "C2*C2*B1*B1*A1*A1*G1********" +
            "D2*D2*C2*C2*B1*B1*A1********" +
            "D2*D2*C2*C2*B1*B1*A1********" +
            "G1*G1*D2*D2*E2*E2*D2********" +
            "C2*C2*B1*B1*A1*A1*G1",
    singlePauseMillis = 50
)

/**
 * Super mario
 */
val mario = Song(
    songString = "E2E2*E2*C2E2*G2********G1********" +
            "C2**G1**E1**A1**B1B1**A1" +
            "G1E2G2A2F2G2E2C2D2B1********" +
            "C2**G1**E1**A1**B1B1**A1" +
            "G1E2G2A2F2G2E2C2D2B1********" +
            "G2*F2D2E2**G1A1C2**A1C2D2********" +
            "G2*F2D2E2**C3C3C3****************" +
            "G2*F2D2E2**G1A1C2**A1C2D2********" +
            "E2**D2**C2********" +
            "C2C2C2**C2D2E2C2A1G1********" +
            "C2C2C2**C2D2E2**************" +
            "C2C2C2**C2D2E2C2A1G1********" +
            "E2E2E2**C2E2G2********G1********",
    singlePauseMillis = 50
)

val jukeBox = mapOf<String, Song>(
    "senorita" to senorita,
    "twinkle" to twinkleStar,
    "mario" to mario
)