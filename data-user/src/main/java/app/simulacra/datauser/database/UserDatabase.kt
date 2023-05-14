package app.simulacra.datauser.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.simulacra.datauser.database.daos.UserDao
import app.simulacra.datauser.database.entities.DeviceEntity
import app.simulacra.datauser.database.entities.UserEntity

@Database(version = 1, entities = [DeviceEntity::class, UserEntity::class])
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

}