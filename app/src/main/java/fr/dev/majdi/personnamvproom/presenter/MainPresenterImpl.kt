package fr.dev.majdi.personnamvproom.presenter

import fr.dev.majdi.personnamvproom.interactor.Interactor
import fr.dev.majdi.personnamvproom.model.Personne
import fr.dev.majdi.personnamvproom.view.MainView

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
open class MainPresenterImpl(var view: MainView?, private var mainInteractor: Interactor.MainInteractor?) : MainPresenter {

    override fun initRecycle() {
        view?.initRecycleView()
    }

    override fun loadPersonnes() {
        mainInteractor?.getPersonnes(object :
            Interactor.OnPersonnesFinishedListener {
            override fun onSuccess(personnes: MutableList<Personne>) {
                view?.setListPersonnes(personnes)
            }

            override fun onError() {
                view?.setErrorLoadPersonnes()
            }

        })
    }

    override fun savePersonne(personne: Personne) {
        mainInteractor?.savePersonne(
            personne,
            object :
                Interactor.OnSavePersonneFinishedListener {
                override fun onSuccess(message: String) {
                    view?.setSuccessSavePersonne(message)
                    loadPersonnes()
                }

                override fun onError(error: String) {
                    view?.setErrorSavePersonne(error)
                }

            })
    }

    override fun deletePersonne(personne: Personne) {
        mainInteractor?.deletePersonne(
            personne,
            object :
                Interactor.OnDeletePersonneFinishedListener {
                override fun onSuccess(message: String) {
                    view?.setSuccessDeletePersonne(message)
                    loadPersonnes()
                }

                override fun onError(error: String) {
                    view?.setErrorDeletePersonne(error)
                }

            })
    }

    override fun unSubscribe() {
        this.view = null
    }
}