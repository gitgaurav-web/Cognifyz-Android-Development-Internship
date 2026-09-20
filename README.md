# 📱 Cognifyz Android Development Internship Projects

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Language-Java-ED8B00?logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Build-Gradle%208.13-02303A?logo=gradle&logoColor=white)
![SQLite](https://img.shields.io/badge/Database-SQLite-003B57?logo=sqlite&logoColor=white)
![Material Design 3](https://img.shields.io/badge/UI-Material%20Design%203-757575)

Welcome to the comprehensive Android Development repository created during my internship tenure at **Cognifyz IT Solutions Pvt. Ltd.**  
This repository contains production-ready implementations for all **8 tasks across 4 progression levels**, unified into a **Master Showcase App** alongside organized standalone level directories.

---

## 👨‍💻 Developer Profile
* **Intern:** Gaurav Kumar
* **Email:** [ama.gauravkumar@gmail.com](mailto:ama.gauravkumar@gmail.com)
* **Role:** Android Development Intern
* **Company:** Cognifyz IT Solutions Pvt. Ltd.
* **Duration:** 31/08/2026 to 01/10/2026

---

## 🎯 Task Breakdown & Features

| Level | Task | Title | Description | Key Tech Stack |
|:---:|:---:|:---|:---|:---|
| **Level 1** | **Task 1** | **Hello World App** | Responsive welcome screen layout displaying intern details and greetings. | MaterialCardView, ConstraintLayout |
| **Level 1** | **Task 2** | **Button Interaction** | Interactive button click counter with real-time `Toast` popups and counter reset. | OnClickListener, Toast Notifications |
| **Level 2** | **Task 3** | **List Display** | Dynamic topic display using custom `ArrayAdapter` with item click listeners. | ListView, ArrayAdapter |
| **Level 2** | **Task 4** | **Basic UI Layout** | Multi-activity screen passing parameters using Explicit `Intent` extras. | Explicit Intents, Bundle Extras |
| **Level 3** | **Task 5** | **Fetch & Display Data** | Fetches live data via online **REST API** using background threads (`ExecutorService`). | RecyclerView, HttpURLConnection, JSON Parsing |
| **Level 3** | **Task 6** | **Simple Form** | Live form validation (`TextWatcher`), Material `DatePickerDialog`, and push notifications. | TextWatcher, Regex Validation, DatePickerDialog |
| **Level 4** | **Task 7** | **SQLite Database** | Full CRUD database management with **live search filter**, edit modal dialog, and **CSV export**. | SQLiteOpenHelper, Full CRUD, CSV Export |
| **Level 4** | **Task 8** | **Smooth Navigation** | Seamless screen transitions with custom XML **Slide** and **Fade** animations. | overridePendingTransition, Custom XML Animations |

---

## 🔥 Production Compliance & Pro Max Features

1. 🎨 **Custom App Name & Branded Launcher Logo (`ic_cognifyz_launcher`):**
   * High-definition vector Cognifyz icon with modern geometry replacing default Android placeholder icons.
2. 🔐 **Authentication & Registration Gateway (`LoginActivity`):**
   * Sign In / Sign Up toggle with input validation, session persistence via `SessionManager`, and a 1-tap `⚡ Fast Evaluation: Continue as Intern Gaurav` button for instant reviewer access.
3. ⚙️ **Settings & Intern Profile (`SettingsActivity`):**
   * Intern details card, dynamic Dark / Light theme toggle, push notification preferences, and safe logout.
4. 🔒 **In-App Legal Modals (Privacy Policy & Terms):**
   * Built-in dialogs explaining data safety (100% offline local SQLite storage, no GPS/contact tracking).
5. ✉️ **Contact & Help Support:**
   * Direct email intent launcher to `support@cognifyz.com` and one-tap access to the official portal (`www.cognifyz.com`).
6. 🌐 **Resilient Error Handling & Retry:**
   * Detects network dropouts before API calls and displays interactive Snackbars with a **RETRY** action.
7. 📂 **Empty States & Loading Indicators:**
   * Dedicated empty state views with descriptive icons when SQLite database has 0 records or during list filtering, plus indeterminate progress bars during network fetch.
8. 🛡️ **Minimal, Genuine Permissions:**
   * Strictly requests essential capabilities only (`INTERNET`, `ACCESS_NETWORK_STATE`, `VIBRATE`, `POST_NOTIFICATIONS`).
9. 🤖 **In-App Smart AI Assistant ("Cognifyz AI Bot"):**
   * Built-in conversational Android tutor answering technical architecture questions (Lifecycle, SQLite, REST APIs, Intents, Kotlin vs Java) with realistic typing delays.
10. 📄 **SQLite CSV Data Export:**
    * One-click export of local SQLite records to CSV format, shareable via WhatsApp, Gmail, or Google Drive.
11. 💻 **In-App Code Inspector:**
    * Tapping "View Code" on any task opens a formatted dialog showing the underlying Java and XML implementation directly in the running app!

---

## 📁 Repository Structure

```
├── Cognifyz_Android_Internship_App/   # Master Runnable Android Studio Project
│   ├── app/
│   │   ├── src/main/java/com/cognifyz/internship/
│   │   │   ├── ai/                   # AI Assistant
│   │   │   ├── database/             # SQLiteOpenHelper & Database operations
│   │   │   ├── level1/ to level4/    # Level-wise Activities
│   │   │   ├── model/ & adapter/     # Models and RecyclerView Adapters
│   │   │   └── util/                 # NotificationHelper & NetworkUtils
│   │   └── src/main/res/             # Layouts, anim, values, drawables
│   ├── build.gradle
│   └── settings.gradle
├── Level_1_Beginner/                 # Standalone files for Level 1 (Task 1 & 2)
├── Level_2_Intermediate/             # Standalone files for Level 2 (Task 3 & 4)
├── Level_3_Advanced/                 # Standalone files for Level 3 (Task 5 & 6)
├── Level_4_Expert/                   # Standalone files for Level 4 (Task 7 & 8)
├── PROJECT_DOCUMENTATION.md          # Technical documentation & project report
├── LINKEDIN_POST_TEMPLATE.md         # Template for LinkedIn video post
└── Cognifyz_Android_Internship_Gaurav_Kumar.zip # Final submission archive
```

---

## 🚀 How to Run the Project

1. **Clone the repository:**
   ```bash
   git clone https://github.com/gitgaurav-web/Cognifyz-Android-Development-Internship.git
   ```
2. **Open in Android Studio:**
   * Launch Android Studio -> Click **Open**.
   * Select `Cognifyz_Android_Internship_App`.
3. **Run on Device or Emulator:**
   * Wait for Gradle Sync to complete.
   * Connect an Android device (via USB Debugging) or start an Emulator.
   * Click the green **Run (▶)** button!

---

## 📜 License & Acknowledgments
Developed as part of the **Android Development Internship** at **Cognifyz IT Solutions Pvt. Ltd.**  
All tasks designed and implemented adhering to official Cognifyz guidelines.
