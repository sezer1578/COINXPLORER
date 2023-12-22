package com.ozaltun.coinxplorer.domain.usecase.firebase

import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val firebaseRepository: FireBaseRepository) {

    operator fun invoke() = firebaseRepository.signOut()
}