package com.project.segunfrancis.crypto_info.repository

import com.project.segunfrancis.crypto_info.dataSource.remote.ApiService
import com.project.segunfrancis.crypto_info.dataSource.local.CryptoDao
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by SegunFrancis
 */
@RunWith(JUnit4::class)
class CryptoRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var cryptoDao: CryptoDao

    @Test
    fun test_getCryptoList() {
        val testObject = cryptoDao
    }

    @Test
    fun test_insertCryptoList() {

    }
}