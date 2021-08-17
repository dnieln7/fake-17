package com.dnieln7.fake17.ui.home.cat

/**
 * Helper class to manage all Api states
 *
 * @author dnieln7
 */
sealed class ApiState {

    /**
     * There's some task executing in background.
     */
    object Loading : ApiState()

    /**
     * The background task ended without errors.
     */
    object Success : ApiState()

    /**
     * The background task ended with an error.
     *
     * @param error A description of what went wrong.
     */
    class Error(val error: String) : ApiState()
}
