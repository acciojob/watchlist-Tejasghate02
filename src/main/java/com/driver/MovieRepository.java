package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String,Movie> movieMap = new HashMap<>();
    Map<String,Director> directorMap = new HashMap<>();
    Map<String, List<String>> pairMap = new HashMap<>();

    //add movie
    public String addMovie(Movie movie){
        String name=movie.getName();
        if(!movieMap.containsKey(name)){
            movieMap.put(name,movie);
        }
        return  "movie added successfully";
    }

    //add director
    public String addDirector (Director director){
        String name = director.getName();
        if(!directorMap.containsKey(name)){
            directorMap.put(name,director);
        }
        return  "director added successfully";
    }

    // pair an existing movie and director
     public String addMovieDirectorPair(String movieName, String directorName){
        if(!movieMap.containsKey(movieName) || !directorMap.containsKey(directorName)){
            return "movie or director not found";
        }
        if(pairMap.containsKey(directorName)){
            pairMap.get(directorName).add(movieName);
        }
        else{
            List<String> ans= new ArrayList<>();
            ans.add(movieName);
            pairMap.put(directorName,ans);
        }
        return  "movie-director pair added successfully";
     }

     //get movie by movie name
    public  Movie getMovieByName(String name){
        if(!movieMap.containsKey(name)){
            return  null;
        }
        return movieMap.get(name);
    }

    //get director by director name
    public  Director  getDirectorByName(String name){
        if(!directorMap.containsKey(name)){
            return  null;
        }
        return directorMap.get(name);
    }

    //Get List of movies name for a given director name
    public List<String>  getMoviesByDirectorName(String director){
        return pairMap.get(director);
    }

    //Get List of all movies added
    public List<String> findAllMovies(){
        List<String> ans = new ArrayList<>();
        for(String movie : movieMap.keySet()){
            ans.add(movie);
        }
        return  ans;
    }

    //Delete a director and its movies from the records
    public  String deleteDirectorByName(String directorName){
        List<String> movies = pairMap.get(directorName);
        for(int i=0; i< movies.size(); i++){
            if(movieMap.containsKey(movies.get(i))){
                movieMap.remove(movies.get(i));
            }
        }
        pairMap.remove(directorName);
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }
        return "director and its movie removed successfully";
    }

    //Delete all directors and all movies by them from the records
    public String  deleteAllDirectors(){
        HashSet<String> movieset = new HashSet<>();
        for(String director : pairMap.keySet()){
            for(String movie : pairMap.get(director)){
                movieset.add(movie);
            }
        }
        for (String movie : movieset){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        return " all director and movie removed successfully";
    }
}
