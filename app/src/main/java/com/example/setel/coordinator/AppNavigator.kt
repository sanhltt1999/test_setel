package com.example.setel.coordinator

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.setel.R
import com.example.setel.ui.restaurantdetail.RestaurantDetailFragment
import java.lang.ref.WeakReference
import javax.inject.Inject

open class AppNavigator @Inject constructor(fragmentActivity: FragmentActivity) {
    private val activityReference = WeakReference(fragmentActivity)
    private val activityRef: FragmentActivity get() = activityReference.get()!!
    private val supportFragmentManager: FragmentManager get() = activityRef.supportFragmentManager

    fun showForgotPasswordFragment(bundle: Bundle) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, RestaurantDetailFragment().apply { arguments = bundle })
            .addToBackStack(RestaurantDetailFragment::class.java.name)
            .commit()
    }

}
