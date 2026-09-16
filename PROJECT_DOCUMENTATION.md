# Cognifyz Android Development Internship - Super-Advanced (Pro Max) Project Documentation

**Intern Name:** Gaurav Kumar  
**Email:** ama.gauravkumar@gmail.com  
**Company:** Cognifyz IT Solutions Pvt. Ltd.  
**Role:** Android Development Intern  
**Tenure:** 31/08/2026 to 01/10/2026  
**Submission Form:** https://forms.gle/c3QZCheEySRDPV6V6  

---

## 1. Executive Summary
This project represents a comprehensive, senior-grade Android application engineered for the **Cognifyz IT Solutions Android Development Internship**.
All **8 tasks across 4 levels** have been completed and elevated with high-end modern features:
- **?? In-App Smart AI Assistant ("Cognifyz AI Bot"):** Conversational Android tutor that explains tasks, answers technical architecture questions, and provides live code hints.
- **?? Android System Push Notifications (NotificationManager):** System notifications triggered on task submissions and SQLite database operations.
- **?? SQLite Database Manager with CSV Export:** Full CRUD, real-time live search filter, edit modal dialog, and 1-click CSV data export to WhatsApp/Google Drive/Email.
- **?? Real-Time Network Connectivity Monitor (ConnectivityManager):** Dynamic status indicator showing Online vs Offline Mode.
- **?? In-App Code Inspector:** Inspect underlying Java and XML code directly inside the running app.
- **?? Dynamic Dark / Light Mode Switcher:** Saved locally via Android SharedPreferences.
- **?? Live REST API Integration:** Background worker threads (ExecutorService) fetching dynamic JSON data.
- **?? Real-time Live Form Validations:** Keystroke validation (TextWatcher) with Material DatePickerDialog.

---

## 2. Directory Structure

`
e:\intership/
+-- ANDROID DEVELOPMENT.pdf
+-- PROJECT_DOCUMENTATION.md
+-- LINKEDIN_POST_TEMPLATE.md
+-- Generate_Submission_ZIP.ps1
+-- Cognifyz_Android_Internship_Gaurav_Kumar.zip   <-- Direct upload to Google Form
+-- Level_1_Beginner/
¦   +-- README.md
¦   +-- Task1_HelloWorld/
¦   +-- Task2_ButtonInteraction/
+-- Level_2_Intermediate/
¦   +-- README.md
¦   +-- Task3_ListDisplay/
¦   +-- Task4_BasicUILayout/
+-- Level_3_Advanced/
¦   +-- README.md
¦   +-- Task5_FetchDisplayData/ (Live REST API + RecyclerView)
¦   +-- Task6_SimpleForm/ (Realtime Validations + DatePicker + Push Notifications)
+-- Level_4_Expert/
¦   +-- README.md
¦   +-- Task7_SQLiteDatabase/ (Full CRUD + Search + Edit + CSV Export + Push Notifications)
¦   +-- Task8_SmoothNavigation/ (Explicit Intents + Animations)
+-- Cognifyz_Android_Internship_App/     <-- Master Runnable Project in Android Studio
`

---

## 3. Tasks & Architecture Overview

### Level 1: Beginner
- **Task 1: Hello World App:** Material Card greeting with custom intern details.
- **Task 2: Button Interaction:** OnClickListener, counter, reset feature, and instant Toast feedback.

### Level 2: Intermediate
- **Task 3: List Display:** ListView powered by ArrayAdapter showcasing core Android modules with interactive click notifications.
- **Task 4: Basic UI Layout:** Multi-Activity navigation passing user parameters through Explicit Intent extras.

### Level 3: Advanced
- **Task 5: Fetch and Display Data (Live REST API):**
  - Online REST endpoints (HttpURLConnection via ExecutorService).
  - Real-time JSON parsing into TechSkill model objects.
  - Dynamically updates the RecyclerView without blocking the main UI thread.
- **Task 6: Simple Form (Live Validations, DatePicker & Push Notifications):**
  - Real-time TextWatcher validators for Name, Email regex, and 10-digit Phone numbers.
  - Material DatePickerDialog allowing dynamic selection of internship start dates.
  - Triggers real Android system push notifications upon submission.

### Level 4: Expert
- **Task 7: SQLite Database (Full CRUD, Search Filter, Edit Modal & CSV Export):**
  - **Create:** Inserts user records into SQLite and fires push notification.
  - **Read:** Live list rendering in RecyclerView.
  - **Search:** Dynamic real-time search box filtering records instantly by name or email.
  - **Update:** Tapping any card opens a custom Edit Dialog to update values directly in SQLite.
  - **Delete:** Remove records with instant list and count refresh.
  - **Export:** Converts SQLite database records into CSV and shares via native Share Sheet.
- **Task 8: Implement Navigation (Smooth Custom Transitions):**
  - Custom XML animations (slide_in_right.xml, slide_out_left.xml, ade_in.xml, ade_out.xml).
  - Seamless navigation and back-stack handling.
