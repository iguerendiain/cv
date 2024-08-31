package nacholab.cv.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.http.http
import com.varabyte.kobweb.core.Page
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import nacholab.cv.BASE_URL_ASSETS
import nacholab.cv.components.InitialLoading
import nacholab.cv.components.CV
import nacholab.cv.model.MainCV

@Page
@Composable
fun HomePage() {
    var cvData by remember { mutableStateOf<MainCV?>(null) }
    var screenWidth by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        val endpointResponse = window
            .http
            .get("${BASE_URL_ASSETS}/db.test.json")
            .decodeToString()

            val jsonHandler = Json { ignoreUnknownKeys = true }
            cvData = jsonHandler.decodeFromString<MainCV>(endpointResponse)

        window.onresize = { window.innerWidth.let { screenWidth = it} }
    }

    cvData
        ?.let { CV(it, screenWidth) }
        ?: InitialLoading()
}
