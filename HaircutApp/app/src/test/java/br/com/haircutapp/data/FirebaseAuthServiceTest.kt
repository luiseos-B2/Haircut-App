package br.com.haircutapp.data

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class FirebaseAuthServiceTest {


    lateinit var firebaseAuth: FirebaseAuth

    @InjectMockKs
    lateinit var firebaseAuthService: FirebaseAuthService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockkStatic(FirebaseAuth::class)
        every { FirebaseAuth.getInstance() } returns mockk(relaxed = true)
        every { FirebaseAuth.getInstance(any())} returns mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        unmockkStatic(FirebaseAuth::class)
    }

    @Test
    fun loggedInUser() {
        // GIVEN
//        every { FirebaseAuth.getInstance().currentUser } returns null

        // WHEN
//        firebaseAuthService.loggedInUser()

        // THEN
//        assertEquals(false, firebaseAuthService.loggedInUser())
//        verify (exactly = 1) { firebaseAuthService.loggedInUser() }

    }

    @Test
    fun validateEmail() {
    }

    @Test
    fun isEmailVerified() {
    }

    @Test
    fun loginUser() {
    }

    @Test
    fun disconnectUser() {
    }

    @Test
    fun resetPassword() {
    }

    @Test
    fun createUser() {
    }
}