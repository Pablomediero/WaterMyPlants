package pmediero.com.core.model.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class PlantEntity: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = "nombre"
    var wateringDays: String = "días de regado a la semana"
    var wateringTime: String = "hora de regado "
    var waterAmount: String = "cantidad de agua"
    var plantSize: String = "tamaño de la planta"
    var description: String = "Descripcion"
}
