package com.metehanbolat.thinqeek.viewmodel

import android.content.Context
import android.graphics.Movie
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.metehanbolat.thinqeek.adapter.MoviesRecyclerAdapter
import com.metehanbolat.thinqeek.model.Movies

class MoviesFragmentViewModel : ViewModel() {

    fun getFilm(firestore: FirebaseFirestore, context: Context, movieList: ArrayList<Movies>, adapter: MoviesRecyclerAdapter){
        firestore.collection("Movies").addSnapshotListener { value, error ->
            if (error != null){
                Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val movies = value.documents
                        movieList.clear()
                        for (movie in movies){
                            val myMovie = Movies(movie["name"].toString(), movie["year"].toString())
                            movieList.add(myMovie)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}