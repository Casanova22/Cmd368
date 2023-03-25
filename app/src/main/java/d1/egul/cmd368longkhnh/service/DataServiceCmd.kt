package d1.egul.cmd368longkhnh.service

import d1.egul.cmd368longkhnh.datalist.DataListCmd
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataServiceCmd {
    private val fireBaseProject = "https://android-projects-82fc0-default-rtdb.firebaseio.com/"

    private val getApiJson : JsonInterfaceCmd = Retrofit.Builder().baseUrl(fireBaseProject).addConverterFactory(
        GsonConverterFactory.create()
    ).build().create(JsonInterfaceCmd::class.java)

    fun getFirebaseJson(): Call<List<DataListCmd>> {
        return getApiJson.readyJson()
    }
}