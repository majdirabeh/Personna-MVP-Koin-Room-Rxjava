package fr.dev.majdi.personnamvproom.interactor

import fr.dev.majdi.personnamvproom.model.Personne

/**
 * Created by Majdi RABEH on 30/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class Interactor{
    interface MainInteractor {
        fun getPersonnes(listener: OnPersonnesFinishedListener)
        fun savePersonne(personne: Personne, listener: OnSavePersonneFinishedListener)
        fun deletePersonne(personne: Personne, listener: OnDeletePersonneFinishedListener)
    }

    interface OnPersonnesFinishedListener {
        fun onSuccess(personnes: MutableList<Personne>)
        fun onError()
    }

    interface OnSavePersonneFinishedListener {
        fun onSuccess(message: String)
        fun onError(error: String)
    }

    interface OnDeletePersonneFinishedListener {
        fun onSuccess(message: String)
        fun onError(error: String)
    }

}