package app.simulacra.datauser.database.daos

import androidx.room.*
import app.simulacra.datauser.database.entities.DeviceEntity
import app.simulacra.datauser.database.entities.UserEntity

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDevice(deviceEntity: DeviceEntity)

    @Query("SELECT * FROM Device LIMIT 1")
    abstract fun selectDevice(): DeviceEntity?

    @Query("DELETE FROM Device")
    abstract fun deleteDevice()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM User LIMIT 1")
    abstract fun selectUser(): UserEntity?

    @Query("DELETE FROM User")
    abstract fun deleteUser()

    @Transaction
    open fun putDevice(deviceEntity: DeviceEntity) {
        deleteDevice()
        insertDevice(deviceEntity)
    }

    @Transaction
    open fun putUser(userEntity: UserEntity) {
        deleteUser()
        insertUser(userEntity)
    }

}