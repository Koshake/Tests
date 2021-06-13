package com.geekbrains.tests

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewDetailsContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter()
        presenter.onAttach(viewDetailsContract)
    }

    @Test
    fun onIncrement_Test() {
        presenter.onIncrement()
        Mockito.verify(viewDetailsContract, Mockito.times(1))
            .setCount(Mockito.anyInt())
    }

    @Test
    fun onDecrement_Test() {
        presenter.onDecrement()
        Mockito.verify(viewDetailsContract, Mockito.times(1))
            .setCount(Mockito.anyInt())
    }

    @Test
    fun onDetach_test() {
        presenter.onDetach(viewDetailsContract)
        Assert.assertNull(presenter.viewContract)
    }

    @Test
    fun onAttach_test() {
        Assert.assertNotNull(presenter.viewContract)
    }

    @Test
    fun onDetach_OnAttach() {
        presenter.onDetach(viewDetailsContract)
        presenter.onAttach(viewDetailsContract)
        Assert.assertNotNull(presenter.viewContract)
    }
}