package dev.caiofaustino.jabwa

import android.app.Application
import android.util.Log
import dev.caiofaustino.jabwa.encryption.PRNGFixes

class Application : Application() {

    companion object {
        init {
            PRNGFixes.apply()
        }
    }
}