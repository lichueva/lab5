package com.example.lab5_1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab5_1.databinding.FragmentFormBinding
import java.util.UUID

class FormFragment : Fragment() {
    private lateinit var _binding: FragmentFormBinding
    private var _listener: OnDataPassListener? = null

    interface OnDataPassListener {
        fun add(newValue: Travel)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataPassListener) {
            _listener = context
        } else {
            throw RuntimeException("$context must implement OnDataPassListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormBinding.inflate(inflater)
        val view = _binding.root

        _binding.addRecipeButton.setOnClickListener {
            val travel = Travel(
                id = UUID.randomUUID().toString(),
                place = _binding.titleInput.text.toString(),
                dateTravel = _binding.dateTravel.text.toString(),
                interectingPlaces = _binding.interestingPlaces.text.split(","),
                expirience = _binding.expirience.text.split("."),
            )

            _listener?.add(travel)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        fun newInstance(): FormFragment {
            return FormFragment()
        }
    }
}
