package pmediero.com.features.plant.data.local.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import org.mongodb.kbson.ObjectId
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity

@RequiresApi(Build.VERSION_CODES.O)
fun PlantEntity.toPlant(plantEntity: PlantEntity): Plant {
    val waterAmountWithoutLastThreeChars = if (plantEntity.waterAmount.length >= 3) {
        plantEntity.waterAmount.substring(0, plantEntity.waterAmount.length - 3)
    } else {
        plantEntity.waterAmount
    }
    return Plant(
        id = plantEntity._id.toHexString(),
        name = plantEntity.name,
        wateringDays = plantEntity.wateringDays,
        lastWateredDate = plantEntity.lastWateredDate,
        wateringTime = plantEntity.wateringTime,
        waterAmount = waterAmountWithoutLastThreeChars,
        plantSize = plantEntity.plantSize,
        description = plantEntity.description,
        photo = plantEntity.photo,
        isWatered = plantEntity.isWatered
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Plant.toPlantEntity(plant: Plant): PlantEntity {
    return PlantEntity().apply {
        _id = if (plant.id == "0" || plant.id == "") {
            ObjectId()
        } else ObjectId(plant.id)
        name = plant.name
        wateringDays = plant.wateringDays
        lastWateredDate = plant.lastWateredDate
        wateringTime = plant.wateringTime
        waterAmount = plant.waterAmount + " ml"
        plantSize = plant.plantSize
        description = plant.description
        photo = plant.photo
        isWatered = plant.isWatered
    }
}