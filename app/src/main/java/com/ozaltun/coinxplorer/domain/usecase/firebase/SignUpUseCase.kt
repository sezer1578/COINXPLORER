package com.ozaltun.coinxplorer.domain.usecase.firebase

import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val fireBaseRepository: FireBaseRepository
) {
    operator fun invoke(email: String, password: String) =
        fireBaseRepository.signUpWithEmailAndPassword(email, password)
}