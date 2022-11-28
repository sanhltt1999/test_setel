package com.example.setel.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.setel.R

class LoadingProgress : DialogFragment() {
    private class CustomProgressDialog(context: Context) : AlertDialog(context) {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.progress_dialog)
            this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.ProgressDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return CustomProgressDialog(requireContext())
    }

    fun showLoadingProgress(activity: AppCompatActivity?) {
        activity?.run {
            showLoadingProgress(supportFragmentManager)
        }
    }


    private fun showLoadingProgress(fragmentManager: FragmentManager) {
        if (isAdded) return
        show(fragmentManager, this::class.java.name)
        fragmentManager.executePendingTransactions()
    }

    fun hideLoadingProgress() {
        if (isAdded) dismissAllowingStateLoss()
    }
}
