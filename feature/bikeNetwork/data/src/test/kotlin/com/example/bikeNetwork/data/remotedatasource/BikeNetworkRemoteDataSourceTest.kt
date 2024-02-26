package com.example.bikeNetwork.data.remotedatasource

import com.example.common.model.Result
import com.example.core.network.service.IBikeNetworkService
import com.example.feature.bikeNetwork.presentation.dispatcherRule.MainDispatcherRule
import com.example.feature.bikeNetwork.presentation.testData.networkDetailDto
import com.example.feature.bikeNetwork.presentation.testData.networkId
import com.example.feature.bikeNetwork.presentation.testData.networkListDto
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
import retrofit2.Retrofit

class BikeNetworkRemoteDataSourceTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var service: IBikeNetworkService

    @MockK
    lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_getBikeNetworkList_loaded() = runTest {
        coEvery { service.getBikeNetworkList() } returns Response.success(networkListDto)
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
    fun bikeNetworkRemoteDataSource_getBikeNetworkList_error() = runTest {

        coEvery { service.getBikeNetworkList() } returns Response.error(
            500,
            ResponseBody.create(
                MediaType.get("text/json"),
                "{'statusCode':500,'statusMessage':'Server is not reachable'}"
            )
        )
        val remoteSource = BikeNetworkRemoteDataSource(service, retrofit)
        val response = remoteSource.getBikeNetworkList()
        coVerify {
            service.getBikeNetworkList()
        }
        assert(response.status == Result.Status.ERROR)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_getBikeNetworkDetail_loaded() = runTest {
        coEvery { service.getBikeNetworkDetail(networkId) } returns Response.success(
            networkDetailDto
        )
        val remoteSource = BikeNetworkRemoteDataSource(service, retrofit)
        val result = remoteSource.getBikeNetworkDetail(networkId)
        coVerify {
            service.getBikeNetworkDetail(any())
        }
        assert(result.status == Result.Status.SUCCESS)
        assert(result.data != null)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRemoteDataSource_getBikeNetworkDetail_error() = runTest {
        coEvery { service.getBikeNetworkDetail(any()) } returns Response.error(
            500,
            ResponseBody.create(
                MediaType.get("text/json"),
                "{'statusCode':500,'statusMessage':'Server is not reachable'}"
            )
        )
        val remoteSource = BikeNetworkRemoteDataSource(service, retrofit)
        val result = remoteSource.getBikeNetworkDetail(networkId)
        coVerify {
            service.getBikeNetworkDetail(any())
        }
        assert(result.status == Result.Status.ERROR)
    }
}