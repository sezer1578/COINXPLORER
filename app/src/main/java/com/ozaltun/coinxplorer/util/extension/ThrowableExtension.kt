package com.ozaltun.coinxplorer.util.extension

import com.ozaltun.coinxplorer.util.constant.Constant
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun Throwable.toAppException(): AppException {
    return when (this) {
        is HttpException -> {
            when (this.code()) {
                in 400..499 -> {
                    if (this.code() == 429){
                        ManyRequestException(
                            message = "${Constant.MANY_REQUEST_ERROR}: ${this.code()}",
                            cause = this,
                        )
                    }else{
                        ClientException(
                            message = "${Constant.CLIENT_ERROR}: ${this.code()}",
                            cause = this,
                        )
                    }
                }

                in 500..599 -> {
                    ServerException(
                        message = "${Constant.SERVER_ERROR}: ${this.code()}",
                        cause = this
                    )
                }

                else -> {
                    UnknownException(
                        message = "${Constant.HTTP_UNKNOWN_ERROR}: ${this.code()}",
                        cause = this
                    )
                }
            }
        }
        is SocketTimeoutException -> NetworkException(
            message = Constant.SOCKET_TIMEOUT_ERROR,
            cause = this
        )

        is IOException -> NetworkException(
            message = Constant.NETWORK_ERROR,
            cause = this
        )

        else -> AppException(
            message = Constant.UNKNOWN_ERROR,
            cause = this
        )
    }
}
fun Throwable.getErrorCode(): String {
    return when (this) {
        is HttpException -> {
            when (this.code()) {
                in 400..499 -> {
                    "#ER${this.code()}"
                }

                in 500..599 -> {
                    "#ER${this.code()}"
                }

                else -> {
                    "#ER${this.code()}"
                }
            }
        }

        else -> {
            this.cause?.message.toString()
        }
    }
}