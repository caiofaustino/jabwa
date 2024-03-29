package dev.caiofaustino.jabwa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.caiofaustino.jabwa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateAddressButton.setOnClickListener {
            binding.addressInputField.setText("Sorry, this is still WIP.")
        }
    }
}
