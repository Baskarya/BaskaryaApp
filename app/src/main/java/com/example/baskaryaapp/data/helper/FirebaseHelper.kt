package com.example.baskaryaapp.data.helper

import com.example.baskaryaapp.data.database.Bookmark
import com.example.baskaryaapp.data.response.BatikItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseHelper {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Mendapatkan referensi koleksi bookmark
    private fun getBookmarkCollection(): CollectionReference {
        return firestore.collection("bookmarks")
    }

    fun addBookmark(batikId: String, title:String, imageUrl: String, batikList: MutableList<BatikItem>) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val bookmark = Bookmark(batikId, userId, title, imageUrl)
            getBookmarkCollection().add(bookmark)
                .addOnSuccessListener {
                    // Update status bookmark pada model data
                    batikList.find { it.id == batikId }?.isBookmarked = true
//                    notifyDataSetChanged()
                }
        }
    }

    fun removeBookmark(batikId: String, title:String, imageUrl: String, batikList: MutableList<BatikItem>) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            getBookmarkCollection()
                .whereEqualTo("batikId", batikId)
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        document.reference.delete()
                        // Update status bookmark pada model data
                        batikList.find { it.id == batikId }?.isBookmarked = false
//                        notifyDataSetChanged()
                    }
                }
        }
    }

    fun getBookmarkedBatiks(userId: String, callback: (List<String?>) -> Unit) {
        getBookmarkCollection()
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                val bookmarkedIds = documents.map { it.getString("batikId") }.toList()
                callback(bookmarkedIds)
            }
    }
}