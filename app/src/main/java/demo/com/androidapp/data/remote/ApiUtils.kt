package demo.com.androidapp.data.remote

/**
 * Created by sundayakinsete on 27/01/2018.
 */
object ApiUtils {

    val BASE_URL = "https://api.github.com/"

    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)
}
