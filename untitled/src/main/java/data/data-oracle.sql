-- Crear secuencia para ID de peliculas
CREATE SEQUENCE peliculas_seq START WITH 1 INCREMENT BY 1;

-- Crear tabla de películas
CREATE TABLE peliculas (
                           id INT PRIMARY KEY,
                           titulo VARCHAR2(255),
                           precio DECIMAL(4,2),
                           duracion INT,
                           trailer VARCHAR2(100),
                           descripcion VARCHAR2(255),
                           n_votos INT,
                           s_puntuacion INT,
                           ano VARCHAR2(50),
                           url VARCHAR2(50)
);

-- Trigger para simular AUTO_INCREMENT
CREATE OR REPLACE TRIGGER peliculas_auto_increment
BEFORE INSERT ON peliculas FOR EACH ROW
BEGIN
SELECT peliculas_seq.NEXTVAL INTO :new.id FROM dual;
END;

-- Crear tabla de categorías
CREATE TABLE categorias (
                            id INT PRIMARY KEY,
                            nombre VARCHAR2(255)
);

-- Crear tabla de relación películas-categorías
CREATE TABLE peliculas_categorias (
                                      id_pelicula INT,
                                      id_categoria INT,
                                      CONSTRAINT fk_peliculas
                                          FOREIGN KEY (id_pelicula) REFERENCES peliculas(id),
                                      CONSTRAINT fk_categorias
                                          FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

-- INSERT de películas
INSERT INTO peliculas (titulo, precio, duracion, trailer, descripcion, n_votos, s_puntuacion, ano, url) VALUES
                                                                                                            ('Titanic', 5.99, 295, 'https://www.youtube.com/watch?v=jnFxtSuZRpU', 'Una joven (Kate Winslet) de sociedad abandona a su arrogante pretendiente (Billy Zane) por un artista (Leonardo DiCaprio) humilde en el transatlántico que se hundió durante su viaje inaugural.', 200, 900, '1997', 'titanic.com'),
                                                                                                            ('Mision imposible: Fallout', 0.99, 148, 'https://www.youtube.com/embed/vDX_r9MH5Z0', 'Un traficante de armas y un grupo de terroristas pretenden realizar un triple ataque nuclear con tres artefactos de plutonio, los cuales se pierden. Ethan Hunt y su equipo tendrán que recuperarlos antes de que caigan en las manos equivocadas.', 100, 300, '2018', 'mision_imposible.com');

-- INSERT de categorías
INSERT INTO categorias VALUES (1, 'Acción');
INSERT INTO categorias VALUES (2, 'Drama');

-- INSERT de relaciones película-categoría
INSERT INTO peliculas_categorias VALUES (1, 2);
INSERT INTO peliculas_categorias VALUES (2, 1);
