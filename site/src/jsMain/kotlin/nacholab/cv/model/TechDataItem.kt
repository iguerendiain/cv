package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class TechDataItem(
    val title: HashMap<String, String?>,
    val content: List<String>
)