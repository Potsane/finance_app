package com.app.financeapp.ui.error

import com.app.financeapp.ui.base.BaseFinanceAppViewModel

class ErrorViewModel : BaseFinanceAppViewModel(){
    fun retry() = goBack()
}