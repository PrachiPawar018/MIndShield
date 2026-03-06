package com.example.mindshield.data.repository

import com.example.mindshield.domain.model.User
import com.example.mindshield.domain.repository.AuthRepository
import com.example.mindshield.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun login(email: String, password: String): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(Resource.Success(true))
                } else {
                    trySend(Resource.Error(task.exception?.message ?: "Login failed"))
                }
            }
        awaitClose()
    }

    override fun signUp(fullName: String, email: String, password: String): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    val user = User(
                        id = firebaseUser?.uid ?: "",
                        fullName = fullName,
                        email = email
                    )
                    firestore.collection("users").document(user.id).set(user)
                        .addOnCompleteListener { firestoreTask ->
                            if (firestoreTask.isSuccessful) {
                                trySend(Resource.Success(true))
                            } else {
                                trySend(Resource.Error(firestoreTask.exception?.message ?: "Failed to save user data"))
                            }
                        }
                } else {
                    trySend(Resource.Error(task.exception?.message ?: "Registration failed"))
                }
            }
        awaitClose()
    }

    override fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser(): User? {
        val firebaseUser = auth.currentUser ?: return null
        return User(
            id = firebaseUser.uid,
            fullName = firebaseUser.displayName ?: "",
            email = firebaseUser.email ?: "",
            isEmailVerified = firebaseUser.isEmailVerified
        )
    }

    override fun sendEmailVerification(): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trySend(Resource.Success(true))
            } else {
                trySend(Resource.Error(task.exception?.message ?: "Failed to send verification email"))
            }
        }
        awaitClose()
    }

    override fun resetPassword(email: String): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trySend(Resource.Success(true))
            } else {
                trySend(Resource.Error(task.exception?.message ?: "Failed to send reset email"))
            }
        }
        awaitClose()
    }

    override fun updateProfile(fullName: String, profileImageUrl: String?): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        val uid = auth.currentUser?.uid ?: return@callbackFlow
        val updates = mutableMapOf<String, Any>("fullName" to fullName)
        profileImageUrl?.let { updates["profileImageUrl"] = it }

        firestore.collection("users").document(uid).update(updates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(Resource.Success(true))
                } else {
                    trySend(Resource.Error(task.exception?.message ?: "Failed to update profile"))
                }
            }
        awaitClose()
    }
}
