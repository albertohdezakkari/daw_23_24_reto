import { MovieService } from "./api/movieService.js";
import { MovieComponent } from "./components/movieComponent.js";

const appContainer = document.getElementById("app");

async function cargarPeliculasDisney() {
  const movies = await MovieService.listarPeliculas();
  movies.forEach((movieData) => {
    const movieComponent = new MovieComponent(movieData);
    const movieElement = movieComponent.render();
    appContainer.appendChild(movieElement);
  });
}

window.addEventListener("load", cargarPeliculasDisney);
