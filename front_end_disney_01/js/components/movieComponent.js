export class MovieComponent {
    constructor(movie) {
        this.movie = movie;
    }

    render() {
        const movieElement = document.createElement('div');
        movieElement.classList.add('movie');
        movieElement.innerHTML = `
            <h2>${this.movie.titulo}</h2>
            <img src="${this.movie.imagen}" alt="${this.movie.titulo}">
            <p>${this.movie.descripcion}</p>
            <p>Fecha de lanzamiento: ${new Date(this.movie.fecha_lanzamiento).toLocaleDateString()}</p>
        `;
        return movieElement;
    }
}