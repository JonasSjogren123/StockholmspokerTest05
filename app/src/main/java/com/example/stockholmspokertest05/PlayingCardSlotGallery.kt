package com.example.stockholmspokertest05

import android.widget.ImageView
import android.widget.LinearLayout

class PlayingCardSlotGallery {

    /*val playingCardSlotEmpty = (R.drawable.gallery_playing_card_slot_empty)
    var playingCardSlotFull = imageView
    var slotFull : Boolean = false

    var positionX : Int? = null
    var positionY : Int? = null

    val rotationAngleInt = 0
    val rotationAngle = rotationAngleInt.toFloat()
    var slotTaken: Boolean = false
    */

    val PlayingCardSlotsPositionY = 10
    val playingCardSlotA = PlayingCardSlot(R.drawable.playing_card_gallery_slot_empty)
    val playingCardSlotB = PlayingCardSlot(R.drawable.playing_card_gallery_slot_empty)
    val playingCardSlotC = PlayingCardSlot(R.drawable.playing_card_gallery_slot_empty)
    val playingCardSlotD = PlayingCardSlot(R.drawable.playing_card_gallery_slot_empty)
    val playingCardSlotE = PlayingCardSlot(R.drawable.playing_card_gallery_slot_empty)

    val listOfPlayingCardsInGallery : List<PlayingCardSlot> = listOf(playingCardSlotA, playingCardSlotB, playingCardSlotC, playingCardSlotD,playingCardSlotE)

    fun populateGalleryWithPlayingCardSlots() {
        playingCardSlotA.positionX = 10
        playingCardSlotB.positionX = 110
        playingCardSlotC.positionX = 210
        playingCardSlotD.positionX = 310
        playingCardSlotE.positionX = 410
        playingCardSlotA.positionY = PlayingCardSlotsPositionY
        playingCardSlotB.positionY = PlayingCardSlotsPositionY
        playingCardSlotC.positionY = PlayingCardSlotsPositionY
        playingCardSlotD.positionY = PlayingCardSlotsPositionY
        playingCardSlotE.positionY = PlayingCardSlotsPositionY
    }

}