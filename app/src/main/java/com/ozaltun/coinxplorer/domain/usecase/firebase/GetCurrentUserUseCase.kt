package com.ozaltun.coinxplorer.domain.usecase.firebase

import com.google.firebase.auth.FirebaseUser
import com.ozaltun.coinxplorer.domain.repository.FireBaseRepository
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val fireBaseRepository: FireBaseRepository
) {
    operator fun invoke():Flow<Resource<FirebaseUser>> {
        return fireBaseRepository.getCurrentUser()
    }
}