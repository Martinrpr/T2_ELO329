# Tarea 2 – Simulador Gráfico de ELOTelTags y Aplicación Find My

**Ramo:** ELO329 – Diseño y Programación Orientados a Objetos  
**Semestre:** 1er semestre 2026

## Integrantes

| Nombre | Rol |
|--------|-----|
| Carlos Ramírez | Desarrollador |
| Nicolás King | Desarrollador |
| Yasin Morales | Desarrollador |
| Martin Pérez| Desarrollador |


## Descripción

Simulador gráfico en JavaFX de dispositivos de rastreo tipo AirTag (EloTelTag), celulares y tablets. Los dispositivos se mueven por un territorio real (mapa de Placeres, Valparaíso), rebotan en los bordes y se comunican con una nube (ETNube). La función **Find My** permite ver en tiempo real la posición de los bienes de cada persona.

El proyecto se desarrolló en **4 etapas iterativas e incrementales**.

---

## Estructura del repositorio

```
Tarea2_elo/
├── Etapa1/   Stage1: solo celulares, menú inactivo
├── Etapa2/   Stage2: todos los equipos, Play/Pause activo
├── Etapa3/   Stage3: ETNube, radar visual, Find My estático
├── Etapa4/   Stage4 (final): sonido, Find My auto-actualizable, Javadoc
└── ayuda/    Recursos del profesor (imágenes, código de referencia)
```

Cada etapa contiene:
- `src/` — código fuente Java
- `config.txt` — archivo de configuración de ejemplo
- `makefile` — instrucciones de compilación y ejecución

---
# Requisitos

- JDK 21 o compatible
- JavaFX SDK 21 o compatible
- GNU Make instalado (`make --version`)

---

# Configuración JavaFX

Cada integrante debe modificar la variable `JAVAFX_PATH`
en el `makefile` con la ruta local de su SDK JavaFX.

Ejemplo Windows:

```makefile
JAVAFX_PATH = C:/Users/TU_USUARIO/Desktop/javafx-sdk-21/lib
```

---

# Configuración JDK

Actualizar las rutas de Java según la instalación local:

```makefile
JAVAC = "C:/Program Files/Java/jdk-21/bin/javac.exe"
JAVA = "C:/Program Files/Java/jdk-21/bin/java.exe"
JAVADOC = "C:/Program Files/Java/jdk-21/bin/javadoc.exe"
```

---

# Compilación y ejecución

Desde la carpeta de cada etapa:

## Compilar

```bash
make
```

## Ejecutar

```bash
make run
```

Al ejecutar se abrirá un FileChooser para seleccionar el archivo `config.txt`.

## Generar documentación Javadoc

```bash
make doc
```

La documentación se generará en:

```text
doc/index.html
```

## Limpiar archivos compilados

```bash
make clean
```

### Etapa 1
```bash
cd Etapa1
make run
```
Abre ventana con imagen de fondo y tres celulares. Menú inactivo.

### Etapa 2
```bash
cd Etapa2
make run
```
Agrega tags (círculos naranjos) y tablets (rectángulos verdes). Play/Pause funcional.

### Etapa 3
```bash
cd Etapa3
make run
```
Los celulares reportan posición a ETNube cada 4 s. Tags y tablets muestran animación de radar. Menú Find My disponible (ventana estática).

### Etapa 4 (final)
```bash
cd Etapa4
make run
```
Versión completa: sonido en búsqueda de radar, ventana Find My se actualiza cada 1 segundo.

---

## Archivo de configuración

Formato de `config.txt`:
```
<archivo_imagen>
<delta_tiempo>
<número_de_personas>
<nombre> <nTags> <tieneTablet(0|1)>
<x> <y> <rapidez> <angulo> <delta_angulo>
(<nombreTag> <x> <y> <rapidez> <angulo> <delta_angulo>)*
[<x> <y> <rapidez> <angulo> <delta_angulo>]   ← tablet (si tieneTablet=1)
```

Ejemplo incluido en cada etapa: `config.txt` con 3 personas (Pedro, Juan, Diego) sobre el mapa `../ayuda/Placeres.jpg`.

---

## Generación de Javadoc (Etapa 4)

```bash
cd Etapa4
make doc
```

Genera documentación HTML en `Etapa4/doc/`. Abre `doc/index.html` en el navegador para consultarla.

Las clases documentadas son: **Equipo**, **EloTelTag** y **EloTelTagView** (según requisito de la Etapa 4).

---

## Arquitectura (MVC)

| Capa | Clases |
|------|--------|
| **Modelo** | `Equipo`, `Cellular`, `EloTelTag`, `Tablet`, `ETNube`, `Territory` |
| **Vista** | `CellularView`, `EloTelTagView`, `TabletView`, `TerritoryView`, `FindMyWindow` |
| **Controlador** | `Stage4` (timelines de animación), `Territory.moveAll()` |

La posición de cada dispositivo se almacena como `DoubleProperty` de JavaFX, lo que permite que las vistas se actualicen automáticamente sin llamadas manuales mediante enlace de propiedades (`bind`).

---

## Documentación

Ver [doc/documentacion.html](Etapa4/doc/documentacion.html) para el diagrama de clases completo y descripción de la arquitectura.
