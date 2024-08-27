package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class LanguagesData(
    val title: HashMap<String, String?>,
    val content: List<LanguageDataItem>
)