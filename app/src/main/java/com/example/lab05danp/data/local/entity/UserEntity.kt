package com.example.lab05danp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lab05danp.data.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val address: String,
    val lastUpdated: Long = System.currentTimeMillis()
) {
    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            password = "", // Avoid storing plain password locally if possible, or leave it empty in domain if not needed post-login
            address = address
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                name = user.name,
                email = user.email,
                address = user.address,
                lastUpdated = System.currentTimeMillis()
            )
        }
    }
}
