package cz.marianjanik.l13restmybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MovieMapper {

    @Select("SELECT id AS idMovie,name,year_of_production AS yearOfProduction,genre,my_score AS myScore " +
            "FROM movie WHERE id=#{idMovie}")
    Movie getMovieById(int idMovie);

    @Select("SELECT id AS idMovie,name,year_of_production AS yearOfProduction,genre,my_score AS myScore " +
            "FROM movie")
    List<Movie> getAllMovie();

    @Insert("INSERT INTO movie (name, year_of_production, genre, my_score) " +
            "VALUES (#{name},#{yearOfProduction},#{genre},#{myScore})")
    void setMovie(Movie movie);

    @Update("UPDATE movie " +
            "SET name=#{name},year_of_production=#{yearOfProduction}," +
            "genre=#{genre},my_score=#{myScore} " +
            "WHERE id=#{idMovie}")
    void updateMovieById(Movie movie);

    @Delete("DELETE FROM movie WHERE name=#{name}")
    void deleteMovieByName(String name);

    @Delete("DELETE FROM movie WHERE id=#{idMovie}")
    void deleteMovieById(int idMovie);
}
