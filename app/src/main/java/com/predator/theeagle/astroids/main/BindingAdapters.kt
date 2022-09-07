package com.predator.theeagle.astroids

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.predator.theeagle.astroids.entities.PictureOfTheDay

import com.squareup.picasso.Picasso


@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("pictureOfTheDay")
fun bindImagePictureOfTheDay(imageView: ImageView, data: PictureOfTheDay?) {

    data?.let {

        if (it.mediaType == "image") {

            Picasso.with(imageView.context)
                .load(it.url)
                .into(imageView)

            val strFormat = imageView.resources.getString(
                R.string.picture_of_the_day_text)
            imageView.contentDescription = String.format(strFormat, it.title)

        }
    }
}
