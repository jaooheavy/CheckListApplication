package jp.solucoes.checklistapplication.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject

class IonResponse internal constructor(
    var status: ResponseStatus,
    var resultObject: JsonObject?,
    var resultArrayObject: JsonArray?,
    var error: Throwable?
) {

    companion object {
        fun loading(): IonResponse {
            return IonResponse(ResponseStatus.LOADING, null, null, null)
        }

        fun success(resultObject: JsonObject?): IonResponse {
            return IonResponse(ResponseStatus.SUCCESS, resultObject, null, null)
        }

        fun success(resultArrayObject: JsonArray?): IonResponse {
            return IonResponse(ResponseStatus.SUCCESS, null, resultArrayObject, null)
        }

        fun error(error: Throwable?): IonResponse {
            return IonResponse(ResponseStatus.ERROR, null, null, error)
        }
    }
}