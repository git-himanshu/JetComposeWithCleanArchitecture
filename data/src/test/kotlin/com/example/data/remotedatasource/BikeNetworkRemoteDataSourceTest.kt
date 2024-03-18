package com.example.data.remotedatasource

import com.example.client.NetworkClient
import com.example.common.model.Error
import com.example.common.model.Result
import com.example.data.dto.BikeNetworkDetailResponseDto
import com.example.data.dto.BikeNetworkListResponseDto
import com.example.data.service.BikeNetworkService
import com.example.testing.dispatcherRule.MainDispatcherRule
import com.example.testing.testData.MEDIA_TYPE
import com.example.testing.testData.NETWORK_ID
import com.example.testing.testData.SERVER_ERROR_CONTENT
import com.example.testing.testData.SERVER_NOT_REACHABLE_ERROR_CODE
import com.example.testing.testData.createBikeNetworkDetailResponseDto
import com.example.testing.testData.createNetworkListDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class BikeNetworkRemoteDataSourceTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var service: BikeNetworkService

    @MockK
    lateinit var client: NetworkClient

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_bike_network_list_is_loaded() = runTest {
        coEvery { service.getBikeNetworkList() } returns Response.success(createNetworkListDto())
        coEvery { client.executeRequest<BikeNetworkListResponseDto>(any()) } returns Result.success(
            data = service.getBikeNetworkList().body()
        )
        val remoteSource = BikeNetworkRemoteDataSource(service, client)
        val response = remoteSource.getBikeNetworkList()
        coVerify {
            service.getBikeNetworkList()
        }
        assert(response.status == Result.Status.SUCCESS)
        response.data?.bikeNetworks?.isNotEmpty()?.let { assert(it) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_server_not_reachable_when_loading_bike_network_list() =
        runTest {

            coEvery { service.getBikeNetworkList() } returns Response.error(
                SERVER_NOT_REACHABLE_ERROR_CODE,
                ResponseBody.create(
                    MediaType.get(MEDIA_TYPE),
                    SERVER_ERROR_CONTENT
                )
            )
            coEvery { client.executeRequest<Error>(any()) } returns Result.error(
                message = SERVER_ERROR_CONTENT,
                error = Error(SERVER_NOT_REACHABLE_ERROR_CODE, SERVER_ERROR_CONTENT),
            )
            val remoteSource = BikeNetworkRemoteDataSource(service, client)
            val response = remoteSource.getBikeNetworkList()
            assert(response.status == Result.Status.ERROR)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_bike_network_detail_is_loaded() = runTest {
        coEvery { service.getBikeNetworkDetail(NETWORK_ID) } returns Response.success(
            createBikeNetworkDetailResponseDto()
        )
        coEvery { client.executeRequest<BikeNetworkDetailResponseDto>(any()) } returns Result.success(
            data = service.getBikeNetworkDetail(NETWORK_ID).body()
        )
        val remoteSource = BikeNetworkRemoteDataSource(service, client)
        val result = remoteSource.getBikeNetworkDetail(NETWORK_ID)
        coVerify {
            service.getBikeNetworkDetail(NETWORK_ID)
        }
        assert(result.status == Result.Status.SUCCESS)
        assert(result.data != null)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_server_not_reachable_when_loading_bike_network_detail() =
        runTest {
            coEvery { service.getBikeNetworkDetail(any()) } returns Response.error(
                SERVER_NOT_REACHABLE_ERROR_CODE,
                ResponseBody.create(
                    MediaType.get(MEDIA_TYPE),
                    SERVER_ERROR_CONTENT
                )
            )
            coEvery { client.executeRequest<Error>(any()) } returns Result.error(
                message = SERVER_ERROR_CONTENT,
                error = Error(SERVER_NOT_REACHABLE_ERROR_CODE, SERVER_ERROR_CONTENT),
            )
            val remoteSource = BikeNetworkRemoteDataSource(service, client)
            val result = remoteSource.getBikeNetworkDetail(NETWORK_ID)
            assert(result.status == Result.Status.ERROR)
        }
}