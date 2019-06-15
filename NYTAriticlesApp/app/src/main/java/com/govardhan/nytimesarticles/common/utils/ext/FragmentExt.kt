package com.govardhan.nytimesarticles.common.utils.ext

import android.support.v4.app.Fragment
import com.govardhan.nytimesarticles.NYTimesArticlesApp

val Fragment.NYTimesArticlesApp: NYTimesArticlesApp get() = activity?.application as NYTimesArticlesApp
