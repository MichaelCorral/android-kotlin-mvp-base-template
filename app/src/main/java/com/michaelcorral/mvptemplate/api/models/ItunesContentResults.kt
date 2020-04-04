package com.michaelcorral.mvptemplate.api.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class ItunesContentResults(
    val trackId: Long? = 0L,
    val trackName: String? = "",
    val artworkUrl100: String? = "",
    val trackPrice: BigDecimal? = BigDecimal.ZERO,
    val primaryGenreName: String? = "",
    val longDescription: String? = "",
    val currency: String? = ""
) : Parcelable {

    val artworkUrl600: String?
        get() = artworkUrl100?.replace("100x100", "600x600")

    val artworkUrl200: String?
        get() = artworkUrl100?.replace("100x100", "200x200")
}