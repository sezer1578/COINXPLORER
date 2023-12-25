package com.ozaltun.coinxplorer.domain.usecase.firebase

import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val firebaseRepository: FireBaseRepository) {

    operator fun invoke(email: String, password: String) =
        firebaseRepository.signInWithEmailAndPassword(email, password)
}