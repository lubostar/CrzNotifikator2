package sk.lubostar.crz.network

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import sk.lubostar.crz.network.model.Contract

interface CrzApiService {
    companion object{
        const val BASE_URL = "https://datahub.ekosystem.slovensko.digital/api/data/crz/"
        // contracts/sync?last_id=2517939&since=2016-07-08T13%3A54%3A05.176752Z"

        private val moshi = Moshi.Builder()
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    }

    object CrzApi{
        val retrofitService: CrzApiService by lazy {
            retrofit.create(CrzApiService::class.java)
        }
    }

    @GET("contracts/sync?last_id=2517939&since=2016-07-08T13%3A54%3A05.176752Z")
    fun getContracts() : Call<List<Contract>>

    @GET("contracts/sync")
    fun getContracts(@Query("last_id") lastId: String, @Query("since") since: String)
            :Call<List<Contract>>
}

