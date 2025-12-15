# AppTiempo - Aplicación del Tiempo para Android
AppTiempo es una aplicación Android nativa que permite a los usuarios consultar el estado del tiempo actual y el pronóstico para cualquier ciudad del mundo. El proyecto está desarrollado siguiendo las mejores prácticas de arquitectura de software y las guías de diseño modernas de Android.

## Captura de pantalla de la App 
<img src="app/src/main/java/com/example/apptiempo/utils/capturas/ captura1.png"  width="250">

<img src="app/src/main/java/com/example/apptiempo/utils/capturas/ captura2.png"  width="250">

<img src="app/src/main/java/com/example/apptiempo/utils/capturas/ captura3.png"  width="250">

## ✨ Características Principales
•	Búsqueda de Tiempo por Ciudad: Introduce el nombre de una ciudad para obtener datos meteorológicos al instante.
•	Tiempo Actual: Muestra la temperatura actual y la condición principal del tiempo (soleado, nublado, etc.).
•	Pronóstico Extendido: Presenta un pronóstico detallado para las próximas horas/días en una lista deslizable.
•	Interfaz Moderna: Utiliza el modo Edge-to-Edge para una experiencia de usuario inmersiva que aprovecha toda la pantalla.
•	Gestión de Errores: Informa al usuario si la ciudad no es encontrada o si ocurre un problema de conexión.
•	Iconos Personalizados: Utiliza iconos vectoriales para representar las diferentes condiciones del tiempo y un icono de aplicación adaptativo.
##🛠️ Tecnologías y Librerías
Este proyecto aprovecha un stack de tecnologías modernas recomendadas para el desarrollo de Android.
•	Lenguaje: Java
•	Arquitectura: MVVM (Model-View-ViewModel). La arquitectura separa la lógica de la interfaz de usuario de la lógica de negocio, lo que resulta en un código más limpio, escalable y fácil de mantener.
o	View: MainActivity (observa los datos y actualiza la UI).
o	ViewModel: MainViewModel (expone los datos a la UI y sobrevive a los cambios de configuración).
o	Model: WeatherRepository (gestiona el origen de los datos, en este caso, la red).
•	Componentes de Android Jetpack:
o	Lifecycle (ViewModel & LiveData): Para crear componentes de UI que reaccionan a los cambios en los datos y son conscientes de su ciclo de vida.
o	RecyclerView: Para mostrar la lista del pronóstico de manera eficiente.
o	Activity & AppCompat: Para la compatibilidad con versiones anteriores de Android.
•	Red (Networking):
o	Retrofit: Un cliente HTTP para Android y Java, utilizado para realizar las llamadas a la API de forma declarativa.
o	Gson: Para convertir automáticamente las respuestas JSON de la API en objetos Java (POJOs).
•	Gestión de Dependencias: Gradle con Version Catalogs (libs.versions.toml), lo que centraliza y simplifica la gestión de las versiones de las librerías.
•	API Externa: OpenWeatherMap API para obtener los datos meteorológicos.

## 📂 Estructura del Proyecto
El código está organizado en paquetes siguiendo la arquitectura MVVM para una clara separación de responsabilidades:
app/
 ├── java/com/example/apptiempo/
 │
 ├── data/                  # Capa de datos
 │    ├── model/            # Clases de modelo (POJOs) para la respuesta de la API.
 │    ├── network/          # Configuración de Retrofit (ApiClient, ApiService).
 │    └── repository/       # Repositorio que gestiona el origen de los datos.
 │
 ├── ui/                    # Capa de presentación (UI)
 │    ├── adapter/          # Adaptadores para RecyclerViews (ForecastAdapter).
 │    └── main/             # Clases relacionadas con la pantalla principal (MainActivity, MainViewModel).
 │
 └── utils/                 # Clases de utilidad (Resource.java).




## 🔒 Seguridad de la API Key
Para proteger la clave de la API de OpenWeatherMap y evitar que sea expuesta en el repositorio de GitHub, el proyecto implementa la siguiente estrategia de seguridad:
1.	La clave de la API se almacena en el archivo local.properties, que está incluido en .gitignore para no ser subido al control de versiones.
2.	El archivo app/build.gradle lee la clave desde local.properties durante el proceso de compilación.
3.	La clave se inyecta de forma segura en la clase BuildConfig, que se genera automáticamente.
4.	La aplicación accede a la clave a través de BuildConfig.WEATHER_API_KEY, manteniéndola fuera del código fuente versionado.

## 🚀 Cómo Empezar
Para compilar y ejecutar el proyecto en tu propia máquina, sigue estos pasos:
1.	Clona el repositorio:
    git clone https://github.com/tu-usuario/AppTiempo.git
    
2.	Obtén tu API Key:
o	Ve a OpenWeatherMap y regístrate para obtener una clave de API gratuita.

3.	Crea el archivo local.properties:
o	En la carpeta raíz de tu proyecto, crea un nuevo archivo llamado local.properties.

4.	Añade tu API Key:
o	Dentro de local.properties, añade la siguiente línea, reemplazando TU_API_KEY_AQUI con la clave que obtuviste:
WEATHER_API_KEY=”TU_API_KEY_AQUI”

5.	Abre y ejecuta en Android Studio:
o	Abre el proyecto en Android Studio.
o	Sincroniza el proyecto con Gradle.
o	Ejecuta la aplicación en un emulador o en un dispositivo físico.
