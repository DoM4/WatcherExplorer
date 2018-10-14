package com.domenicoaumenta.puc.ui


/**
 * Created by domenicoaumenta on 06/10/2018.
 */
class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View
}
