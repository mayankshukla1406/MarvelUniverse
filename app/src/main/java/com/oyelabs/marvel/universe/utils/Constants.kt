package com.oyelabs.marvel.universe.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "881f5dabd4bfa786f3614e3397af79e7"
        const val PRIVATE_KEY = "a52821a455577983f4e9397051409c985c3d7134"
        const val limit = "20"
        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}