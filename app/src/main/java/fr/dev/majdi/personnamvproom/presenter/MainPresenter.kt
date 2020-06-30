package fr.dev.majdi.personnamvproom.presenter

import fr.dev.majdi.personnamvproom.base.BasePresenter
import fr.dev.majdi.personnamvproom.model.Personne
import fr.dev.majdi.personnamvproom.view.MainView

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
interface MainPresenter : BasePresenter<MainView> {
    fun initRecycle()
    fun loadPersonnes()
    fun savePersonne(personne: Personne)
    fun deletePersonne(personne: Personne)
}