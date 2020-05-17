
object Jukebox {

    val tracks: List<SongSheet> by lazy {
        listOf(

            SongSheet(
                artistAndTitle = "Camila Cabello ft. Shawn Mendes - Señorita",
                sheet = "c+****a****e****c+b****" +
                        "g****gc+babc+****g****e****c+b****g****" +
                        "gc+bagf**c+*c+*c+************" +
                        "f****c+*c+*c+************" +
                        "b****c+bc+bg********" +
                        "b****c+bc+bgaa",
                singlePauseMillis = 50
            ),

            SongSheet(
                artistAndTitle = "Twinkle twinkle little star",
                sheet = "g-*g-*d*d*e*e*d********" +
                        "c*c*b-*b-*a-*a-*g-********" +
                        "d*d*c*c*b-*b-*a-********" +
                        "d*d*c*c*b-*b-*a-********" +
                        "g-*g-*d*d*e*e*d********" +
                        "c*c*b-*b-*a-*a-*g-",
                singlePauseMillis = 50
            ),

            SongSheet(
                artistAndTitle = "Super Mario theme",
                sheet = "ee*e*ce*g********g-********" +
                        "c**g-**e-**a-**b-b-**a-" +
                        "g-egafgecdb-********" +
                        "c**g-**e-**a-**b-b-**a-" +
                        "g-egafgecdb-********" +
                        "g*fde**g-a-c**a-cd********" +
                        "g*fde**c+c+c+****************" +
                        "g*fde**g-a-c**a-cd********" +
                        "e**d**c********" +
                        "ccc**cdeca-g-********" +
                        "ccc**cde**************" +
                        "ccc**cdeca-g-********" +
                        "eee**ceg********g-********",
                singlePauseMillis = 50
            ),

            SongSheet(
                artistAndTitle = "Tetris theme",
                sheet = "e*b-cd*cb-a-*" +
                        "a-ce*dcb-*" +
                        "b-cd*e*c*a-*a-****" +
                        "d*fa*gfe**ce*dcb-*" +
                        "b-cd*e*c*a-*a-****",
                singlePauseMillis = 200
            ),

            SongSheet(
                artistAndTitle = "Ludwig van Beethoven - Für Elise",
                sheet = "e*e*eb-dca-**" +
                        "c-e-a-b-**" +
                        "e-a-b-c**" +
                        "e-e*e*eb-dca-**" +
                        "c-e-a-b-**" +
                        "e-cb-a-****" +
                        "e*e*eb-dca-**" +
                        "c-e-a-b-**" +
                        "e-a-b-c**" +
                        "e-e*e*eb-dca-**" +
                        "c-e-a-b-**" +
                        "e-cb-a-**" +
                        "b-cde**" +
                        "g-fed**" +
                        "f-edc**" +
                        "e-dcb-**" +
                        "e-e**" +
                        "e**e*e**e**e**eb-dca-**" +
                        "c-e-a-b-**" +
                        "e-a-b-c**" +
                        "e-e*e*eb-dca-**" +
                        "c-e-a-b-**" +
                        "e-cb-a-**",
                singlePauseMillis = 200
            ),

            SongSheet(
                artistAndTitle = "Sam Smith, Normani - Dancing With A Stranger",
                sheet = "e**a-a-a-g-g-*****ea-a-a-g-g-***e**a-a-a-g-e-g-*e-e-d-d-*c-*",
                singlePauseMillis = 100
            ),

            SongSheet(
                artistAndTitle = "Lil Nas X - Old Town Road",
                sheet = "eee**d**c**a-a-ed**c**a-a-******d**********dec**b-**b-a-a-a-e**d**c**a-a-edc**a-a-******d********dec**a-g-a-",
                singlePauseMillis = 100
            )
        )
    }
}