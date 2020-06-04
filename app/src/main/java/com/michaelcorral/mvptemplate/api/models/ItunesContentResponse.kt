package com.michaelcorral.mvptemplate.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItunesContentResponse(
    @SerialName("results")
    val results: List<ItunesContentResults>
)