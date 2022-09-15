package cz.marianjanik.L13RESTmyBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class MainService {

    public Movie getMovieForTest() {
        return new Movie(7,"test2",2000,"test222",5);
    }

    public Movie getMovieByIdSql(int idMovie) throws IOException {
        MovieMapper movieMapper = initMapperSql();
        Movie movie = movieMapper.getMovieById(idMovie);
        return movie;
    }

    public List<Movie> getAllMovieSql() throws IOException {
        MovieMapper movieMapper = initMapperSql();
        List<Movie> movieList = movieMapper.getAllMovie();
        return movieList;
    }

    public void setMovieSql(Movie movie) throws IOException {
        SqlSession session = initSessionSql();
        MovieMapper movieMapper = session.getMapper(MovieMapper.class);
        Movie separateMovie = new Movie();
        separateMovie.setName(movie.getName());
        separateMovie.setYearOfProduction(movie.getYearOfProduction());
        separateMovie.setGenre(movie.getGenre());
        separateMovie.setMyScore(movie.getMyScore());
        separateMovie.setId(0);
        movieMapper.setMovie(separateMovie);
        session.commit();
    }

    public void updateMovieSql(Movie movie) throws IOException {
        SqlSession session = initSessionSql();
        MovieMapper movieMapper = session.getMapper(MovieMapper.class);
        movieMapper.updateMovieById(movie);
        session.commit();
    }
    public void deleteMovieByNameSql(String name) throws IOException {
        SqlSession session = initSessionSql();
        MovieMapper movieMapper = session.getMapper(MovieMapper.class);
        movieMapper.deleteMovieByName(name);
        session.commit();
    }

    public void deleteMovieByIdSql(int idMovie) throws IOException {
        SqlSession session = initSessionSql();
        MovieMapper movieMapper = session.getMapper(MovieMapper.class);
        movieMapper.deleteMovieById(idMovie);
        session.commit();
    }


    private SqlSession initSessionSql() throws IOException {
        Reader reader = Resources.getResourceAsReader("MyBatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSessionFactory.getConfiguration().addMapper(MovieMapper.class);
        return sqlSessionFactory.openSession();
    }

    private MovieMapper initMapperSql() throws IOException {
        SqlSession session = initSessionSql();
        return session.getMapper(MovieMapper.class);
    }
}
