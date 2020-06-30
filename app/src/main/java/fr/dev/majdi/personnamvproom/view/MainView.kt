package fr.dev.majdi.personnamvproom.view

import fr.dev.majdi.personnamvproom.base.BaseView
import fr.dev.majdi.personnamvproom.model.Personne
import fr.dev.majdi.personnamvproom.presenter.MainPresenter

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
interface MainView : BaseView<MainPresenter> {
    fun initRecycleView()
    fun setListPersonnes(personnes: MutableList<Personne>)
    fun setErrorLoadPersonnes()
    fun setErrorSavePersonne(error: String)
    fun setSuccessSavePersonne(msg: String)

    fun setErrorDeletePersonne(error: String)
    fun setSuccessDeletePersonne(msg: String)
}