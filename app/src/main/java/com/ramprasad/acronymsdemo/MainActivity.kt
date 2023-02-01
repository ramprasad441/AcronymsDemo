package com.ramprasad.acronymsdemo

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.ramprasad.acronymsdemo.adapter.AcronymAdapter
import com.ramprasad.acronymsdemo.commons.Utility
import com.ramprasad.acronymsdemo.databinding.ActivityMainBinding
import com.ramprasad.acronymsdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var acronymListAdapter: AcronymAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.acronymList.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        binding.search.setOnClickListener {
            searchForAcronyms()
        }
        binding.acronymInput.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchForAcronyms()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun searchForAcronyms() {
        val acronymUserInput = binding.acronymInput.text.toString()
        if (acronymUserInput.isEmpty()) return Toast.makeText(
            this,
            getString(R.string.please_enter_an_acronym),
            Toast.LENGTH_SHORT
        ).show()
        if (!Utility.isConnectedToNetwork(applicationContext)) return Toast.makeText(
            this,
            getString(R.string.no_network_connection),
            Toast.LENGTH_SHORT
        ).show()
        mainViewModel.fetchAcronymsResponse(acronymUserInput).observe(
            this@MainActivity,
            Observer { acronymResponse ->
                if (acronymResponse == null) {
                    return@Observer
                }
                if (acronymResponse.isEmpty()) {
                    Toast.makeText(this, getString(R.string.no_acronym_found), Toast.LENGTH_SHORT)
                        .show()
                    return@Observer
                }
                acronymListAdapter = AcronymAdapter(acronymResponse[0].longForms)
                binding.acronymList.adapter = acronymListAdapter
            }
        )
        /** For Dismissing the Keyboard after searching an acronym **/
        try {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            Toast.makeText(this, "Keyboard Dismissed", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
