package com.dnieln7.fake17.ui.auth

/**
 * Helper class to manage all Auth states
 *
 * @author dnieln7
 */
sealed class AuthState {

    /**
     * Nothing is happening. Used to reset form state.
     */
    object Nothing : AuthState()

    /**
     * There's some task executing in background.
     */
    object Loading : AuthState()

    /**
     * The background task ended without errors.
     */
    object Success : AuthState()

    /**
     * The background task ended with an error.
     *
     * @param error A description of what went wrong.
     */
    class Failure(val error: String) : AuthState()
}
