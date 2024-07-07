package com.example.jogodavelha

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityRoboBinding
import kotlin.random.Random

class RoboActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoboBinding

    // Vetor bidimensional que representará o tabuleiro de jogo
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    // Qual jogador está jogando
    var jogadorAtual = "steve"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRoboBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // O botão levará o jogador para a tela inicial do programa
        binding.buttonVoltar.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }
    }

    // Função associada com todos os botões @param view é o botão clicado
    fun buttonClick(view: View) {
        // O botão clicado é associado com uma constante
        val buttonSelecionado = view as Button
        // O texto do botão recebe o jogador atual
        buttonSelecionado.setBackgroundResource(R.drawable.steve)

        // De acordo com o botão clicado, a posição da matriz receberá o Jogador
        when (buttonSelecionado.id) {
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

        // Desativa o botão após ser clicado
        buttonSelecionado.isEnabled = false

        // Verifica se há um vencedor após a jogada do jogador
        var vencedor = verificarVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_LONG).show()
            val intent = Intent(this, RoboActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Jogada do robô
        var rX: Int
        var rY: Int
        var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)

            if (tabuleiro[rX][rY] != "steve" && tabuleiro[rX][rY] != "creeper") {
                tabuleiro[rX][rY] = "creeper"

                val posicao = rX * 3 + rY
                when (posicao) {
                    0 -> binding.buttonZero.setBackgroundResource(R.drawable.crepe)
                    1 -> binding.buttonUm.setBackgroundResource(R.drawable.crepe)
                    2 -> binding.buttonDois.setBackgroundResource(R.drawable.crepe)
                    3 -> binding.buttonTres.setBackgroundResource(R.drawable.crepe)
                    4 -> binding.buttonQuatro.setBackgroundResource(R.drawable.crepe)
                    5 -> binding.buttonCinco.setBackgroundResource(R.drawable.crepe)
                    6 -> binding.buttonSeis.setBackgroundResource(R.drawable.crepe)
                    7 -> binding.buttonSete.setBackgroundResource(R.drawable.crepe)
                    8 -> binding.buttonOito.setBackgroundResource(R.drawable.crepe)
                }
                break
            }
            i++
        }

        // Verifica se há um vencedor após a jogada do robô
        vencedor = verificarVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_LONG).show()
            val intent = Intent(this, RoboActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun verificarVencedor(tabuleiro: Array<Array<String>>): String? {
        // Verifica linhas e colunas
        for (i in 0 until 3) {
            // Verifica se há três itens iguais na linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            // Verifica se há três itens iguais na coluna
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }

        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }

        // Verifica a quantidade de jogadas
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if (valor == "steve" || valor == "creeper") {
                    empate++
                }
            }
        }
        // Se existem 9 jogadas e não há três letras iguais, houve um empate
        if (empate == 9) {
            return "Empate"
        }
        // Nenhum vencedor
        return null
    }
}