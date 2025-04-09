# 📱 Finanzauto

## 📝 Descripción del Problema
La aplicación busca resolver el problema de cómo gestionar usuarios de forma sencilla. Permite **registrar, ver, editar y eliminar usuarios** en una base de datos. Está pensada como una herramienta básica para practicar o como base en otros proyectos Android.

---

## ✅ Requisitos Previos

- Android Studio Flamingo o superior  
- Java 11 o superior  
- Un emulador o dispositivo físico con Android 8.0 (API 26) o superior

---

## 🚀 Tecnologías Utilizadas

- **Lenguaje**: Kotlin  
- **Arquitectura**: MVVM (Model-View-ViewModel)  
- **Navegación**: Jetpack Navigation Component

---

### 📚 Librerías principales
- **Retrofit**: Para llamadas a servicios API REST  
- **Coroutines**: Para manejo de operaciones asíncronas  
- **ViewModel & LiveData**: Para la gestión del ciclo de vida y los estados de UI  
- **Hilt**: Inyección de dependencias  
- **View Binding / Data Binding**: Enlace seguro y eficiente con las vistas  
- **Temas y estilos personalizados** para una UI consistente  

---

## 🛠️ Instrucciones de Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/cristianpaez123/finanzauto.git

   Abre el proyecto en Android Studio

2. Sincroniza las dependencias de Gradle

3. Configura un emulador o conecta un dispositivo físico

4. Haz clic en el botón "Run" para ejecutar la aplicación

---

## 🧠 Estructura del Proyecto

El proyecto sigue una Clean Architecture con separación por capas y responsabilidades bien definidas:
```
com.example.finanzautotest
│
├── data               # Capa de datos y red
│   ├── model          # Modelos de datos remotos
│   └── network        # Definición de servicios API
│
├── di                 # Inyección de dependencias (Hilt)
│   └── NetworkModule.kt
│
├── domain             # Lógica de negocio
│   ├── model          # Entidades del dominio
│   ├── repository     # Interfaces de repositorios
│   └── use cases      # Casos de uso: CreateUser, DeleteUser, GetDataUser
│
├── iu                 # Capa de presentación (UI)
│   ├── adapter        # Adaptadores para RecyclerView y otros
│   ├── helper         # Utilidades auxiliares de UI
│   ├── model          # Modelos específicos de UI
│   ├── state          # Estados de la interfaz
│   ├── view           # Fragments o Activities
│   └── viewModel      # ViewModels que conectan con casos de uso
│
├── utils              # Funciones de utilidad reutilizables
    ├── ContextExtensions
    ├── InputValidator
    └── ViewExtensions
```
---

## ⚙️ Decisiones Técnicas

MVVM
Separación clara entre lógica y UI, permitiendo una arquitectura escalable y testeable.

Hilt para Inyección de Dependencias
Facilita el manejo de dependencias y mejora la modularidad.

Repository Pattern
Abstracción entre la fuente de datos y la lógica de negocio.

Modularización
Paquete utils con validaciones y extensiones reutilizables para mantener limpio el código.

Binding
Uso de View Binding y Data Binding para una manipulación más segura de vistas.

---

## 🚧 Limitaciones y Mejoras Futuras
No se implementaron pruebas unitarias ni de UI

El manejo de errores puede ser mejorado (especialmente en red y base de datos)

Se puede agregar soporte para paginación en la lista de usuarios

---

## 👤 Autor

Nombre: Cristian Paez

GitHub: @cristianpaez123

Correo: cristianpaezguerrero@gmail.com

Celular: +57 300 702 5600

---

<h3>Pantalla Principal</h3>
<img src="https://github.com/user-attachments/assets/5ec71e97-3a19-4cfa-928c-72f741e5ed3c" alt="Pantalla Principal" width="200"/>

<h3>Pantalla Registro de usuario</h3>
<img src="https://github.com/user-attachments/assets/40432d56-4db0-4201-a0ee-64f485a83789" alt="Pantalla Principal" width="200"/>

<h3>Pantalla eliminacion de usuario</h3>
<img src="https://github.com/user-attachments/assets/129eb8ef-aa6e-4291-b216-be1272ed11e0" alt="Pantalla Principal" width="200"/>

<h3>Pantalla Principal (night)</h3>
<img src="https://github.com/user-attachments/assets/f1edf5ab-9fdc-4889-81a9-1424a3268c3e" alt="Pantalla Principal" width="200"/>

<h3>Pantalla Registro de usuario (night)</h3>
<img src="https://github.com/user-attachments/assets/2bc0ca29-8113-428b-a703-7ea868212c5d" alt="Pantalla Principal" width="200"/>




