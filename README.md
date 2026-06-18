# UTNGRunner - Wear OS Game

UTNGRunner es un videojuego de tipo "Infinite Runner" desarrollado específicamente para dispositivos **Wear OS**. El proyecto sigue los principios de **Clean Architecture** y utiliza las últimas tecnologías de Android como **Jetpack Compose for Wear OS**.

## Información del Autor
*   **Nombre:** Zahir Andrés Rodríguez Mora
*   **Grupo:** GIDS6092
*   **Institución:** UTNG

## Características del Proyecto
*   **Clean Architecture:** Separación clara entre las capas de Dominio, Datos y Presentación.
*   **60 FPS:** El motor del juego está optimizado para funcionar a 60 cuadros por segundo, garantizando una experiencia fluida.
*   **Rotary Input:** Soporte completo para la corona física/digital del reloj para controlar los saltos y deslizamientos del personaje.
*   **Integración con Health Services:** Monitoreo en tiempo real de la frecuencia cardíaca (BPM) del usuario durante el juego.
*   **Haptic Feedback:** Respuesta táctil (vibración) al realizar acciones como saltar o al recibir daño.
*   **Persistencia:** Guardado de la puntuación más alta (High Score) mediante Jetpack DataStore.

## Arquitectura
1.  **Capa de Dominio:** Contiene los modelos inmutables (`GameState`, `Player`, `Obstacle`, `Coin`) y las reglas de negocio (Casos de Uso).
2.  **Capa de Datos:** Implementa los repositorios y fuentes de datos (DataStore para preferencias y Health Services para sensores).
3.  **Capa de Presentación:** Utiliza el patrón MVVM (`GameViewModel`) y renderizado directo en `Canvas` mediante Compose.

## Requisitos
*   Dispositivo Wear OS 3.0+ (API 30+)
*   Permisos de sensores de cuerpo activos para el monitoreo de BPM.

## Cómo ejecutar los tests
Para verificar la pureza del motor de juego y la lógica de colisiones, ejecuta:
```bash
./gradlew :wear:test
```

---
© 2026 Zahir Andrés Rodríguez Mora
