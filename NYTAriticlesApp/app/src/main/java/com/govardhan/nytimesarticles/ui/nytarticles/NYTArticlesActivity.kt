package com.govardhan.nytimesarticles.ui.nytarticles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.govardhan.nytimesarticles.R
import com.govardhan.nytimesarticles.common.EnvironmentState
import com.govardhan.nytimesarticles.common.events.RxBus
import com.govardhan.nytimesarticles.common.utils.ext.addFragmentInActivity
import com.govardhan.nytimesarticles.common.utils.ext.replaceFragmentInActivity
import com.govardhan.nytimesarticles.data.remote.NetworkingController
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_nyt_acrticles.*
import kotlinx.android.synthetic.main.fragment_nyt_articles_list.*
import javax.inject.Inject


class NYTArticlesActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    private val TAG = this::class.java.simpleName

    @Inject
    lateinit var state: EnvironmentState

    @Inject
    lateinit var networkingController: NetworkingController

    private val imm by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nyt_acrticles)
        setSupportActionBar(toolbar_activity)

        RxBus.listen(RxBus.Event::class.java)
            .subscribe(
                { handleRxBusEvent(it) },
                { Log.e(TAG, it.toString()) })

        routeStartFragment()
    }


    private fun handleRxBusEvent(event: RxBus.Event) {
        when (event) {
            is RxBus.Event.FragmentSwitchEvent -> handleFragmentSwitchEvent(event)
            is RxBus.Event.ActivitySwitchEvent -> handleActivitySwitchEvent(event)
            is RxBus.Event.KeyboardEvent -> handleKeyboardEvent(event.isShowing, event.view)
        }
    }

    private fun handleKeyboardEvent(isShowing: Boolean, view: View) {
        when (isShowing) {
            true -> showKeyboard(view)
            false -> hideKeyboard(view)
        }
    }

    private fun showKeyboard(view: View) {
        view.requestFocus()
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun hideKeyboard(view: View) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }


    private fun handleFragmentSwitchEvent(fragmentSwitchEvent: RxBus.Event.FragmentSwitchEvent) {
        switchFragment(fragmentSwitchEvent.fragmentClass, fragmentSwitchEvent.bundle)
    }

    private fun handleActivitySwitchEvent(fragmentSwitchEvent: RxBus.Event.ActivitySwitchEvent) {
        startActivity(Intent(baseContext, fragmentSwitchEvent.activityClass))
    }

    private fun switchFragment(fragmentClass: Class<*>, bundle: Bundle? = Bundle()) {
        val fragment = fragmentClass.newInstance() as Fragment
        fragment.arguments = bundle
        replaceFragmentInActivity(fragment, R.id.fragmentContainer)
    }

    private fun routeStartFragment() {
        addFragment(NYTArticlesListFragment::class.java)
    }

    private fun addFragment(fragmentClass: Class<*>) {
        addFragmentInActivity(fragmentClass.newInstance() as Fragment, R.id.fragmentContainer)
    }

    override fun onBackPressed() {

        val fragment = this.supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment is NYTArticlesListFragment) {
            finish()
        } else
            super.onBackPressed()
    }
}

