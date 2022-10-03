package com.example.stockholmspokertest05

class PlayingCardSlot (var imageFrame: Int) {

    val playingCardSlotEmpty = (R.drawable.playing_card_gallery_slot_empty)
    var playingCardSlotFull = imageFrame

    var positionX : Int? = null
    var positionY : Int? = null

    val rotationAngleInt = 0
    val rotationAngle = rotationAngleInt.toFloat()
    var slotTaken: Boolean = false

    fun assignImageView(){
        if (slotTaken == false){

        }
    }
}