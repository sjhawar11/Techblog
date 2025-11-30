package com.sjhawar11.composetest

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

// A custom runner that sets up the Hilt Test Application
class CustomTestRunner : AndroidJUnitRunner() {

  override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
    // This is the magic line. It tells Android to use Hilt's special TestApplication
    return super.newApplication(cl, HiltTestApplication::class.java.name, context)
  }
}