package com.example.jogodavelha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityInicioBinding

class InicioActivity: AppCompatActivity() {

    private lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Aqui esta sendo inflando o layout da tela principal.
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root) //App ira usar esse layout na tela


        //Configuração do botão que aparecerá no layout.
        binding.button1v1Local.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            //Início da proxima atividade.
            startActivity(intent)

        }

        binding.buttonMaquina.setOnClickListener {

            val intent = Intent(this, RoboActivity::class.java)
            //Início da proxima atividade.
            startActivity(intent)

        }


    }
}
