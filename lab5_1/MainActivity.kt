package com.example.lab5_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    FormFragment.OnDataPassListener,
    ListFragment.OnDataPassListener,
    DetailFragment.OnDataPassListener
{
    private val _travel = TravelData.travels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        renderFormAndList()
    }

    override fun add(newValue: Travel) {
        _travel.add(newValue)
        renderFormAndList()
    }

    override fun openDetailFragment(travel: Travel) {
        val formFragment = DetailFragment.newInstance(travel)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.firstFragment, formFragment)
            .remove(supportFragmentManager.findFragmentById(R.id.secondFragment) ?: return)
            .addToBackStack(null)
            .commit()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun saveChanges(travel: Travel) {
        val index = _travel.indexOfFirst { it.id == travel.id }
        if (index != -1) {
            _travel[index] = travel
            renderFormAndList()
        }
    }

    override fun delete(travelId: String) {
        val travelToMove = _travel.find { it.id == travelId }
        if (travelToMove != null) {
            _travel.remove(travelToMove)
            renderFormAndList()
        }
    }

    private fun renderFormAndList() {
        val formFragment = FormFragment.newInstance()
        val listFragment = ListFragment.newInstance(_travel)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.firstFragment, formFragment)
            .replace(R.id.secondFragment, listFragment)
            .addToBackStack(null)
            .commit()
    }
}
