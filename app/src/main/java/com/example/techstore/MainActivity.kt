package com.example.techstore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var addTechBtn: Button
    private lateinit var deleteTechBtn: Button
    private lateinit var refreshTechsBtn: Button
    private lateinit var nameInputField: EditText
    private lateinit var brandInputField: EditText
    private lateinit var sizeInputField: EditText
    private lateinit var priceInputField: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var techAdapter: TechAdapter
    private lateinit var techData: TechData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Управление системными краями экрана
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
        }

        setContentView(R.layout.activity_main)

        techData = TechData()

        addTechBtn = findViewById(R.id.addRowBtn)
        deleteTechBtn = findViewById(R.id.deleteRowBtn)
        refreshTechsBtn = findViewById(R.id.refreshRowsBtn)

        nameInputField = findViewById(R.id.name_inputField)
        brandInputField = findViewById(R.id.brand_inputField)
        sizeInputField = findViewById(R.id.size_inputField)
        priceInputField = findViewById(R.id.price_inputField)

        recyclerView = findViewById(R.id.techRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addTechBtn.setOnClickListener {
            val name = nameInputField.text.toString()
            val brand = brandInputField.text.toString()
            val sizeStr = sizeInputField.text.toString()
            val priceStr = priceInputField.text.toString()

            if (name.isEmpty() || brand.isEmpty() || sizeStr.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Все поля обязательны для заполнения", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val size = sizeStr.toDouble()
                val price = priceStr.toDouble()
                val newTech = Tech(name, brand, size, price)
                techData.addTech(newTech)
                clearInputFields()
                refreshTechs()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Неверный формат размера или цены", Toast.LENGTH_SHORT).show()
            }
        }

        refreshTechsBtn.setOnClickListener { refreshTechs() }

        deleteTechBtn.setOnClickListener {
            if (techAdapter != null && techAdapter.itemCount > 0) {
                val techToDelete = techAdapter.getTechAt(0)
                if (techToDelete != null) {
                    techData.deleteTech(techToDelete)
                }
                refreshTechs()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBarsInsets = Insets.of(insets.systemGestureInsets.left, insets.systemGestureInsets.top,
                insets.systemGestureInsets.right, insets.systemGestureInsets.bottom)
            v.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom)
            insets
        }
    }

    private fun clearInputFields() {
        nameInputField.setText("")
        brandInputField.setText("")
        sizeInputField.setText("")
        priceInputField.setText("")
    }

    private fun refreshTechs() {
        val techs = techData.getAllTechs()
        techAdapter = TechAdapter(techs)
        recyclerView.adapter = techAdapter
    }
}
