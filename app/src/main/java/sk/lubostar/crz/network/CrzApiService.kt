package sk.lubostar.crz.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sk.lubostar.crz.network.model.Contract

enum class CrzApiStatus { LOADING, ERROR, DONE }

interface CrzApiService {
    companion object{
        const val BASE_URL = "https://datahub.ekosystem.slovensko.digital/api/data/crz/"
        // contracts/sync?last_id=2517939&since=2016-07-08T13%3A54%3A05.176752Z"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    object CrzApi{
        val retrofitService: CrzApiService by lazy {
            retrofit.create(CrzApiService::class.java)
        }
    }

    @GET("contracts/sync?last_id=2517939&since=2016-07-08T13%3A54%3A05.176752Z")
    fun getContracts() : Observable<List<Contract>>

    @GET("contracts/sync")
    fun getContracts(@Query("last_id") lastId: String, @Query("since") since: String)
            : Observable<Contract>

    @GET("contracts/{id}")
    fun getContractDetail(@Path("id") id: Int) :Observable<Contract>
}

