-- Crear la base de datos netflix
CREATE DATABASE IF NOT EXISTS netflix;

-- Usar la base de datos netflix
USE netflix;

-- Crear la tabla de películas
CREATE TABLE peliculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    Precio DECIMAL(4,2),
    Duracion INT,
    Trailer VARCHAR(100),
    descripcion VARCHAR(255),
    N_Votos INT,
    S_Puntuacion INT,
    ano VARCHAR(50),
    URL VARCHAR(50)
);

-- Crear la tabla de categorías
CREATE TABLE categorias (
    id INT PRIMARY KEY,
    nombre VARCHAR(255)
);

-- Crear la tabla de relación
CREATE TABLE peliculas_categorias (
    id_pelicula INT,
    id_categoria INT,
    FOREIGN KEY (id_pelicula) REFERENCES peliculas(id),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

-- INSERT de películas
INSERT INTO peliculas (titulo, Precio, Duracion, Trailer, descripcion, N_Votos, S_Puntuacion, ano, URL) VALUES
('Titanic', 5.99, 295, 'https://www.youtube.com/watch?v=jnFxtSuZRpU', 'Una joven (Kate Winslet) de sociedad abandona a su arrogante pretendiente (Billy Zane) por un artista (Leonardo DiCaprio) humilde en el transatlántico que se hundió durante su viaje inaugural.', 200, 900, '1997', 'titanic'),
('Mision imposible: Fallout', 0.99, 148, 'https://www.youtube.com/embed/vDX_r9MH5Z0', 'Un traficante de armas y un grupo de terroristas pretenden realizar un triple ataque nuclear con tres artefactos de plutonio, los cuales se pierden. Ethan Hunt y su equipo tendrán que recuperarlos antes de que caigan en las manos equivocadas.', 100, 300, '2018', 'mision_imposible'),
('Interstellar', 9.99, 169, 'https://www.youtube.com/watch?v=NqniWGlg5kU', 'Un grupo de exploradores hacen uso de un orificio recién descubierto para superar las limitaciones de los viajes espaciales humanos y conquistar las vastas distancias relacionadas con los viajes interestelares.', 500, 2200, '2014', 'interstellar'),
('El Padrino', 7.99, 178, 'https://www.youtube.com/watch?v=gCVj1LeYnsc', 'Una adaptación ganadora del Premio de la Academia, de la novela de Mario Puzo acerca de la familia Corleone.', 500, 2400, '1972', 'padrino'),
('La forma del agua', 3.99, 183, 'https://www.youtube.com/watch?v=FMNTFFhR__g', 'Elisa es una joven muda que se enamora de un hombre anfibio que está recluido en un acuario en un laboratorio secreto, propiedad del Gobierno, en el que ella trabaja limpiando. Llevada por el amor, Elisa trama un plan para liberar al mutante.', 100, 400, '2017', 'agua'),
('Tres anuncios en las afueras', 4.99, 175, 'https://www.youtube.com/watch?v=uLr4jog9EX8', 'Una mujer cuya hija fue asesinada se enfrenta a la policía usando los carteles publicitarios para denunciar que han pasado meses desde que se cometió el crimen y no solo no han resuelto nada, sino que parecen no tener interés en hacerlo.', 50, 165, '2017', 'anuncios'),
('It', 7.99, 195, 'https://www.youtube.com/watch?v=xKJmEC5ieOk', 'Varios niños de una pequeña ciudad del estado de Maine se alían para combatir a una entidad diabólica que adopta la forma de un payaso y desde hace mucho tiempo emerge cada 27 años para sac', 100, 400, '2017', 'agua');

-- INSERT de categorías
INSERT INTO categorias VALUES (1, 'Acción');
INSERT INTO categorias VALUES (2, 'Drama');
INSERT INTO categorias VALUES (3, 'Comedia');
INSERT INTO categorias VALUES (4, 'Terror');
INSERT INTO categorias VALUES (5, 'Aventura');

-- INSERT de relaciones película-categoría
INSERT INTO peliculas_categorias VALUES (1, 2);
INSERT INTO peliculas_categorias VALUES (2, 1);
INSERT INTO peliculas_categorias VALUES (3, 2);
INSERT INTO peliculas_categorias VALUES (4, 2);
INSERT INTO peliculas_categorias VALUES (5, 3);
INSERT INTO peliculas_categorias VALUES (6, 3);
INSERT INTO peliculas_categorias VALUES (7, 4);
