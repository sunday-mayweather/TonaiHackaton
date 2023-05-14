package app.simulacra.networkcore.token

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface TokenRestApi {

    // Post type will be added
    @POST("/v1/auth/refresh_token")
    fun refreshAccessToken(@Field("refresh_token") refreshToken: String): Call<AuthTokenModel>

}