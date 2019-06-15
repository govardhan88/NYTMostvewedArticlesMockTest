package com.govardhan.nytimesarticles.common.events

import android.os.Bundle
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

// Use object so we have a singleton instance
object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

    sealed class Event {
        data class FragmentSwitchEvent(val fragmentClass: Class<*>, val bundle: Bundle = Bundle()) : Event()
        data class ActivitySwitchEvent(val activityClass: Class<*>) : Event()
        data class KeyboardEvent(val isShowing: Boolean, val view: View) : Event()
    }

    fun switchFragment(fragmentClass: Class<*>, bundle: Bundle = Bundle()) {
        publish(RxBus.Event.FragmentSwitchEvent(fragmentClass, bundle))
    }

    fun switchActivity(fragmentClass: Class<*>, bundle: Bundle = Bundle()) {
        publish(RxBus.Event.ActivitySwitchEvent(fragmentClass))
    }

}