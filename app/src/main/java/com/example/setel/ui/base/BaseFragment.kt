package com.example.setel.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.setel.ui.dialog.ErrorDialog
import com.example.setel.ui.dialog.LoadingProgress

abstract class BaseFragment<N : BaseViewModel> : Fragment() {

    abstract fun viewModel(): N

    @LayoutRes
    abstract fun getLayoutId(): Int

    private val progress: LoadingProgress by lazy {
        LoadingProgress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel().error.observe(viewLifecycleOwner) {
            ErrorDialog().show(childFragmentManager, ErrorDialog::class.simpleName)
        }

        viewModel().dataLoading.observe(viewLifecycleOwner) {
            if (it) progress.showLoadingProgress(this) else progress.hideLoadingProgress()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

}