package com.eshc.goonersapp.core.domain.usecase.match

import com.eshc.goonersapp.core.domain.model.DataResult
import com.eshc.goonersapp.core.domain.model.match.MatchRecently
import com.eshc.goonersapp.core.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentlyMatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    operator fun invoke(): Flow<DataResult<MatchRecently>> =
        matchRepository.getRecentlyMatch()
}