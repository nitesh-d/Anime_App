Anime Discovery App:
An Android application built with modern, native technologies to explore top-rated anime series using the Jikan API. This project demonstrates a clean and scalable architecture.

Features Implemented:
Modern UI with Jetpack Compose: The entire user interface is built declaratively with Jetpack Compose, ensuring a flexible and maintainable UI layer.

MVVM Architecture: Follows the recommended Model-View-ViewModel pattern to separate data, UI, and business logic.

Dependency Injection with Hilt (KSP): Utilizes Dagger Hilt with the faster KSP engine for efficient and robust dependency management.

Asynchronous Operations: Employs Kotlin Coroutines and StateFlow for managing asynchronous tasks and UI state reactively.

Clean Networking Layer: Fetches data from the Jikan API using Retrofit and Gson.

Responsive Grid Layout: The home screen features an adaptive grid that adjusts the number of columns based on the screen size.

Detailed Anime View: A dedicated scrollable screen to display comprehensive details of a selected anime,this additionally includes 2 buttons to watch trailer of selected anime (if available) and share a message using implicit intent.

Efficient Image Loading: Uses the Glide library to asynchronously load and cache images.

Robust State Handling: Gracefully handles loading and error states, with a "Try Again" option for network failures.



Tech Stack

Language: Kotlin

UI: Jetpack Compose

Architecture: MVVM (Model-View-ViewModel)

Dependency Injection: Dagger Hilt (with KSP)

Asynchronous Programming: Kotlin Coroutines & Flow

Networking: Retrofit & OkHttp

JSON Parsing: Gson

Image Loading: Glide

Video Playback: Youtube Player API

Navigation: Jetpack Navigation Compose

Assumptions Made:
The Jikan API (https://api.jikan.moe/v4/) is available and responsive.

A stable internet connection is required for the application to fetch and display data for the first time..

For the trailer on the detail page, displaying the Toast message is an acceptable fallback if a trailer is unavailable.

For the Anime title, if the English title is not available then default title is shown, and if default is also not available then “Untitled” is acceptable fallback.


Known Limitations

No Pagination: The app only loads the first page of results from the /top/anime endpoint. It does not support infinite scrolling or loading subsequent pages.

No Search or Filtering: The app lacks functionality to search for a specific anime or to filter/sort the displayed list based on criteria like genre or rating.



















