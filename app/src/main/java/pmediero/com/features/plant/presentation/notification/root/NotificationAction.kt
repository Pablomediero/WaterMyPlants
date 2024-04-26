package pmediero.com.features.plant.presentation.notification.root

sealed class NotificationAction {
    data object OnReturnClick: NotificationAction()
    data class OnLinkTextClick(val plantIdParam: String): NotificationAction()

}