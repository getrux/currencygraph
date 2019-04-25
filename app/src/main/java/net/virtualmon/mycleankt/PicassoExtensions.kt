package net.virtualmon.mycleankt

import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ImageView.bind(url: String) {
    Picasso.with(this.context).load(url).fit().into(this)
}