package com.hssn_mirza.digitify_code_challenge.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.hssn_mirza.digitify_code_challenge.model.MovieModel

class AllMoviesResponseModel {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<MovieModel>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
}