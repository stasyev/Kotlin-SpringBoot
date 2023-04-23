package com.springkotlin.example.demo.controller

import com.fasterxml.jackson.annotation.JsonRawValue
import com.springkotlin.example.demo.DTO.MerchantDTO
import com.springkotlin.example.demo.Entities.Merchant
import com.springkotlin.example.demo.repository.MerchantRepository

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(private val repository: MerchantRepository) {


    @GetMapping("/")
    fun allInfo(): String{

        val mutList = mutableSetOf<MerchantDTO>()

        val merchant = repository.findAll().map { it.render() }

        return merchant.toString()
    }


    @PostMapping("/merchant/add")
    fun addMerchant(@RequestBody merchant: Merchant): HttpStatus{
        return try {
            repository.save(merchant)
            HttpStatus.CREATED
        }catch (e: Exception){
            HttpStatus.NOT_MODIFIED
        }


    }

    @DeleteMapping("/merchant/delete")
    fun deleteMerchant(@RequestParam id: Int): HttpStatus {
        return try {
            repository.deleteById(id)
            HttpStatus.GONE
        } catch (e: Exception) {
            HttpStatus.NOT_MODIFIED
        }
    }
    @PutMapping("/merchant/modify")
    fun modifyMerchant(@RequestBody merchant: Merchant): HttpStatus{

        return try{
            val doesItExist = merchant.id?.let { repository.existsById(it) }
            if(doesItExist == true){
                 var newMerchant: Merchant?  = null
                 merchant.id?.let { newMerchant = repository.findById(it).get() }
                 newMerchant?.email = merchant.email
                 newMerchant?.name = merchant.name
                 newMerchant?.let { repository.save(it) }
            }
            repository.save(merchant)
            HttpStatus.ACCEPTED
        }catch (e: Exception){
            HttpStatus.NOT_MODIFIED
        }
    }

    fun Merchant.render() = MerchantDTO(
        name,
        email
    )
}
