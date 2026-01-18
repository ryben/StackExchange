# 🔍 StackOverflow User Search (via StackExchange API)

A native Android application that provides an interface for searching and viewing Stack Overflow user profiles using the StackExchange API

---

## 📱 App Previews

<p align="center">
  <img src="demo/01-search-screen.png" alt="Search Screen" width="360">
  <img src="demo/02-user-info-screen.png" alt="User Info Screen" width="360">
</p>

### 🎬 Interaction Demo
Watch the app in action:

[**Click here to view the Demo Recording**](https://github.com/user-attachments/assets/41b2a810-89d3-4491-8c5b-2eb34f1eaa3c)



---

## 🚀 Quick Start

### Try the App
You can test the application immediately on your Android device by downloading the pre-built APK:

👉 **[Download stackoverflow-user-search-debug.apk](demo/stackoverflow-user-search-debug.apk)**

---

## 📋 Features

- **User Search:** Query any user across the Stack Overflow database.
- **Optimized Results:** The app returns up to **20 search results** each time for a fast and focused browsing experience.
- **Smart Sorting:** Results are sorted alphabetically. To ensure accuracy, the app trims leading spaces from display names returned by the API before performing the final sort.
- **View Profiles:** View display name, location, reputation, and account creation dates.

---

## 🛠️ Built With

- **[StackExchange API](https://api.stackexchange.com/docs)** – Powering the user data retrieval.
- **Jetpack Compose** – For a modern, declarative UI.
- **Material 3** – Implementing the latest design system guidelines.
- **Hilt** – Dependency injection for a modular and testable codebase.
- **Retrofit** – Handling REST API communication with Stack Exchange.
- **Glide** – Efficient image loading and caching for user avatars.
- **MVVM Architecture** – Ensuring a clean separation of concerns and maintainable code.
- **Kotlin Coroutines** – Managing asynchronous tasks and smooth background processing.

---
