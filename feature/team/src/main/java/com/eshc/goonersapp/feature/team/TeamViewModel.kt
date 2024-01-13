package com.eshc.goonersapp.feature.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.goonersapp.core.network.GnrRemoteDataSource
import com.eshc.goonersapp.core.network.model.RemotePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val gnrRemoteDataSource: GnrRemoteDataSource
) : ViewModel() {

    val items = MutableStateFlow<List<RemotePlayer>>(emptyList())

    init {
        viewModelScope.launch {
            items.emit(
                gnrRemoteDataSource.getPlayerList()
            )
        }
    }
}