package pmediero.com.features.plant.data

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError
import pmediero.com.core.presentation.util.setTimeToMillis
import pmediero.com.features.plant.data.local.localsource.PlantLocalSource
import pmediero.com.features.plant.data.notification.scheduler.NotificationScheduler
import pmediero.com.features.plant.domain.repository.PlantRepository
import java.util.Calendar

class PlantRepositoryImpl(
    private val plantLocalSource: PlantLocalSource,
    private val schedulerTodayNotification: NotificationScheduler
): PlantRepository {
    override suspend fun savePlant(plant: Plant): Result<Plant, RootError> {
        if ( isTodayWateringDay(plant.wateringDays) ) {
            if(isTimeToWaterToday(plant.wateringTime)){
                schedulerTodayNotification(plant)
            }
        }
        return plantLocalSource.savePlant(plant)
    }


    override suspend fun saveAllPlant(plants: List<Plant>): Result<Unit, RootError> = plantLocalSource.saveAllPlant(plants)

    override suspend fun observePlants(): Flow<List<Plant>> = plantLocalSource.observePlants()

    override suspend fun getPlants(): List<Plant> = plantLocalSource.getPlants()

    override suspend fun getPlantById(plantIdParam: String): Plant = plantLocalSource.getPlantById(plantIdParam)

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getFilterUpcomingPlants(): List<Plant> = plantLocalSource.getFilterUpcomingPlants()

    private fun isTimeToWaterToday(wateringTime: String): Boolean {
        val currentTime = Calendar.getInstance()
        val wateringTimeParts = wateringTime.split(":")
        val hourToWater = wateringTimeParts[0].toInt()
        val minuteToWater = wateringTimeParts[1].toInt()

        val timeToWater = Calendar.getInstance().setTimeToMillis(hourToWater, minuteToWater)
        val timeUntilWater = timeToWater - currentTime.timeInMillis

        val endOfDay = Calendar.getInstance().setTimeToMillis(23,59,59)

        val timeUntilEndOfDay = endOfDay - currentTime.timeInMillis

        return timeUntilWater in 1..timeUntilEndOfDay
    }
    private fun isTodayWateringDay(wateringDays: String): Boolean {
        val currentDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val dayOfWeekString = getDayOfWeekString(currentDayOfWeek)
        return wateringDays.contains(dayOfWeekString, ignoreCase = true) || wateringDays.contains("everyday", ignoreCase = true)
    }

    private fun getDayOfWeekString(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            Calendar.SUNDAY -> "Su"
            Calendar.MONDAY -> "Mo"
            Calendar.TUESDAY -> "Tu"
            Calendar.WEDNESDAY -> "We"
            Calendar.THURSDAY -> "Th"
            Calendar.FRIDAY -> "Fr"
            Calendar.SATURDAY -> "Sa"
            else -> throw IllegalArgumentException("Invalid day of week: $dayOfWeek")
        }
    }
}

