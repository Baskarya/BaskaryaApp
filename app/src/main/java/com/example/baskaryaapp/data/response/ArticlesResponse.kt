package com.example.baskaryaapp.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

//@Parcelize
//data class DataDokumen(
//
//	@field:SerializedName("author")
//	val author: String? = null,
//
//	@field:SerializedName("id")
//	val id: String? = null,
//
//	@field:SerializedName("publishedDate")
//	val publishedDate: PublishedDate? = null,
//
//	@field:SerializedName("title")
//	val title: String? = null,
//
//	@field:SerializedName("content")
//	val content: String? = null,
//
//	@field:SerializedName("url")
//	val url: String? = null
//) : Parcelable

@Parcelize
data class PublishedDate(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int? = null,

	@field:SerializedName("_seconds")
	val seconds: Int? = null
) : Parcelable

//@Parcelize
//data class DataItem(
//
//	@field:SerializedName("ID Dokumen : ")
//	val iDDokumen: String? = null,
//
//	@field:SerializedName("Data Dokumen : ")
//	val dataDokumen: DataDokumen? = null
//) : Parcelable
