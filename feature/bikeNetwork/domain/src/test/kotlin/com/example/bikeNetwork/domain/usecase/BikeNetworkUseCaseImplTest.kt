package com.example.bikeNetwork.domain.usecase

import com.example.bikeNetwork.domain.repository.BikeNetworkRepository
import com.example.common.model.Result
import com.example.testing.dispatcherRule.MainDispatcherRule
import com.example.testing.testData.NETWORK_ID
import com.example.testing.testData.createBikeNetworksEntity
import com.example.testing.testData.createNetworkDetailEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BikeNetworkUseCaseImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var repository: BikeNetworkRepository

    private lateinit var useCase: BikeNetworkUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { repository.getBikeNetworkList() } returns flow {
            emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = createBikeNetworksEntity(),
                    error = null,
                    message = null
                )
            )
        }
        coEvery { repository.getBikeNetworkDetail(any()) } returns flow {
            emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = createNetworkDetailEntity(),
                    error = null,
                    message = null
                )
            )
        }
        useCase = BikeNetworkUseCaseImpl(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkUseCase_get_bike_network_list_is_called() = runTest {
        useCase.getList()
        coVerify {
            repository.getBikeNetworkList()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkUseCase_get_bike_network_detail_is_called() = runTest {
        useCase.getDetail(NETWORK_ID)
        coVerify {
            repository.getBikeNetworkDetail(any())
        }
    }
}