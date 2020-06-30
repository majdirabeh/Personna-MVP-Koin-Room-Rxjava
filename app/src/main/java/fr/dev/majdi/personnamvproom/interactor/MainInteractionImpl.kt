package fr.dev.majdi.personnamvproom.interactor

import android.annotation.SuppressLint
import fr.dev.majdi.personnamvproom.model.Personne
import fr.dev.majdi.personnamvproom.room.PersonneDAO
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@SuppressLint("CheckResult")
class MainInteractionImpl(private var personneDAO: PersonneDAO) :
    Interactor.MainInteractor {

    override fun getPersonnes(listener: Interactor.OnPersonnesFinishedListener) {
        personneDAO.getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    listener.onSuccess(it)
                }
            }, {
                listener.onError()
            })
    }

    override fun savePersonne(
        personne: Personne,
        listener: Interactor.OnSavePersonneFinishedListener
    ) {

        Observable.fromCallable { personneDAO.insert(personne) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.onSuccess("Personne saved!")
            }, {
                listener.onError("Opps, Error !")
            })
    }

    override fun deletePersonne(
        personne: Personne,
        listener: Interactor.OnDeletePersonneFinishedListener
    ) {
        Observable.fromCallable { personneDAO.deleteById(personne.id!!) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.onSuccess("Personne deleted!")
            }, {
                listener.onError("Opps, Error !")
            })
    }
}