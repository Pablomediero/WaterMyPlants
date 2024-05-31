package pmediero.com.features.plant.data.local.mappers

import org.mongodb.kbson.ObjectId
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity

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
        wateringTime = plantEntity.wateringTime,
        waterAmount = waterAmountWithoutLastThreeChars,
        plantSize = plantEntity.plantSize,
        description = plantEntity.description,
        photo = plantEntity.photo,
        isWatered = plantEntity.isWatered
    )
}

fun Plant.toPlantEntity(plant: Plant): PlantEntity {
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