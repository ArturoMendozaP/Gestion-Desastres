package com.example.gestiondesastres.core.repositories

import com.example.gestiondesastres.models.UserProfile
import com.example.gestiondesastres.core.ResponseService

interface UserService {
    suspend fun saveUserInfo(userProfile: UserProfile): ResponseService<Boolean>
}