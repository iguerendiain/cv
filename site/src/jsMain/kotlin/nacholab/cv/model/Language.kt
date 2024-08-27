package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val code: String,
    val name: HashMap<String, String?>
)