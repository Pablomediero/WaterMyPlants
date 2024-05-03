package pmediero.com.features.plant.data.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity
import pmediero.com.core.model.util.LocalError
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError
import pmediero.com.core.presentation.util.setTimeToMillis
import pmediero.com.features.plant.data.mappers.toPlant
import pmediero.com.features.plant.data.mappers.toPlantEntity
import pmediero.com.features.plant.data.notification.scheduler.SchedulerNotification
import java.util.Calendar

class PlantRepository(
    private val realm: Realm,
    private val schedulerTodayNotification: SchedulerNotification
) {

    suspend fun savePlant(plant: Plant): Result<Plant, RootError> {
        return try {
            val plantEntity = toPlantEntity(plant)
            realm.write {
                copyToRealm(plantEntity, UpdatePolicy.ALL)
            }
            if ( isTodayWateringDay(plant.wateringDays) ) {
                if(isTimeToWaterToday(plant.wateringTime)){
                    schedulerTodayNotification(plant)
                }
            }
            Result.Success(toPlant(plantEntity))
        } catch (e: Exception) {
            Result.Error(LocalError)
        }

    }

    suspend fun saveAllPlant(plants: List<Plant>): Result<Unit, RootError> {
        return try {
            realm.write {
                plants.forEach { plant ->
                    copyToRealm(toPlantEntity(plant), UpdatePolicy.ALL)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(LocalError)
        }

    }

    suspend fun observePlants(): Flow<List<Plant>> = realm
        .query<PlantEntity>()
        .asFlow()
        .map { results ->
            results.list.toList().map {
                toPlant(it)
            }
        }


    suspend fun getPlants(): List<Plant> = realm.query<PlantEntity>().find().map { toPlant(it) }
    suspend fun getPlantById(plantIdParam: String):Plant {
        val plantEntity = ObjectId(plantIdParam)
        return toPlant(realm.query<PlantEntity>("_id == $0", plantEntity).find().first())
    }

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

