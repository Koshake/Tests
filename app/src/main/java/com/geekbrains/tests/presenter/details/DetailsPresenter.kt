package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private var count: Int = 0
) : PresenterDetailsContract {

    var viewContract: ViewDetailsContract? = null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContract?.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract?.setCount(count)
    }

    override fun onAttach(view: ViewDetailsContract) {
        if (view != viewContract) {
            viewContract = view
        }
    }

    override fun onDetach(view: ViewDetailsContract) {
        if (viewContract == view) {
            viewContract = null
        }
    }
}
