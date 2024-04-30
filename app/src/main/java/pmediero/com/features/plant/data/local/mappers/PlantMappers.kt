package pmediero.com.features.plant.data.local.mappers

import org.mongodb.kbson.ObjectId
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity

fun toPlant(type: PlantEntity): Plant {
    val waterAmountWithoutLastThreeChars = if (type.waterAmount.length >= 3) {
        type.waterAmount.substring(0, type.waterAmount.length - 3)
    } else {
        type.waterAmount
    }
    return Plant(
        id = type._id.toHexString(),
        name = type.name,
        wateringDays = type.wateringDays,
        wateringTime = type.wateringTime,
        waterAmount = waterAmountWithoutLastThreeChars,
        plantSize = type.plantSize,
        description = type.description,
        photo = type.photo,
        isWatered = type.isWatered
    )
}

fun toPlantEntity(plant: Plant): PlantEntity {
    return PlantEntity().apply {
        _id = if (plant.id == "0" || plant.id == "") {
            ObjectId()
        } else ObjectId(plant.id)
        name = plant.name
        wateringDays = plant.wateringDays
        wateringTime = plant.wateringTime
        waterAmount = plant.waterAmount + " ml"
        plantSize = plant.plantSize
        description = plant.description
        photo = plant.photo
        isWatered = plant.isWatered
    }
}