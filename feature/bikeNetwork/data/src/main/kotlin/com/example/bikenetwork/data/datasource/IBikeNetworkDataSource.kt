package com.example.bikenetwork.data.datasource
import androidx.navigation.NavArgs
import com.example.bikenetwork.data.dto.BikeNetworkDetailResponseDto
import com.example.bikenetwork.data.dto.BikeNetworkListResponseDto
import com.example.common.model.Result

interface IBikeNetworkDataSource {
    suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto>
    suspend fun getBikeNetworkDetail(networkId:String): Result<BikeNetworkDetailResponseDto>
}
