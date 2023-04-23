package com.springkotlin.example.demo.repository

import com.springkotlin.example.demo.Entities.Merchant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MerchantRepository: CrudRepository<Merchant, Int> {
    fun findByName(name: String): Merchant
    fun save(merchant: Merchant)
}