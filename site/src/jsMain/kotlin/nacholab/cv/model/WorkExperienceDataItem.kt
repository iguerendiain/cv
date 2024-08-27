package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkExperienceDataItem(
    val title: HashMap<String, String?>,
    val from: Long,
    val to: Long? = null,
    val description: HashMap<String, String?>,
    val url: String? = null
)