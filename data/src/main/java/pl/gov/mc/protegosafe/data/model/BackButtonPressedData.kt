package pl.gov.mc.protegosafe.data.model

import com.google.gson.annotations.SerializedName

data class BackButtonPressedData(
    @SerializedName("back")
    val back: Boolean = true
)
