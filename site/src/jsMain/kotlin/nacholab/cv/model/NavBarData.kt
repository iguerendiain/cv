package nacholab.cv.model

import kotlinx.serialization.Serializable

@Serializable
data class NavBarData(
    val title: HashMap<String, String?>,
    val menu: NavBarMenuData
)