import { MovieRepository } from '../repository/movieRepository.js';

export class MovieService {
    static async listarPeliculas() {
        return await MovieRepository.getMovies();
    }
}

