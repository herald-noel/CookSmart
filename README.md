# CookSmart 🍳

CookSmart is an intelligent kitchen companion that bridges the gap between "what's in the fridge" and "what's for dinner." By leveraging on-device image recognition, CookSmart identifies food ingredients from a single photo and suggests tailored recipes to help users cook efficiently and reduce food waste.

## 📌 Project Overview

The core mission of CookSmart is to simplify meal planning. Instead of manually searching for recipes, users can simply take a photo of an ingredient. The app processes the image, identifies the item, and queries a recipe database to provide instant culinary inspiration.

---

## 🏗 Architecture (MVC Pattern)

This project strictly follows the **Model-View-Controller (MVC)** design pattern to ensure a clean separation of concerns, as detailed in the technical class diagrams.

### 1. Models (Data & Logic)
* **UserModels (Login/Register):** Handles Firebase authentication and user data persistence.
* **Recipe & RecipeApiResponseItem:** Defines the structure of recipe data, including ingredients, instructions, and image URLs.
* **ProfileModel:** Manages user-specific data including saved diagrams and history.

### 2. Views (UI Components)
* **HomeView:** The main dashboard featuring the camera interface and ingredient detection results.
* **RecipeView:** Displays detailed cooking instructions and media.
* **History/Saved Views:** Dedicated screens for users to revisit past searches and favorite recipes.
* **Auth Views:** Clean interfaces for Login and Registration.

### 3. Controllers (The Bridge)
* **HomeController:** The "brain" of the app. It coordinates the `LifecycleScope` for image processing, triggers `runObjectDetection`, and updates the View with recipe results.
* **RecipeHistoryController:** Manages the retrieval of snapshots from the database to populate the user's history list.
* **AuthController:** Validates user input and communicates with Firebase for secure access.
<img width="2401" height="2225" alt="CookSmart_Class-Diagram drawio" src="https://github.com/user-attachments/assets/8dc9b310-6873-446f-a0ef-dd0620f7f26f" />

---

## ✨ Key Features

* **AI Ingredient Detection:** Snap a photo of any ingredient (e.g., a tomato or chicken breast) to identify it automatically.
* **Smart Recipe Generation:** Get a list of recipes that specifically feature your identified ingredient.
* **User Authentication:** Secure login and registration powered by Firebase.
* **Recipe History:** Never lose a great meal idea; the app automatically tracks your recently viewed recipes.
* **Favorites & Saved Diagrams:** Save specific recipe flows or diagrams for quick access later.
* **Profile Management:** Customize user details and manage saved content from a centralized profile hub.

---

## 🛠 Tech Stack

* **Language:** Kotlin
* **Architecture:** Model-View-Controller (MVC)
* **Backend:** Firebase Auth & Realtime Database
* **Image Processing:** Bitmaps & Object Detection API
* **UI:** Material Design Components, RecyclerViews, and ViewGroups
