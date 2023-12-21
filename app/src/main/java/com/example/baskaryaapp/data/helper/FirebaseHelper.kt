package com.example.baskaryaapp.data.helper

import android.util.Log
import com.example.baskaryaapp.data.database.BookmarkArticles
import com.example.baskaryaapp.data.database.BookmarkBatik
import com.example.baskaryaapp.data.database.BookmarkCustom
import com.example.baskaryaapp.data.response.ArticlesItem
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.data.response.Data
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

    fun addBookmarkBatik(batikId: String, title:String, imageUrl: String, batikList: MutableList<BatikItem>) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val bookmark = BookmarkBatik(batikId, userId, title, imageUrl)
            getBookmarkCollection().add(bookmark)
                .addOnSuccessListener {
                    // Update status bookmark pada model data
                    batikList.find { it.id == batikId }?.isBookmarked = true
//                    notifyDataSetChanged()
                }
        }
    }

    fun removeBookmarkBatik(batikId: String, title:String, imageUrl: String, batikList: MutableList<BatikItem>) {
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

    fun addBookmarkArticle(articleId: String, title:String, imageUrl: String, articleList: MutableList<ArticlesItem>) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val bookmark = BookmarkArticles(articleId, userId, title, imageUrl)
            getBookmarkCollection().add(bookmark)
                .addOnSuccessListener {
                    // Update status bookmark pada model data
                    articleList.find { it.id == articleId }?.isBookmarked = true
//                    notifyDataSetChanged()
                }
        }
    }

    fun removeBookmarkArticle(articleId: String, title:String, imageUrl: String, articleList: MutableList<ArticlesItem>) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            getBookmarkCollection()
                .whereEqualTo("articleId", articleId)
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        document.reference.delete()
                        // Update status bookmark pada model data
                        articleList.find { it.id == articleId }?.isBookmarked = false
//                        notifyDataSetChanged()
                    }
                }
        }
    }

    fun getBookmarkedArticles(userId: String, callback: (List<String?>) -> Unit) {
        getBookmarkCollection()
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                val bookmarkedIds = documents.map { it.getString("articleId") }.toList()
                callback(bookmarkedIds)
            }
    }

    fun addBookmarkCustom(customId: String, name:String, imageUrl: String, customList: MutableList<Data>) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val bookmark = BookmarkCustom(customId, userId, name, imageUrl)
            getBookmarkCollection().add(bookmark)
                .addOnSuccessListener {
                    // Update status bookmark pada model data
                    customList.find { it.id == customId }?.isBookmarked = true
//                    notifyDataSetChanged()
                }
        }
    }

    fun removeBookmarkCustom(customId: String, name:String, imageUrl: String, customList: MutableList<Data>) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            getBookmarkCollection()
                .whereEqualTo("customId", customId)
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        document.reference.delete()
                        // Update status bookmark pada model data
                        customList.find { it.id == customId }?.isBookmarked = false
//                        notifyDataSetChanged()
                    }
                }
        }
    }

    fun getBookmarkedCustom(userId: String, callback: (List<String?>) -> Unit) {
        getBookmarkCollection()
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                val bookmarkedIds = documents.map { it.getString("customId") }.toList()
                callback(bookmarkedIds)
            }
    }

//    fun fetchCustomizations(customIds: List<String?>, callback: (List<Data>) -> Unit) {
//        val customizations = mutableListOf<Data>()
//
//        customIds.forEach { customId ->
//            customId?.let {
//                firestore.collection("bookmarks").document(it)
//                    .get()
//                    .addOnSuccessListener { documentSnapshot ->
//                        if (documentSnapshot.exists()) {
//                            val data = documentSnapshot.toObject(Data::class.java)
//                            data?.let {
//                                customizations.add(it)
//                            }
//                        }
//
//                        if (customizations.size == customIds.size) {
//                            callback(customizations)
//                            // Tambahkan log untuk memastikan bahwa callback dipanggil
//                            Log.d("FirestoreCallback", "Callback called with data: $customizations")
//                        }
//                    }
//                    .addOnFailureListener { e ->
//                        // Tangkap error jika terjadi
//                        Log.e("FirestoreError", "Error fetching customization data: $e")
//                    }
//            }
//        }
//    }

    fun fetchCustomizations(userId: String, callback: (List<BookmarkCustom>) -> Unit) {
        getBookmarkCollection()
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                val bookmarkedCustomizations = mutableListOf<BookmarkCustom>()
                for (document in documents) {
                    val customId = document.getString("customId")
                    val name = document.getString("name")
                    val imageUrl = document.getString("imageUrl")

                    if (customId != null && name != null && imageUrl != null) {
                        val bookmarkedCustom = BookmarkCustom(customId, userId, name, imageUrl)
                        bookmarkedCustomizations.add(bookmarkedCustom)
                    }
                }

                callback(bookmarkedCustomizations)
            }
            .addOnFailureListener { e ->
                // Tangkap error jika terjadi
                Log.e("FirestoreError", "Error fetching customization data: $e")
            }
    }
}