package com.zainco.dtag

import android.app.Application
import com.zainco.dtag.di.authModule
import com.zainco.dtag.di.firebaseModule
import com.zainco.dtag.di.notesModule
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext


fun start(application: Application) {
    val koin = StandAloneContext.startKoin(
        listOf(
            firebaseModule,
            authModule,
            notesModule
        )
    ) with application
}