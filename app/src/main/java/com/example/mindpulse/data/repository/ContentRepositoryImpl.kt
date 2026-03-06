package com.example.mindpulse.data.repository

import com.example.mindpulse.domain.model.Meditation
import com.example.mindpulse.domain.repository.ContentRepository
import com.example.mindpulse.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ContentRepositoryImpl(
    private val firestore: FirebaseFirestore
) : ContentRepository {

    override fun getRecommendedMeditations(): Flow<Resource<List<Meditation>>> = callbackFlow {
        trySend(Resource.Loading())
        val subscription = firestore.collection("meditations")
            .limit(5)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to fetch meditations"))
                    return@addSnapshotListener
                }
                val list = snapshot?.toObjects(Meditation::class.java) ?: emptyList()
                trySend(Resource.Success(list))
            }
        awaitClose { subscription.remove() }
    }

    override fun getAllMeditations(): Flow<Resource<List<Meditation>>> = callbackFlow {
        trySend(Resource.Loading())
        val subscription = firestore.collection("meditations")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to fetch meditations"))
                    return@addSnapshotListener
                }
                val list = snapshot?.toObjects(Meditation::class.java) ?: emptyList()
                trySend(Resource.Success(list))
            }
        awaitClose { subscription.remove() }
    }

    override fun getMeditationDetails(id: String): Flow<Resource<Meditation>> = callbackFlow {
        trySend(Resource.Loading())
        val subscription = firestore.collection("meditations").document(id)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to fetch details"))
                    return@addSnapshotListener
                }
                val meditation = snapshot?.toObject(Meditation::class.java)
                if (meditation != null) {
                    trySend(Resource.Success(meditation))
                } else {
                    trySend(Resource.Error("Content not found"))
                }
            }
        awaitClose { subscription.remove() }
    }
}