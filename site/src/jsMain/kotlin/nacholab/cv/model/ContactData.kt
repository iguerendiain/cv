package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactData(
    val title: HashMap<String, String?>,
    val avatar: String,
    val linkedin: String,
    val email: String
)