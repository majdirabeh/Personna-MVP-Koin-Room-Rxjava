package fr.dev.majdi.personnamvproom.module

import androidx.room.Room
import fr.dev.majdi.personnamvproom.interactor.Interactor
import fr.dev.majdi.personnamvproom.interactor.MainInteractionImpl
import fr.dev.majdi.personnamvproom.presenter.MainPresenter
import fr.dev.majdi.personnamvproom.presenter.MainPresenterImpl
import fr.dev.majdi.personnamvproom.room.AppDatabase
import fr.dev.majdi.personnamvproom.view.MainView
import org.koin.dsl.module


/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */

val databaseModule = module {
    //Init Room
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "personne-db")
            .build()
    }
    //PersonneDAO
    single { get<AppDatabase>().personneDao() }
}

val interactionModule = module {
    factory<Interactor.MainInteractor> {
        MainInteractionImpl(
            get()
        )
    }
}

val presenterModule = module {
    factory<MainPresenter> { (view: MainView) ->
        MainPresenterImpl(
            view,
            get()
        )
    }
}

