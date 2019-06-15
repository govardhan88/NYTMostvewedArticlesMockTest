package com.govardhan.nytimesarticles.common.utils.ext

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.govardhan.nytimesarticles.NYTimesArticlesApp

/**
 * Runs a FragmentTransaction, replaces current fragment.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int, bundle: Bundle? = null) {
    bundle?.let {
        fragment.arguments = it
    }
    supportFragmentManager.transact {
        replace(frameId, fragment)
        addToBackStack(fragment::class.java.name)
    }

}

fun AppCompatActivity.addFragmentInActivity(fragment: Fragment, frameId: Int, bundle: Bundle? = null) {
    bundle?.let {
        fragment.arguments = it
    }
    supportFragmentManager.transact {
        add(frameId, fragment)
        addToBackStack(fragment::class.java.name)
    }
}


/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

val AppCompatActivity.NYTimesArticlesApp: NYTimesArticlesApp get() = application as NYTimesArticlesApp