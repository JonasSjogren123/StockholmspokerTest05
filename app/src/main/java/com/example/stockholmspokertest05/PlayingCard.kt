package com.example.stockholmspokertest05

import android.widget.ImageView

class PlayingCard(var imageFace: Int, val suit: PlayingCardSuits, val rank: PlayingCardRanks) {

    val cardSideBack = (R.drawable.playing_card_backside)
    var positionX : Int? = null
    var positionY : Int? = null

    var rotationAngleInt = 0
    var rotationAngle = rotationAngleInt.toFloat()
}