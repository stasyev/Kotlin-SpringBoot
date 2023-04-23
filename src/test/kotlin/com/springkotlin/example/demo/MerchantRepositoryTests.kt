package com.springkotlin.example.demo

import com.springkotlin.example.demo.Entities.Merchant
import com.springkotlin.example.demo.repository.MerchantRepository
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager



@DataJpaTest
class MerchantRepositoryTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val merchantRepository: MerchantRepository

) {

    @Test
    fun givenMerchant_saveToDB(){
        val merchant = Merchant("jake","jake@gmail.com" )
        merchantRepository.save(merchant)
        val merchantFound = merchantRepository.findByName("jake");
        assertNotNull(merchantFound)
        assertEquals(merchant, merchantFound)
    }


    @Test
        fun givenMerchant_whenSaved_thenFoundByName(){
            val merchant = Merchant("jake","jake@gmail.com" )
            entityManager.persist(merchant)
            entityManager.flush()
            val merchantFound = merchantRepository.findByName("jake");
            assertNotNull(merchantFound)
            assertEquals(merchant, merchantFound)
    }

    @Test
    fun givenMerchant_whenSaved_thenFound(){
        val merchant = Merchant("jake", "jake@gmail.com")
        entityManager.persist(merchant)
        val result = merchantRepository.save(merchant);
        assertNotNull(result)
        val merchantFound = merchantRepository.findByName("jake");
        assertNotNull(merchantFound)

    }
}