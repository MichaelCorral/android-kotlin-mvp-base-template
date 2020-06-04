package com.michaelcorral.mvptemplate.api.models

import android.os.Parcelable
import com.michaelcorral.mvptemplate.utils.serializers.BigDecimalSerializer
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.*
import java.math.BigDecimal

@Parcelize
@Serializable
data class ItunesContentResults(
    @SerialName("trackId")
    val trackId: Long? = 0L,

    @SerialName("trackName")
    val trackName: String? = "",

    @SerialName("artworkUrl100")
    val artworkUrl100: String? = "",

    @Serializable(with = BigDecimalSerializer::class)
    val trackPrice: BigDecimal? = BigDecimal.ZERO,

    @SerialName("primaryGenreName")
    val primaryGenreName: String? = "",

    @SerialName("longDescription")
    val longDescription: String? = "",

    @SerialName("currency")
    val currency: String? = ""
) : Parcelable {

    val artworkUrl600: String?
        get() = artworkUrl100?.replace("100x100", "600x600")

    val artworkUrl200: String?
        get() = artworkUrl100?.replace("100x100", "200x200")
}