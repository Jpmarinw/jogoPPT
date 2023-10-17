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
import androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var jogador1 : ImageView
    lateinit var jogador2 : ImageView
    lateinit var botaoPedra : ImageButton
    lateinit var botaoPapel : ImageButton
    lateinit var botaoTesoura : ImageButton

    var jogada1 = 0
    var jogada2 = 0

    lateinit var some: AlphaAnimation
    lateinit var aparece: AlphaAnimation

    var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jogador1 = findViewById<ImageView>(R.id.jogador1)
        jogador2 = findViewById<ImageView>(R.id.jogador2)
        botaoPedra = findViewById<ImageButton>(R.id.botaoPedra)
        botaoPapel = findViewById<ImageButton>(R.id.botaoPapel)
        botaoTesoura = findViewById<ImageButton>(R.id.botaoTesoura)
        some = AlphaAnimation(1f,0f)
        aparece = AlphaAnimation(0f, 1f)

        some.duration = 1500
        aparece.duration = 250

        some.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                jogador1.visibility = View.VISIBLE

            }

            override fun onAnimationEnd(animation: Animation?) {
                jogador1.visibility = View.INVISIBLE
                jogador1.startAnimation((aparece))
            }

            override fun onAnimationRepeat(animation: Animation?){}
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

            override fun onAnimationRepeat(animation: Animation?){}
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
        }
        jogador2.startAnimation(some)
        jogador2.setImageResource(R.drawable.interrogacao)
    }

    fun tocaSom() {
        mediaPlayer?.start()
    }

    fun verificaJogada() {
        when{
            jogada1 == jogada2 -> Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show()
            jogada1 == 1 && jogada2 == 3 || jogada1 == 2 && jogada2 == 1 || jogada1 == 3 && jogada2 == 2 -> Toast.makeText(this, "Ganhei!", Toast.LENGTH_SHORT).show()
            jogada2 == 1 && jogada1 == 3 || jogada2 == 2 && jogada1 == 1 || jogada2 == 3 && jogada1 == 2 -> Toast.makeText(this, "Perdeu!", Toast.LENGTH_SHORT).show()
        }

    }

    fun sorteiaJogadaInimigo() {
        val r = Random()
        val numRandom = r.nextInt(3)
        when(numRandom){
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
        }
    }


}