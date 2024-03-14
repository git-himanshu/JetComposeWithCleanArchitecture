package com.example.bikeNetwork.data.repository

import com.example.bikeNetwork.data.datasource.BikeNetworkDataSource
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkDetailRemoteDataMapper
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkRemoteListDataMapper
import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.common.model.Result
import com.example.testing.dispatcherRule.MainDispatcherRule
import com.example.testing.testData.NETWORK_ID
import com.example.testing.testData.networkDetailDto
import com.example.testing.testData.networkListDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BikeNetworkRepositoryImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var dataSource: BikeNetworkDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRepository_bike_network_list_is_loaded() = runTest {
        coEvery { dataSource.getBikeNetworkList() } returns Result(
            status = Result.Status.SUCCESS,
            data = networkListDto,
            error = null,
            message = null
        )
        val repository = BikeNetworkRepositoryImpl(
            dataSource = dataSource,
            listModelMapper = BikeNetworkRemoteListDataMapper(),
            detailModelMapper = BikeNetworkDetailRemoteDataMapper()
        )
        lateinit var result: Result<BikeNetworksEntity>
        val response = repository.getBikeNetworkList()
        coVerify {
            dataSource.getBikeNetworkList()
            response.collect {
                result = it
            }
        }
        assert(result.status == Result.Status.SUCCESS)
        result.data?.networks?.isNotEmpty()?.let { assert(it) }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRepository_error_in_loading_bike_network_list() = runTest {
        coEvery { dataSource.getBikeNetworkList() } returns Result(
            status = Result.Status.ERROR,
            data = null,
            error = null,
            message = null
        )
        val repository = BikeNetworkRepositoryImpl(
            dataSource = dataSource,
            listModelMapper = BikeNetworkRemoteListDataMapper(),
            detailModelMapper = BikeNetworkDetailRemoteDataMapper()
        )
        lateinit var result: Result<BikeNetworksEntity>
        val response = repository.getBikeNetworkList()
        coVerify {
            dataSource.getBikeNetworkList()
            response.collect {
                result = it
            }
        }
        assert(result.status == Result.Status.ERROR)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRepository_bike_network_detail_loaded() = runTest {
        coEvery { dataSource.getBikeNetworkDetail(any()) } returns Result(
            status = Result.Status.SUCCESS,
            data = networkDetailDto,
            error = null,
            message = null
        )
        val repository = BikeNetworkRepositoryImpl(
            dataSource = dataSource,
            listModelMapper = BikeNetworkRemoteListDataMapper(),
            detailModelMapper = BikeNetworkDetailRemoteDataMapper()
        )
        lateinit var result: Result<BikeNetworkDetailEntity>
        val response = repository.getBikeNetworkDetail(NETWORK_ID)
        coVerify {
            dataSource.getBikeNetworkDetail(any())
            response.collect {
                result = it
            }
        }
        assert(result.status == Result.Status.SUCCESS)
        assert(result.data != null)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkRepository_error_in_loading_bike_network_detail() = runTest {
        coEvery { dataSource.getBikeNetworkDetail(any()) } returns Result(
            status = Result.Status.ERROR,
            data = null,
            error = null,
            message = null
        )
        val repository = BikeNetworkRepositoryImpl(
            dataSource = dataSource,
            listModelMapper = BikeNetworkRemoteListDataMapper(),
            detailModelMapper = BikeNetworkDetailRemoteDataMapper()
        )
        lateinit var result: Result<BikeNetworkDetailEntity>
        val response = repository.getBikeNetworkDetail(NETWORK_ID)
        coVerify {
            dataSource.getBikeNetworkDetail(any())
            response.collect {
                result = it
            }
        }
        assert(result.status == Result.Status.ERROR)

    }
}