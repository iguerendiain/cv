package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectURLData(
    val icon: String,
    val title: HashMap<String, String?>,
    val url: String
)