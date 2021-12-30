package `in`.stc.hackmate.data.repositories

import `in`.stc.hackmate.data.models.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.Response

interface InvitesRepo {

    //todo invite format-create model and invite response
    @POST("invites/invite/60fcca948bed930015d67619/60f186b2b7e1c25f80f13f60")
    suspend fun createInv(@HeaderMap headers: HashMap<String,String>, @Body params: Team): Call<InvitesResponse>

    @GET("invites/myInvites")
    suspend fun getMyInv(@HeaderMap headers: HashMap<String,String>): Response<List<InvitesReceived>>

    @POST("invites/inviteStatus/{id}/60f2674c554f7653b01e683f")
    suspend fun statusInv(@HeaderMap headers: HashMap<String,String>, @Path("id") id: String)

    @DELETE("invites/60f2610eff22d53c9415d85c")
    suspend fun delInv(@HeaderMap headers: HashMap<String,String>): Call<InvitesResponse>

}