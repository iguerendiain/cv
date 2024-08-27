package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectData(
    val id: String,
    val title: HashMap<String, String?>,
    val icon: String,
    val description: HashMap<String, String?>,
    val items: List<HashMap<String, HashMap<String, String?>>>,
    val urls: List<ProjectURLData>? = null
)