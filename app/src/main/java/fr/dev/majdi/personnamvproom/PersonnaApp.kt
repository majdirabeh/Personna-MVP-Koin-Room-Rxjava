package fr.dev.majdi.personnamvproom

import android.app.Application
import fr.dev.majdi.personnamvproom.module.databaseModule
import fr.dev.majdi.personnamvproom.module.interactionModule
import fr.dev.majdi.personnamvproom.module.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module


/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class PersonnaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //Init Koin
        startKoin {
            androidLogger()
            androidContext(this@PersonnaApp)
            modules(listOf(presenterModule, databaseModule, interactionModule))
        }

    }

}