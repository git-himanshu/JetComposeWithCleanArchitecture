package com.example.domain.usecase

import com.example.common.model.Result
import com.example.domain.repository.BikeNetworkRepository
import com.example.testing.dispatcherRule.MainDispatcherRule
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

class BikeNetworkListUseCaseImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var repository: BikeNetworkRepository

    private lateinit var listUseCase: BikeNetworkListUseCase

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
        val repository = BikeNetworkListUseCaseImpl(repository)
        listUseCase = repository
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkUseCase_get_bike_network_list_is_called() = runTest {
        listUseCase.getList()
        coVerify {
            repository.getBikeNetworkList()
        }
    }
}