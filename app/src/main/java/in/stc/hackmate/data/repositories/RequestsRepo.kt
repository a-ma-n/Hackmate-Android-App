package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RequestsRepo {
    //todo rquest format-create model and invite response
    @POST("requests/request/60f269d086cadf64148a5d4f")
    suspend fun createReq(@HeaderMap headers: HashMap<String,String>, @Body params: Team): Call<RequestsResponse>

    @GET("requests/myRequests")
    suspend fun getMyReq(@HeaderMap headers: HashMap<String,String>): Response<List<InvitesSent>>

    @POST("requests/requestStatus/{id}/60f285451d61a55c141a9a02")
    suspend fun statusReq(@HeaderMap headers: HashMap<String,String>, @Path("id") id: String): Call<RequestsResponse>

    @DELETE("requests/60f281d34ecfbb3620daca01")
    suspend fun delReq(@HeaderMap headers: HashMap<String,String>): Call<RequestsResponse>

}