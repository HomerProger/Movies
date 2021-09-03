package com.example.movies.view.fragments

import android.content.Context.*
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movies.R
import com.example.movies.databinding.SettingsFragmentBinding
import com.example.movies.model.ADULT

class SettingsFragment : Fragment() {

    var _binding: SettingsFragmentBinding? = null
    val binding: SettingsFragmentBinding
        get() :SettingsFragmentBinding = _binding!!

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = context?.getSharedPreferences(ADULT, MODE_PRIVATE)!!
        binding.reminder.isChecked = loadPreferences(sharedPref)
        binding.reminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                savePreferences(sharedPref, true)
            } else {
                savePreferences(sharedPref, false)
            }
            setValueTextView(isChecked)
        }
    }

    private fun loadPreferences(sharedPref: SharedPreferences): Boolean {
        setValueTextView(sharedPref.getBoolean(ADULT, true))
        return sharedPref.getBoolean(ADULT, true)
    }

    private fun savePreferences(sharedPref: SharedPreferences, adult: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean(ADULT, adult)
        editor.commit()
    }

    private fun setValueTextView(adultContent: Boolean) {
        if (adultContent) {
            binding.textViewAdult.text = context?.resources!!.getString(R.string.on_adult_content)
        } else {
            binding.textViewAdult.text = context?.resources!!.getString(R.string.adult_content)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}