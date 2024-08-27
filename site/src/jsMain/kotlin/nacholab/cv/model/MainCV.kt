package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class MainCV(
    val languages: List<Language>,
    val defaultLanguage: String,
    val months: HashMap<String, Array<String>>,
    val navbar: NavBarData,
    val portfolio: PortfolioData,
    val cv: ResumeData,
    val contact: ContactData
)