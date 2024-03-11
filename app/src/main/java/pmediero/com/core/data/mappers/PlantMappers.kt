package pmediero.com.core.data.mappers

import org.mongodb.kbson.ObjectId
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity

fun toPlant(type: PlantEntity): Plant {
    return Plant(
        id = type._id.toString().hashCode().toLong(),
        name = type.name,
        wateringDays = type.wateringDays,
        wateringTime = type.wateringTime,
        waterAmount = type.waterAmount,
        plantSize = type.plantSize,
        description = type.description,
    )
}

fun toPlantEntity(plant: Plant): PlantEntity {
    return PlantEntity().apply {
        _id = ObjectId(plant.id)
        name = plant.name
        wateringDays = plant.wateringDays
        wateringTime = plant.wateringTime
        waterAmount = plant.waterAmount
        plantSize = plant.plantSize
        description = plant.description
    }
}