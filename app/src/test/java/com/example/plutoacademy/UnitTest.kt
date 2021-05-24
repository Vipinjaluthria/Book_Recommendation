package com.example.plutoacademy

import android.text.format.DateUtils
import com.example.plutoacademy.service.utility.DateUtils.Companion.convertToDateString
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {

    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun dateConversion_isCorrect(){
        val actual = "2017-12-15T20:00:53Z"
        val expected = "2017-12-15 20:00:53"
        Assert.assertEquals(expected,convertToDateString(actual))
    }
}