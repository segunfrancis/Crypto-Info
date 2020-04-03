package com.project.segunfrancis.fixaslabchallenge.repository

import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.ApiService
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

/**
 * Created by SegunFrancis
 */
@RunWith(JUnit4::class)
class CryptoRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var cryptoDao: CryptoDao
    private lateinit var response: ApiResponse
    private lateinit var response2: ApiResponse
    private lateinit var response3: ApiResponse
    private lateinit var response4: ApiResponse
    private lateinit var responseList: List<ApiResponse>

    @Before
    fun setup() {
        val mockedList: ArrayList<ApiResponse> = mock(List()<ApiResponse>::class)
        mockedList.add(response)

        responseList = ArrayList()
        response = ApiResponse("1", "Ethereum", "ETH", "232.286055", "0.63")
        response2 = ApiResponse("2", "Bitcoin", "BTC", "8755.286055", "0.23")
        response3 = ApiResponse("3", "Bit Gold", "BTG", "19.286055", "1.97")
        response4 = ApiResponse("4", "Tether", "USDT", "319.286055", "-0.09")
        (responseList as ArrayList).apply {
            add(response)
            add(response2)
            add(response3)
            add(response4)
        }
    }

    @Test
    fun test_getCryptoCoins() {
        val testObject = apiService.getCryptoCoins().execute()
        assertThat(testObject.body(), `is`(equalTo(responseList)))
    }

    @Test
    fun test_getCryptoList() {
        val testObject = cryptoDao
    }

    @Test
    fun test_insertCryptoList() {

    }
}