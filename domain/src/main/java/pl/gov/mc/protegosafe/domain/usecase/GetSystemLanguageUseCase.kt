package pl.gov.mc.protegosafe.domain.usecase

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pl.gov.mc.protegosafe.domain.executor.PostExecutionThread
import pl.gov.mc.protegosafe.domain.model.OutgoingBridgeDataResultComposer
import pl.gov.mc.protegosafe.domain.repository.AppRepository

class GetSystemLanguageUseCase(
    private val appRepository: AppRepository,
    private val resultComposer: OutgoingBridgeDataResultComposer,
    private val postExecutionThread: PostExecutionThread
) {
    fun execute(): Single<String> {
        return appRepository.getSystemLanguage()
            .flatMap { getResult(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
    }

    private fun getResult(languageISO: String): Single<String> {
        return Single.fromCallable { resultComposer.composeSystemLanguageResult(languageISO) }
    }
}
