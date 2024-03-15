package com.example.bikeNetwork.data.remotedatasource

import com.example.common.model.Error
import com.example.common.model.Result
import com.example.core.network.service.BikeNetworkService
import com.example.testing.dispatcherRule.MainDispatcherRule
import com.example.testing.testData.MEDIA_TYPE
import com.example.testing.testData.NETWORK_ID
import com.example.testing.testData.SERVER_ERROR_CONTENT
import com.example.testing.testData.SERVER_NOT_REACHABLE
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
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

class BikeNetworkRemoteDataSourceTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var service: BikeNetworkService

    @MockK
    lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_bike_network_list_is_loaded() = runTest {
        coEvery { service.getBikeNetworkList() } returns Response.success(createNetworkListDto())
        val remoteSource = BikeNetworkRemoteDataSource(service, retrofit)
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
            coEvery { retrofit.responseBodyConverter<Error>(any(), any()) } returns
                    Converter {
                        Error(
                            statusCode = SERVER_NOT_REACHABLE_ERROR_CODE,
                            statusMessage = SERVER_NOT_REACHABLE
                        )
                    }

            val remoteSource = BikeNetworkRemoteDataSource(service, retrofit)
            val response = remoteSource.getBikeNetworkList()
            coVerify {
                service.getBikeNetworkList()
            }
            assert(response.status == Result.Status.ERROR)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_bike_network_detail_is_loaded() = runTest {
        coEvery { service.getBikeNetworkDetail(NETWORK_ID) } returns Response.success(
            createBikeNetworkDetailResponseDto()
        )
        val remoteSource = BikeNetworkRemoteDataSource(service, retrofit)
        val result = remoteSource.getBikeNetworkDetail(NETWORK_ID)
        coVerify {
            service.getBikeNetworkDetail(any())
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
            coEvery { retrofit.responseBodyConverter<Error>(any(), any()) } returns
                    Converter {
                        Error(
                            statusCode = SERVER_NOT_REACHABLE_ERROR_CODE,
                            statusMessage = SERVER_NOT_REACHABLE
                        )
                    }
            val remoteSource = BikeNetworkRemoteDataSource(service, retrofit)
            val result = remoteSource.getBikeNetworkDetail(NETWORK_ID)
            coVerify {
                service.getBikeNetworkDetail(any())
            }
            assert(result.status == Result.Status.ERROR)
        }
}