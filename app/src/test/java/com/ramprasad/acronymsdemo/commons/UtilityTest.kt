package com.ramprasad.acronymsdemo.commons

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowNetworkCapabilities

/**
 * Created by Ramprasad on 2/1/23.
 */
@HiltAndroidTest
@Config(manifest = Config.NONE, sdk = [28])
@RunWith(RobolectricTestRunner::class)
class UtilityTest {

    @Test
    fun `checking the internet connection, and verifying if it returns false`() {
        val connectivityManager = ApplicationProvider.getApplicationContext<Context>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = ShadowNetworkCapabilities.newInstance()
        Shadows.shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        Shadows.shadowOf(connectivityManager)
            .setNetworkCapabilities(connectivityManager.activeNetwork, null)

        val result =
            Utility.isConnectedToNetwork(ApplicationProvider.getApplicationContext())
        MatcherAssert.assertThat(result, IsEqual(false))
    }

    @Test
    fun `checking the internet connection, and verifying if it returns true`() {
        val connectivityManager = ApplicationProvider.getApplicationContext<Context>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = ShadowNetworkCapabilities.newInstance()
        Shadows.shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        Shadows.shadowOf(connectivityManager)
            .setNetworkCapabilities(connectivityManager.activeNetwork, networkCapabilities)
        val result = Utility.isConnectedToNetwork(ApplicationProvider.getApplicationContext())

        MatcherAssert.assertThat(result, IsEqual(true))
    }
}
