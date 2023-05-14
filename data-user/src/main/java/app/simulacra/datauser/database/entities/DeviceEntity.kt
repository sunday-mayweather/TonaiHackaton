package app.simulacra.datauser.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device")
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "device_id") val deviceId: String,
    @ColumnInfo(name = "push_token") val pushToken: String,
    @ColumnInfo(name = "time_zone") val timeZone: Long
)