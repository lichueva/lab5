package com.example.lab5_1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab5_1.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var _binding: FragmentDetailBinding
    private var _listener: OnDataPassListener? = null
    private lateinit var _travel: Travel

    interface OnDataPassListener {
        fun back()
        fun saveChanges(travel: Travel)
        fun delete(recipeId: String)
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

        arguments?.let {
            _travel = it.getParcelable(ARG_TRAVEL) ?: Travel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        val view = _binding.root

        _binding.titleInput.setText(_travel.place)
        _binding.dateTravel.setText(_travel.dateTravel)
        _binding.interestingPlaces.setText(_travel.interectingPlaces.joinToString(separator = ","))
        _binding.expirience.setText(_travel.expirience.joinToString(separator = ","))

        _binding.backButton.setOnClickListener {
            _listener?.back()
        }
        _binding.saveChangesButton.setOnClickListener {
            val travel = Travel(
                id = _travel.id,
                place = _binding.titleInput.text.toString(),
                dateTravel = _binding.dateTravel.text.toString(),
                interectingPlaces = _binding.interestingPlaces.text.split(","),
                expirience = _binding.expirience.text.split("."),
            )

            _listener?.saveChanges(travel)
        }
        _binding.deleteButton.setOnClickListener {
            _listener?.delete(_travel.id)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        private const val ARG_TRAVEL = "travel"

        fun newInstance(travel: Travel): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_TRAVEL, travel)
            fragment.arguments = args
            return fragment
        }
    }
}
