package com.example.chuckjokes.about

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.chuckjokes.R

class AboutDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        return builder.create()
    }
}