package com.example.baskaryaapp.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BatikResponse(

	@field:SerializedName("data")
	val data: List<DataItem> = emptyList(),

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("ID Dokumen : ")
	val iDDokumen: String? = null,

	@field:SerializedName("Data Dokumen : ")
	val dataDokumen: DataDokumen? = null
)

@Parcelize
data class DataDokumen(

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable
