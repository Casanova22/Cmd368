package d1.egul.cmd368longkhnh.datalist

import androidx.annotation.Keep

@Keep
data class DataListCmd(
    var LoginKey : String?,
    var PathName : String?,
    var RegisterKey : String?,
    var Status : Boolean?,
    var Url : String?,
)