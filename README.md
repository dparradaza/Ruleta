# Clean code (Ruleta)
Backend: Spring boot
Base de datos: Redis


# Requerimientos:
- Docker

# Ejecución de la prueba: 

```shell
#1. Clonar repositorio gitHub
git clone https://github.com/dparradaza/CleanCode.git

#2. Ir a la ruta del proyecto
cd ruleta

# 3. Ejecutar docker-compose
docker-compose up
```



- Acceder al endpoint base http://localhost:8080 
- Url de swagger  http://localhost:8080/swagger-ui.html#

## Instrucciones:

Las rutas disponibles son:
- /user/save [post] Crea 5 usuarios por defecto con una cantidad de 15000 en dinero.
- /user [get] muestra a todos los usuarios y su dinero.
- /user/{id} [get] muestra la informción de de un usuario específico a partir de su id.
- /ruleta [get] Muestra todas las ruletas y sus estados.
- /ruleta [post] Crea una nueva ruleta y retorna su Id.
- /ruleta/{id} [get] Muestra una ruleta en especifico, {id} es un id de ruleta.
- /ruleta/betOnRuletaForNumber/{rouletteId}/{numberWin}/{cash} [patch] Crea una apuesta por número, donde:
    - {rouletteId} es un id de ruleta.
    - {numberWin} es el número al cual se va a apostar.
    - {cash} es la cantidad de dinero a apostar.
    - Además recibe un header llamado UserId con un id de usuario.
- /ruleta/betOnRuletaForColor/{rouletteId}/{numberWin}/{cash} [patch] Crea una apuesta por color, donde:
    - {rouletteId} es un id de ruleta.
    - {blackIsWin} es un booleano, en el cual si ingresa true elige el negro y si ingresa false elige rojo.
    - {cash} es la cantidad de dinero a apostar.
    - Además recibe un header llamado UserId con un id de usuario.
- /ruleta/open/{id} [patch] abre una ruleta solo si existe y nunca hasido abierta.
- /ruleta/close/{id} [patch] cierra una ruleta solo si actualmente está abierta, además genera el numero y color ganador y retorna las ganancias a quienes ganan.

    
    
