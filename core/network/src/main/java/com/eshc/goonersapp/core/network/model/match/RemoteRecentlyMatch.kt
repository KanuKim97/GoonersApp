package com.eshc.goonersapp.core.network.model.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRecentlyMatch(
    @SerialName("match") val match: RemoteMatchTeam,
    @SerialName("matchDetail") val matchDetail: List<RemoteMatchDetail>
)