package com.example.jogodavelha

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    //Vetor bidimensional que representará o tabuleiro de jogo
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    //Qual o Jogador está jogando
    var jogadorAtual = "steve"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    //Função associada com todos os botões @param view é o botão clicado
    fun buttonClick(view: View) {
        //O botão clicado é associado com uma constante
        val buttonSelecionado = view as Button
        //O texto do botão recebe o jogador atual
       // buttonSelecionado.text = jogadorAtual

        //De acordo com o botão clicado, a posição da matriz receberá o Jogador
        when(buttonSelecionado.id){
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }

        var vencedor = verificarVencedor(tabuleiro)

        if(!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(jogadorAtual.equals("steve")) {
            buttonSelecionado.setBackgroundResource(R.drawable.steve)
            jogadorAtual = "crepper"
        }else{
            buttonSelecionado.setBackgroundResource(R.drawable.crepe)
            jogadorAtual = "steve"
        }
        buttonSelecionado.isEnabled=false
    }

    fun verificarVencedor(tabuleiro: Array<Array<String>>): String? {
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]){
                return tabuleiro[i][0]
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]){
                return tabuleiro[0][i]
            }
        }
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]){
            return tabuleiro[0][0]
        }
        if (tabuleiro [0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]){
            return tabuleiro[0][2]
        }

        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if (valor.equals("steve") || valor.equals("crepper")) {
                    empate++
                }
            }
        }

        if(empate == 9){
            return "Empate"
        }
        return null
    }

}
