package com.example.trial_clean_architecture.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.trial_clean_architecture.R
import com.squareup.picasso.Picasso

@BindingAdapter("image_url")
fun ImageView.loadImage(imageUrl: String?) {
    imageUrl?.let {
        Log.v("imageUrlimageUrl", it)

        if (it.isNotBlank()) {
            Picasso.get().load(it) //"https://developer.mozilla.org/static/img/favicon144.png"
                .placeholder(R.drawable.img_place_holder_user)
                .transform(CircleTransform())
                .error(R.drawable.img_place_holder_user)
                .into(this);

//        Glide.with(context)
//            .load(it)
////        .centerCrop()
//            .circleCrop()
//            .placeholder(R.drawable.img_place_holder_user)
//            .error(R.drawable.img_place_holder_user)
//            .into(this)}
        } else {
            setImageResource(R.drawable.img_place_holder_user)
        }
    }?:setImageResource(R.drawable.img_place_holder_user)
}

@BindingAdapter("imageResource")
fun ImageView.setImageViewResource(resource: Int) {
    setImageResource(resource)
}