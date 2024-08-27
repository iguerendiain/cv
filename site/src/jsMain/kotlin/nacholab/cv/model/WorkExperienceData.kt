package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkExperienceData(
    val title: HashMap<String, String?>,
    val content: List<WorkExperienceDataItem>
)