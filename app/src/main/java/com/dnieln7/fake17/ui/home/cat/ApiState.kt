package com.dnieln7.fake17.ui.home.cat

sealed class ApiState {
    object Loading : ApiState()
    object Success : ApiState()
    class Error(val error: String) : ApiState()
}
