package fr.dev.majdi.personnamvproom.base

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
interface BaseView<out T : BasePresenter<*>>  {
    val presenter: T
}