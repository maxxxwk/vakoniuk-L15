package com.maxxxwk.texthometask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxxwk.texthometask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val namesListAdapter = NamesListAdapter()
    private val namesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRecyclerView()
        setupListeners()
    }

    private fun initRecyclerView() {
        binding.rvNamesList.layoutManager = LinearLayoutManager(this)
        binding.rvNamesList.adapter = namesListAdapter
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            val name = binding.etFullName.text.toString()
            if (checkValidity(name)) {
                namesList.add(name)
                namesListAdapter.submitList(namesList)
            }
        }
    }

    private fun checkValidity(name: String): Boolean {
        if (name.contains(Regex("[0-9]"))) {
            showErrorMessage("Name shouldn't contains digits!")
            return false
        }
        if (name.split(" ").size != 2) {
            showErrorMessage("You should enter first name and last name!")
            return false
        }
        val firstName = name.split(" ")[0]
        val lastName = name.split(" ")[1]
        if (!firstName.toCharArray()[0].isUpperCase()) {
            showErrorMessage("First name should start with upper case!")
            return false
        }
        if (!lastName.toCharArray()[0].isUpperCase()) {
            showErrorMessage("Last name should start with upper case!")
            return false
        }
        if (firstName.length <= 2) {
            showErrorMessage("First name should have more than 2 symbols")
            return false
        }
        if (lastName.length <= 2) {
            showErrorMessage("Last name should have more than 2 symbols")
            return false
        }
        return true
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}