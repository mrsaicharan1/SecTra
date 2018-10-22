package org.freeflo.sashank.sectra.utils

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RetrofitService {
    @Multipart
    @POST("/")
    fun postImage(@Part image: MultipartBody.Part, @Part("name") name: RequestBody) : Call<ResponseBody>
}

