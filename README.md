
# BookFinderApp

BookFinderApp is a book discovery and management application built using Jetpack Compose, MVVM architecture, and Room Database. It allows users to browse books from an external API, view details, and manage their favorite collection locally.

## Features

-   **Browse Books**: Fetch and display a list of books from an API.
    
-   **Book Details**: View detailed information about books.
    
-   **Favorite Books**: Add and manage favorite books using a local database.
    
-   **Offline Support**: View saved favorite books without an internet connection.
    
-   **User Preferences**: Manage theme and settings using DataStore.
    

## Project Structure

```
BookFinderApp
├── data
│   ├── local
│   │   ├── FavouriteBookDao.kt          # Data Access Object for Room Database
│   │   ├── FavouriteBooksDatabase.kt    # Room Database setup
│   │   ├── FavouriteBooksRepository.kt  # Repository for local DB operations
│   │   ├── OfflineFavouriteBooksRepository.kt  # Offline data handling
│   │   └── UserPreferenceRepository.kt  # Manages user preferences with DataStore
│   └── network
│       ├── BooksApiService.kt          # Retrofit API service for fetching books
│       └── BooksRepository.kt          # Repository for network operations
├── di
│   └── AppModule.kt                     # Dependency injection setup using Hilt
├── MainActivity.kt                      # Main entry point of the app
├── model
│   ├── Books.kt                         # Data model for books
│   ├── BooksState.kt                    # State management for books
│   └── FavouriteBook.kt                 # Data model for favorite books
├── navigation
│   ├── Navigation.kt                    # Handles navigation between screens
│   └── Routes.kt                        # Defines navigation routes
├── ui
│   ├── screens
│   │   ├── BookScreen.kt                # Main book listing screen
│   │   ├── DetailScreen.kt              # Book details screen
│   │   ├── ErrorScreen.kt               # UI for handling errors
│   │   ├── FavouriteDetailScreen.kt     # Details screen for favorite books
│   │   ├── FavouriteScreen.kt           # Favorite books list screen
│   │   └── HomeScreen.kt                # Home screen
│   ├── theme
│   │   ├── Color.kt                     # App color settings
│   │   ├── Theme.kt                     # Theme settings and Material Design 3
│   │   └── Type.kt                      # Typography settings
│   └── utils
│       ├── BottomNavBar.kt              # Bottom navigation bar UI
│       └── BottomNavItem.kt             # Bottom navigation items
└── viewmodel
    └── BooksViewModel.kt                # ViewModel for managing book data

```

## Technologies Used

-   **Jetpack Compose** - Modern UI Toolkit
    
-   **MVVM Architecture** - Best practices for state management
    
-   **Navigation Component** - Manages screen navigation
    
-   **DataStore Preferences** - Stores user preferences
    
-   **Retrofit** - Handles API requests
    
-   **Room Database** - Local storage for favorite books
    
-   **Hilt (Dagger)** - Dependency injection
    
-   **Coil** - Image loading library
    
-   **Material Design 3** - UI design principles
    
-   **Kotlin Coroutines** - Asynchronous programming support
    

## Getting Started

### Setup

1.  **Clone the repository:**
    
    ```sh
    git clone https://github.com/anish920/BookFinderApp.git
    
    ```
    
2.  **Open the project in Android Studio.**
    
3.  **Add API Key** (in `BooksRepository.kt`).
    
4.  **Run the app on an emulator or physical device.**
