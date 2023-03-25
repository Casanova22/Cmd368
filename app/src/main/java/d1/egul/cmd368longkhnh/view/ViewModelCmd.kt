package d1.egul.cmd368longkhnh.view

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d1.egul.cmd368longkhnh.datalist.DataListCmd
import d1.egul.cmd368longkhnh.service.DataServiceCmd
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class ViewModelCmd : ViewModel() {

    private val manifest = DataServiceCmd()

    var chismisInfo: ArrayList<DataListCmd> = ArrayList<DataListCmd>()
    private val kuhaInfo = MutableLiveData<List<DataListCmd>?>()
    val dataListModel: MutableLiveData<List<DataListCmd>?> get() = kuhaInfo

    fun initJson() {
        manifest.getFirebaseJson().enqueue(object : retrofit2.Callback<List<DataListCmd>> {
            override fun onResponse(
                call: Call<List<DataListCmd>>,
                responde: Response<List<DataListCmd>>
            ) {
                try {
                    val sheeEsh: List<DataListCmd> = responde.body()!!
                    for (ind in sheeEsh.indices) {
                        chismisInfo.add(sheeEsh[ind])
                        Log.e(ContentValues.TAG, sheeEsh.toString())
                    }
                    kuhaInfo.value = chismisInfo
                    Log.e(ContentValues.TAG, chismisInfo.toString())

                } catch (e: Exception) {
                    kuhaInfo.value = chismisInfo
                }
            }
            override fun onFailure(call: Call<List<DataListCmd>>, t: Throwable) {
                kuhaInfo.value = chismisInfo
            }
        })
    }
}