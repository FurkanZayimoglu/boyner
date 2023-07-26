package com.example.boyner.model.SourcesModel

import com.google.gson.annotations.SerializedName

data class SourcesData(
   @SerializedName("status")
   val status: String,
   @SerializedName("sources")
   val sources: List<SourcesSubListData>
)
