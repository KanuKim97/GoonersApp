package com.eshc.goonersapp.core.data.mapper

import com.eshc.goonersapp.core.database.model.PlayerEntity
import com.eshc.goonersapp.core.domain.model.player.Player
import com.eshc.goonersapp.core.network.model.player.RemotePlayer

fun RemotePlayer.toModel() = Player(
    id = playerId ?: 0,
    name = playerName ?: "",
    backNumber = backNumber ?: 0,
    birthDate = birthDate ?: "",
    height = height ?: 0,
    weight = weight ?: 0,
    imageUrl = playerImage ?: "",
    contractStartDate = "",
    contractEndDate = "",
    position = position ?: "",
    positionInitial = positionInitial ?: "",
    positionCategory = positionCategory  ?: "",
    nationality = nationality ?: ""
)

fun RemotePlayer.toEntity() = PlayerEntity(
    id = playerId ?: 0,
    name = playerName  ?: "",
    backNumber = backNumber ?: 0,
    birthDate = birthDate ?: "",
    height = height ?: 0,
    weight = weight ?: 0,
    imageUrl = playerImage ?: "",
    contractStartDate = "",
    contractEndDate = "",
    position = position ?: "",
    nationality = nationality ?: ""
)

fun PlayerEntity.toModel() = Player(
    id = id,
    name = name,
    backNumber = backNumber,
    birthDate = birthDate,
    height = height,
    weight = weight,
    imageUrl = imageUrl,
    contractStartDate = contractStartDate,
    contractEndDate = contractEndDate,
    position = position,
    nationality = nationality
)