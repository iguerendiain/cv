package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkExperienceDataItem(
    val title: HashMap<String, String?>,
    val from: List<Int> = listOf(0,0),
    val to: List<Int>? = null,
    val description: HashMap<String, String?>,
    val url: String? = null
)