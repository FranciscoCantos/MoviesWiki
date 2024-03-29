package com.example.movieswiki.UI.Common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.movieswiki.R

class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var ratio: Float = 1f

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
        ratio = a.getFloat(R.styleable.AspectRatioImageView_ratio, 1f)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (measuredWidth == 0 && measuredHeight == 0) {  return  }

        var width = measuredWidth
        var height = measuredHeight

        if (measuredWidth > 0) {
            height = (measuredWidth * ratio).toInt()
        } else if (measuredHeight > 0) {
            width = (height / ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }
}