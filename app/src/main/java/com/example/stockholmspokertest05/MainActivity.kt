package com.example.stockholmspokertest05

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.view.DragEvent
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
//import com.raywenderlich.android.masky.databinding.ActivityMainBinding

//import com.example.stockholmspokertest04.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    val playingCardDeck = PlayingCardDeck()
    var listOfPlayingCardsRemainingInGame = playingCardDeck.listOfPlayingCardsRandom.toMutableList()
    val playingCardSlotGallery = PlayingCardSlotGallery()
    lateinit var layout: RelativeLayout

    var buttonIsAlreadyPressed: Boolean = false
    var playingCardFaceUp : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val galleryCardSlot = ImageView(this)
        galleryCardSlot.layoutParams = LinearLayout.LayoutParams(300, 300)
        galleryCardSlot.setImageResource(R.drawable.playing_card_gallery_slot_empty)

        layout = findViewById<RelativeLayout>(R.id.layout)
        playingCardDeck.shuffle()
        populateCourtWithPlayingCards()
        populateGalleryWithPlayingCardSlots()

        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            populateCourtWithPlayingCards()
            populateGalleryWithPlayingCardSlots()
            playingCardDeck.listOfPlayingCardsRandom.shuffle()
            layout.addView(button)
        }

    }

    fun flipCardSide() {
        var choice = (0..1).random()
        playingCardFaceUp = choice == 0
    }

    fun populateGalleryWithPlayingCardSlots(){
        var positionX = -11
        val positionY = 50

        for (playingCardSlot in playingCardSlotGallery.listOfPlayingCardsInGallery) {
            val imageView = ImageView(this)
            imageView.layoutParams = LinearLayout.LayoutParams(250, 250)
            imageView.setImageResource(playingCardSlot.imageFrame)
            imageView.x = positionX.toFloat()
            imageView.y = positionY.toFloat()
            layout?.addView(imageView)
            imageView.tag = playingCardSlot
            positionX += 213
        }
    }

    fun populateCourtWithPlayingCards() {
        newGameDecision()
        playingCardDeck.listOfPlayingCardsRemainingInGame = playingCardDeck.listOfPlayingCardsRandom.toMutableList()
        playingCardDeck.listOfPlayingCardsRandom.clear()
        if (buttonIsAlreadyPressed == true) {
            layout?.removeAllViewsInLayout()
            /*for (playingCard in playingCardDeck.listOfPlayingCardsRemainingInGame) {
                layout?.removeViewInLayout(playingCard)
            }*/
        }
        for (playingCard in playingCardDeck.listOfPlayingCardsRemainingInGame) {
            val imageView = ImageView(this)
            imageView.layoutParams = LinearLayout.LayoutParams(350, 350)
            flipCardSide()
            if (playingCardFaceUp == true) {
                imageView.setImageResource(playingCard.imageFace)
                playingCardDeck.listOfPlayingCardsRandom.add(playingCard)
            } else {
                imageView.setImageResource(playingCard.cardSideBack)
            }
            placeViewRandomly(imageView)
            rotateViewRandomly(imageView)
            layout?.addView(imageView)
            imageView.tag = playingCard
        }
        buttonIsAlreadyPressed = true
    }

    fun newGameDecision(){
        if (playingCardDeck.listOfPlayingCardsRemainingInGame.size == 0){
            playingCardDeck.listOfPlayingCardsRandom = playingCardDeck.listOfPlayingCards.toMutableList()
        }
    }


    fun placeViewRandomly(imageView: ImageView) {
        var randomIntX = (-50..800).random()
        imageView.x = randomIntX.toFloat()
        var randomIntY = (400..1500).random()
        imageView.y = randomIntY.toFloat()
    }

    fun rotateViewRandomly(imageView: ImageView) {
        var randomInt = (0..360).random()
        imageView.rotation = randomInt.toFloat()
    }
}