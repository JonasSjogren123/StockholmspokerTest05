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

//import com.example.stockholmspokertest05.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    private val maskDragMessage = "Mask Added"
    private val maskOn = "Bingo! Mask On"
    private val maskOff = "Mask off"

    //private lateinit var binding: ActivityMainBinding

    val playingCardDeck = PlayingCardDeck()
    var listOfPlayingCardsRemainingInGame = playingCardDeck.listOfPlayingCardsRandom.toMutableList()
    val playingCardSlotGallery = PlayingCardSlotGallery()
    lateinit var layout: RelativeLayout

    var buttonIsAlreadyPressed: Boolean = false
    var playingCardFaceUp : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Switch to AppTheme for displaying the activity
        //setTheme(R.style.AppTheme)

        //super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        //attachViewDragListener(binding.mask, R.drawable.ic_mask)
        //attachViewDragListener(binding.test01, R.drawable.test_01)
        //attachViewDragListenerTest01()

        //binding.maskDropArea.setOnDragListener(maskDragListener)

        /*
        // Creates a mask drag event listener
        private val maskDragListener = View.OnDragListener { view, dragEvent ->

            val draggableItem = dragEvent.localState as View

            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    // dims the view when the mask has entered the drop area
                    binding.maskDropArea.alpha = 0.3f
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    // reset opacity if the mask exits drop area without drop action
                    binding.maskDropArea.alpha = 1.0f
                    //when mask exits drop-area without dropping set mask visibility to VISIBLE
                    draggableItem.visibility = View.VISIBLE
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    // reset opacity if the mask is dropped
                    binding.maskDropArea.alpha = 1.0f

                    //on drop event in the target drop area, read the data and
                    // re-position the mask in it's new location
                    if (dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        val draggedData = dragEvent.clipData.getItemAt(0).text
                        println("draggedData $draggedData")
                    }

                    //re-position the mask in the updated x, y co-ordinates
                    // with mask center position pointing to new x,y co-ordinates
                    draggableItem.x = dragEvent.x - (draggableItem.width / 2)
                    draggableItem.y = dragEvent.y - (draggableItem.height / 2)

                    //on drop event remove the mask from parent viewGroup
                    val parent = draggableItem.parent as ConstraintLayout
                    parent.removeView(draggableItem)

                    //add the mask view to a new viewGroup where the mask was dropped
                    val dropArea = view as ConstraintLayout
                    dropArea.addView(draggableItem)

                    checkIfMaskIsOnFace(dragEvent)
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    draggableItem.visibility = View.VISIBLE
                    view.invalidate()
                    true
                }
                else -> {
                    false
                }

            }
        }*/

        /**
         * Method checks whether the mask is dropped on the face and
         * displays an appropriate Toast message
         *
         * @param dragEvent DragEvent
         */

        /*
        private fun checkIfMaskIsOnFace(dragEvent: DragEvent) {
            //x,y co-ordinates left-top point
            val faceXStart = binding.faceArea.x
            val faceYStart = binding.faceArea.y

            //x,y co-ordinates bottom-end point
            val faceXEnd = faceXStart + binding.faceArea.width
            val faceYEnd = faceYStart + binding.faceArea.height

            val toastMsg = if (dragEvent.x in faceXStart..faceXEnd && dragEvent.y in faceYStart..faceYEnd){
                maskOn
            } else {
                maskOff
            }

            Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show()

        }*/

/*
        private fun attachViewDragListener(imageView : ImageView, image: Int) {

            imageView.setOnLongClickListener { view: View ->

                // Create a new ClipData.Item with custom text data
                val item = ClipData.Item(maskDragMessage)

                // Create a new ClipData using a predefined label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                val dataToDrag = ClipData(
                    maskDragMessage,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    item
                )

                // Instantiates the drag shadow builder.
                val maskShadow = GeneralDragShadowBuilder(view, image)

                // Starts the drag
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    //support pre-Nougat versions
                    @Suppress("DEPRECATION")
                    view.startDrag(dataToDrag, maskShadow, view, 0)
                } else {
                    //supports Nougat and beyond
                    view.startDragAndDrop(dataToDrag, maskShadow, view, 0)
                }

                view.visibility = View.INVISIBLE
                true
            }

        }*/

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