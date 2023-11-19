package com.example.gweiland_top20crypto.utility

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

object AnimationUtil {
    /**
     * To handle multiple clicks on the same view.
     */

    fun View.uniClick(isAnimated: Boolean = false, debounceTime: Int = 500, action: () -> Unit) {

        fun animation() {
            val animX = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.9f, 1.0f)
            val animY = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.9f, 1.0f)
            val animator = AnimatorSet()
            animator.play(animX).with(animY)
            animator.start()
        }

        setOnClickListener {
            when {
                tag != null && (tag as Long) > System.currentTimeMillis() -> return@setOnClickListener
                else -> {
                    if (isAnimated) animation()
                    tag = System.currentTimeMillis() + debounceTime.toLong()
                    action()
                }
            }
        }
    }

}