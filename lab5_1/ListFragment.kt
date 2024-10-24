package com.example.lab5_1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5_1.adapters.TravelAdapter
import com.example.lab5_1.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var _binding: FragmentListBinding
    private var _listener: OnDataPassListener? = null
    private lateinit var _travels: ArrayList<Travel>

    interface OnDataPassListener {
        fun openDetailFragment(travel: Travel)
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
            _travels = it.getParcelableArrayList<Travel>(ARG_TRAVEL) ?: ArrayList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)
        val view = _binding.root

        val recyclerView: RecyclerView = _binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TravelAdapter(_travels) { recipe ->
            _listener?.openDetailFragment(recipe)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        private const val ARG_TRAVEL = "travels"

        fun newInstance(stringList: ArrayList<Travel>): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_TRAVEL, stringList)
            fragment.arguments = args
            return fragment
        }
    }
}
