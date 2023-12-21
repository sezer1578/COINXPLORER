package com.ozaltun.coinxplorer.domain.usecase

import com.ozaltun.coinxplorer.domain.model.CoinSearch
import com.ozaltun.coinxplorer.domain.repository.CoinRepository
import com.ozaltun.coinxplorer.util.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(query:String): Flow<Resource<List<CoinSearch>>>{
        return repository.coinSearch(query = query)
    }
}