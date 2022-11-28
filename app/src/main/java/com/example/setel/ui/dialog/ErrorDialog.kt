package com.example.setel.ui.dialog

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.setel.R
import com.example.setel.databinding.LayoutErrorDialogBinding
import com.wada811.databinding.dataBinding

class ErrorDialog : DialogFragment() {
  private val binding by dataBinding<LayoutErrorDialogBinding>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnSkip.setOnClickListener {
      dismiss()
    }
  }
  override fun onResume() {
    super.onResume()
    val displayMetric = Resources.getSystem().displayMetrics
    val rect = displayMetric.run { Rect(0, 0, widthPixels, heightPixels) }
    dialog?.window?.setLayout((rect.width() * 0.9F).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.layout_error_dialog, container, false)
  }
}
