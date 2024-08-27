package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class TechData(
    val title: HashMap<String, String?>,
    val content: List<TechDataItem>
)