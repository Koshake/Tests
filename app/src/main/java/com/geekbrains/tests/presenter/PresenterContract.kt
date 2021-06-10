package com.geekbrains.tests.presenter

import com.geekbrains.tests.view.details.ViewDetailsContract

internal interface PresenterContract<V> {
    fun onAttach(view: V)
    fun onDetach(view: V)
}
