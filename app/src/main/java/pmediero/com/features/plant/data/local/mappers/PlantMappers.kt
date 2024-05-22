package pmediero.com.features.plant.data.local.mappers

import org.mongodb.kbson.ObjectId
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity

fun PlantEntity.toPlant(plantEntityTipe: PlantEntity): Plant {
    val waterAmountWithoutLastThreeChars = if (plantEntityTipe.waterAmount.length >= 3) {
        plantEntityTipe.waterAmount.substring(0, plantEntityTipe.waterAmount.length - 3)
    } else {
        plantEntityTipe.waterAmount
    }
    return Plant(
        id = plantEntityTipe._id.toHexString(),
        name = plantEntityTipe.name,
        wateringDays = plantEntityTipe.wateringDays,
        wateringTime = plantEntityTipe.wateringTime,
        waterAmount = waterAmountWithoutLastThreeChars,
        plantSize = plantEntityTipe.plantSize,
        description = plantEntityTipe.description,
        photo = plantEntityTipe.photo,
        isWatered = plantEntityTipe.isWatered
    )
}

fun Plant.toPlantEntity(plantType: Plant): PlantEntity {
    return PlantEntity().apply {
        _id = if (plantType.id == "0" || plantType.id == "") {
            ObjectId()
        } else ObjectId(plantType.id)
        name = plantType.name
        wateringDays = plantType.wateringDays
        wateringTime = plantType.wateringTime
        waterAmount = plantType.waterAmount + " ml"
        plantSize = plantType.plantSize
        description = plantType.description
        photo = plantType.photo
        isWatered = plantType.isWatered
    }
}