package com.app.financeapp.util

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DateTimeUtilKtTest{

    @Test
    fun `should get correct date`(){
        val date =  getFormattedDate("2022-03-10T10:06:14Z")
        assertEquals("10 Mar, 2022", date)
    }
}