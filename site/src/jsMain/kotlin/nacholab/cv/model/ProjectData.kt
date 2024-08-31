package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectData(
    val title: HashMap<String, String?>,
    val icon: String,
    val items: List<HashMap<String, HashMap<String, String?>>>,
    val urls: List<ProjectURLData>? = null
)