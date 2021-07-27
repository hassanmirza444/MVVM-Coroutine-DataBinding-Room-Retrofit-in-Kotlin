package com.hssn_mirza.digitify_code_challenge

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.hssn_mirza.digitify_code_challenge.databinding.AdapterMovieBinding
import com.hssn_mirza.digitify_code_challenge.model.MovieModel
import com.hssn_mirza.digitify_code_challenge.util.HelperFunctions
import java.io.File
import java.util.*

class MovieAdapter(private val mContext: Context) : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<MovieModel>()
    var currentYear = Calendar.getInstance().get(Calendar.YEAR);

    fun setMovies(movies: List<MovieModel>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]
        holder.binding.name.text = movie.title
        val imgPath = "https://image.tmdb.org/t/p/w500" + (movie.posterPath)
        val mThumbFile: File = File(
            HelperFunctions.getArworksThumbnailsPath(
                mContext,
                movie.getId().toString() + ""
            ) + movie.getId().toString() + ".jpg"
        )
        if (mThumbFile.exists()) {
            val imageUri = Uri.fromFile(mThumbFile)
            val requestOption: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background).fitCenter()

            Glide.with(mContext).load(imageUri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(
                    Glide.with(mContext)
                        .load(imageUri)
                        .apply(requestOption)
                )
                .apply(requestOption)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?, model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        return true
                    }
                })
                .into(holder.binding.imageview)

        } else {
            val requestOption: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background).fitCenter()
            Glide.with(mContext).load(imgPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(
                    Glide.with(mContext)
                        .load(imgPath)
                        .apply(requestOption)
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any,
                                target: Target<Drawable?>,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any,
                                target: Target<Drawable?>,
                                dataSource: DataSource,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }
                        })
                )
                .apply(requestOption)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        HelperFunctions.saveThumbnailImage(
                            resource,
                            HelperFunctions.getArworksThumbnailsPath(
                                mContext,
                                movie.getId().toString() + ""
                            ), movie.getId().toString() + ".jpg"
                        )
                        return true
                    }
                })
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.binding.imageview)
        }

        if (currentYear == movie.releaseDate.split('-')[0].toInt()) {
            holder.binding.tvReleaseDate.setTextColor(mContext.getColor(R.color.red))
            holder.binding.tvReleaseDate.setTypeface(
                holder.binding.tvReleaseDate.getTypeface(),
                Typeface.BOLD
            )
        }
        holder.binding.tvReleaseDate.setText(movie.releaseDate)


    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}