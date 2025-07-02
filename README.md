# TymeX Take Home Assignment

This project is a take-home assignment for an Android Developer position at **TymeX**.  
The app demonstrates fetching GitHub users and viewing detailed information for each user using modern Android development best practices.

## 🧩 Features

- Paginated list of GitHub users (20 per page)
- User detail screen with offline-first support
- Stores user detail locally for instant access on second app launch
- Navigation between list and detail screens

## 🛠️ Technologies Used

### Architecture & Patterns
- **MVVM** (Model-View-ViewModel)
- **Repository Pattern**
- **Clean Architecture** (Domain, Data, Presentation separation)

### Jetpack & Android Libraries
- **Hilt** – Dependency Injection
- **Navigation Component** – Fragment navigation & Safe Args
- **Room** – Local database for user detail caching
- **Paging 3** – Paginated API fetching
- **ViewBinding** – Safe view access
- **LiveData & StateFlow** – Lifecycle-aware reactive data
- **ConstraintLayout & Material Components** – Modern UI building

### Networking
- **Retrofit** – API communication
- **Gson** – JSON parsing
- **OkHttp + Logging Interceptor** – API logging

### Image Loading
- **Glide** – Load GitHub avatar images efficiently

## 🔧 Build Setup

### Requirements
- Android Studio Electric Eel or newer
- JDK 17+
- Kotlin DSL (`build.gradle.kts`)
- Internet connection for API access

### Configuration
