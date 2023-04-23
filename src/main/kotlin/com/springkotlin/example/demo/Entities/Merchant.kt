package com.springkotlin.example.demo.Entities

import jakarta.persistence.*

@Entity(name = "merchants")
class Merchant(
     var name: String,
     var email: String,

    @Id @GeneratedValue
    var id: Int? = null
)