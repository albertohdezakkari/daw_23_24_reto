import { FetchUtil } from "./fetchutil.js";

export class MovieRepository {
  static MOVIES = "movies";
  static async getMovies() {
    try {
      const response = await fetch(`${FetchUtil.BASE_URL}/${this.MOVIES}`);
      if (!response.ok) {
        throw new Error("Error al obtener las películas");
      }
      return await response.json();
    } catch (error) {
      console.error(error);
      return [];
    }
  }
}
