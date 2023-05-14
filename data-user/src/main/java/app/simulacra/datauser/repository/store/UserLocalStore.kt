package app.simulacra.datauser.repository.store

import app.simulacra.datauser.database.UserDatabase
import app.simulacra.datauser.database.entities.DeviceEntity
import app.simulacra.datauser.database.entities.UserEntity
import javax.inject.Inject

interface UserLocalStore {

    suspend fun getDevice(): DeviceEntity?

    suspend fun getUser(): UserEntity?

    suspend fun saveDevice(device: DeviceEntity)

    suspend fun saveUser(user: UserEntity)

}

class UserLocalStoreImpl @Inject constructor(private val database: UserDatabase) : UserLocalStore {

    override suspend fun getDevice(): DeviceEntity? {
        return database.getUserDao().selectDevice()
    }

    override suspend fun getUser(): UserEntity? {
        return database.getUserDao().selectUser()
    }

    override suspend fun saveDevice(device: DeviceEntity) {
        return database.getUserDao().putDevice(device)
    }

    override suspend fun saveUser(user: UserEntity) {
        return database.getUserDao().putUser(user)
    }
}