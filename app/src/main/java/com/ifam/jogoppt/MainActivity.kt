package com.ifam.jogoppt

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {

   private lateinit var jogador1 : ImageView
   private lateinit var jogador2 : ImageView
   private lateinit var botaoPedra : ImageButton
   private lateinit var botaoPapel : ImageButton
   private lateinit var botaoTesoura : ImageButton
   private lateinit var botaoSpock : ImageButton
   private lateinit var botaoLagarto : ImageButton

   private var jogada1 = 0
   private var jogada2 = 0

   private lateinit var some: AlphaAnimation
   private lateinit var aparece: AlphaAnimation

   private var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jogador1 = findViewById(R.id.jogador1)
        jogador2 = findViewById(R.id.jogador2)
        botaoPedra = findViewById(R.id.botaoPedra)
        botaoPapel = findViewById(R.id.botaoPapel)
        botaoTesoura = findViewById(R.id.botaoTesoura)
        botaoSpock = findViewById(R.id.botaoSpock)
        botaoLagarto = findViewById(R.id.botaoLagarto)

        some = AlphaAnimation(1f,0f)
        aparece = AlphaAnimation(0f, 1f)

        some.duration = 1000
        aparece.duration = 250

        some.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                jogador2.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                jogador2.visibility = View.INVISIBLE
                jogador2.startAnimation(aparece)
            }

            override fun onAnimationRepeat(animation: Animation?){

            }
        })

        aparece.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                sorteiaJogadaInimigo()
                jogador2.visibility = View.INVISIBLE

            }

            override fun onAnimationEnd(animation: Animation?) {
                verificaJogada()
                jogador2.visibility = View.VISIBLE

            }

            override fun onAnimationRepeat(animation: Animation?){

            }
        })
    }

    fun tocouBotao(view: View){
        tocaSom()
        jogador1.scaleX = -1f

        when (view.id){
            R.id.botaoPedra -> {
                jogador1.setImageResource(R.drawable.pedra)
                jogada1 = 1
            }
            R.id.botaoPapel -> {
                jogador1.setImageResource(R.drawable.papel)
                jogada1 = 2
            }
            R.id.botaoTesoura -> {
                jogador1.setImageResource(R.drawable.tesoura)
                jogada1 = 3
            }
            R.id.botaoSpock -> {
                jogador1.setImageResource(R.drawable.spock)
                jogada1 = 4
            }
            R.id.botaoLagarto -> {
                jogador1.setImageResource(R.drawable.lagarto)
                jogada1 = 5
            }
        }
        jogador2.startAnimation(some)
        jogador2.setImageResource(R.drawable.interrogacao)
    }

    fun sorteiaJogadaInimigo() {
        val r = Random()
        //val numRandom = r.nextInt(3)
        when(r.nextInt(5)){
            0->{
                jogador2.setImageResource(R.drawable.pedra)
                jogada2 = 1
            }
            1->{
                jogador2.setImageResource(R.drawable.papel)
                jogada2 = 2
            }
            2->{
                jogador2.setImageResource(R.drawable.tesoura)
                jogada2 = 3
            }
            3->{
                jogador2.setImageResource(R.drawable.spock)
                jogada2 = 4
            }
            4->{
                jogador2.setImageResource(R.drawable.lagarto)
                jogada2 = 5
            }
        }
    }

    fun verificaJogada() {
        when {
            jogada1 == jogada2 -> Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show()
            jogada1 == 1 && jogada2 == 3 || jogada1 == 2 && jogada2 == 1 || jogada1 == 3 && jogada2 == 2 || jogada1 == 1 && jogada2 == 5 || jogada1 == 5 && jogada2 == 4
                    || jogada1 == 4 && jogada2 == 3 || jogada1 == 3 && jogada2 == 5 || jogada1 == 5 && jogada2 == 2 || jogada1 == 2 && jogada2 == 4 || jogada1 == 4 && jogada2 == 1 -> Toast.makeText(
                this,
                "Ganhei!",
                Toast.LENGTH_SHORT
            ).show()

            jogada2 == 1 && jogada1 == 3 || jogada2 == 2 && jogada1 == 1 || jogada2 == 3 && jogada1 == 2 || jogada2 == 1 && jogada1 == 5 || jogada2 == 5 && jogada1 == 4
                    || jogada2 == 4 && jogada1 == 3 || jogada2 == 3 && jogada1 == 5 || jogada2 == 5 && jogada1 == 2 || jogada2 == 2 && jogada1 == 4 || jogada2 == 4 && jogada1 == 1 -> Toast.makeText(
                this,
                "Perdi!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun tocaSom() {
        mediaPlayer?.start()
    }
}