package pmediero.com.features.plant.presentation.notification.root

import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.presentation.notification.model.TabType

data class NotificationState(
    var plantListMap: Map<TabType, List<Plant>> = mapOf(),

    )