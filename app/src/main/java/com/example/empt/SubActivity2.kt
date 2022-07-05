package com.example.empt

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.*
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.GenericTransitionOptions.with
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.with
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.with
import kotlinx.android.synthetic.main.activity_button2.*
import kotlinx.android.synthetic.main.camera_layout.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class SubActivity2 : AppCompatActivity() {

    private var currentAnimator: Animator? = null

    private var shortAnimationDuration: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button2)

        val thumb1View : View =  findViewById(R.id.thumb_button1)
        thumb1View.setOnClickListener({
            zoomImageFromThumb(thumb1View,R.drawable.cityscape1)
        })
        val thumb2View : View =  findViewById(R.id.thumb_button2)
        thumb2View.setOnClickListener({
            zoomImageFromThumb(thumb2View,R.drawable.cityscape2)
        })
        val thumb3View : View =  findViewById(R.id.thumb_button3)
        thumb3View.setOnClickListener({
            zoomImageFromThumb(thumb3View,R.drawable.woods1)
        })
        val thumb4View : View =  findViewById(R.id.thumb_button4)
        thumb4View.setOnClickListener({
            zoomImageFromThumb(thumb4View,R.drawable.woods2)
        })
        val thumb5View : View =  findViewById(R.id.thumb_button5)
        thumb5View.setOnClickListener({
            zoomImageFromThumb(thumb5View,R.drawable.photo1)
        })
        val thumb6View : View =  findViewById(R.id.thumb_button6)
        thumb6View.setOnClickListener({
            zoomImageFromThumb(thumb6View,R.drawable.space1)
        })
        val thumb7View : View =  findViewById(R.id.thumb_button7)
        thumb7View.setOnClickListener({
            zoomImageFromThumb(thumb7View,R.drawable.space2)
        })
        val thumb8View : View =  findViewById(R.id.thumb_button8)
        thumb8View.setOnClickListener({
            zoomImageFromThumb(thumb8View,R.drawable.soccer)
        })
        val thumb9View : View =  findViewById(R.id.thumb_button9)
        thumb9View.setOnClickListener({
            zoomImageFromThumb(thumb9View,R.drawable.sea1)
        })
        val thumb10View : View =  findViewById(R.id.thumb_button10)
        thumb10View.setOnClickListener({
            zoomImageFromThumb(thumb10View,R.drawable.police)
        })
        val thumb11View : View =  findViewById(R.id.thumb_button11)
        thumb11View.setOnClickListener({
            zoomImageFromThumb(thumb11View,R.drawable.plant1)
        })
        val thumb12View : View =  findViewById(R.id.thumb_button12)
        thumb12View.setOnClickListener({
            zoomImageFromThumb(thumb12View,R.drawable.nyang)
        })
        val thumb13View : View =  findViewById(R.id.thumb_button13)
        thumb13View.setOnClickListener({
            zoomImageFromThumb(thumb13View,R.drawable.maplestory)
        })
        val thumb14View : View =  findViewById(R.id.thumb_button14)
        thumb14View.setOnClickListener({
            zoomImageFromThumb(thumb14View,R.drawable.homework)
        })
        val thumb15View : View =  findViewById(R.id.thumb_button15)
        thumb15View.setOnClickListener({
            zoomImageFromThumb(thumb15View,R.drawable.flowers)
        })
        val thumb16View : View =  findViewById(R.id.thumb_button16)
        thumb16View.setOnClickListener({
            zoomImageFromThumb(thumb16View,R.drawable.fish)
        })
        val thumb17View : View =  findViewById(R.id.thumb_button17)
        thumb17View.setOnClickListener({
            zoomImageFromThumb(thumb17View,R.drawable.dog)
        })
        val thumb18View : View =  findViewById(R.id.thumb_button18)
        thumb18View.setOnClickListener({
            zoomImageFromThumb(thumb18View,R.drawable.basketball)
        })
        val thumb19View : View =  findViewById(R.id.thumb_button19)
        thumb19View.setOnClickListener({
            zoomImageFromThumb(thumb19View,R.drawable.study)
        })
        val thumb20View : View =  findViewById(R.id.thumb_button20)
        thumb20View.setOnClickListener({
            zoomImageFromThumb(thumb20View,R.drawable.time)
        })

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        backbutton2.setOnClickListener {
            this@SubActivity2.finishAffinity()
        }

    }
    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        currentAnimator?.cancel()

        // Load the high-resolution "zoomed-in" image.
        val expandedImageView: ImageView = findViewById(R.id.expanded_image)
        expandedImageView.setImageResource(imageResId)

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBoundsInt)
        findViewById<View>(R.id.container)
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                expandedImageView,
                View.X,
                startBounds.left,
                finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sub_2, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Togrid -> {
                val intent_two_grid = Intent(this, SubActivity2_2::class.java)
                startActivity(intent_two_grid)
            }
            R.id.TakePhoto -> {
                val intent_zoom_1 = Intent(this, SubActivity2_2_zoomActivity::class.java)
                startActivity(intent_zoom_1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}