package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class ResumeData(
    val title: HashMap<String, String?>,
    val work: WorkExperienceData,
    val tech: TechData,
    val languages: LanguagesData,
)