package com.example.gestiondesastres.core.repositories

import com.example.gestiondesastres.core.ResponseService
import com.example.gestiondesastres.models.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRepository : UserService {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override suspend fun saveUserInfo(userProfile: UserProfile): ResponseService<Boolean> = withContext(Dispatchers.IO) {
        try {
            // Obtenemos el ID único del usuario que acaba de iniciar sesión
            val userId = auth.currentUser?.uid

            if (userId != null) {
                // Guardamos sus datos en la colección "users" usando su ID exacto
                firestore.collection("users").document(userId).set(userProfile).await()
                ResponseService.Success(true)
            } else {
                ResponseService.Error("Usuario no autenticado")
            }
        } catch (e: Exception) {
            ResponseService.Error(e.localizedMessage ?: "Error al guardar los datos")
        }
    }
}