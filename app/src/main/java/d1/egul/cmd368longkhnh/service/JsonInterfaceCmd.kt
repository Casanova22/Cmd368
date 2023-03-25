package d1.egul.cmd368longkhnh.service

import d1.egul.cmd368longkhnh.datalist.DataListCmd
import retrofit2.http.GET

interface JsonInterfaceCmd {
    @GET("MarchJSON.json?auth=nvOo8N6bit2yEJCw981nfvJETnp4CUDdridkNie7")
    fun readyJson(): retrofit2.Call<List<DataListCmd>>
}