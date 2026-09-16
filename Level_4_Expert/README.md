# Cognifyz Technologies - Level 4: Expert Tasks

**Candidate Name:** Gaurav Kumar  
**Domain:** Android Development Intern  
**Company:** Cognifyz IT Solutions Pvt. Ltd.

---

## ?? Task 7: Basic Database Usage (SQLite CRUD)
- **Objective:** Integrate a simple SQLite database into the app to store and retrieve data.
- **Implementation:**
  - DatabaseHelper.java: Subclasses SQLiteOpenHelper to manage table creation, versioning, insert, read, and delete operations.
  - SqliteDatabaseActivity.java: Interactive UI to input user records, store them in SQLite DB, and view them immediately in a RecyclerView.
  - Real-time delete operation and record count display.

---

## ?? Task 8: Implement Navigation (Smooth Transitions)
- **Objective:** Enhance the app with smooth navigation between screens using explicit intents and custom animations.
- **Implementation:**
  - NavigationActivity.java: Implements explicit intents passing custom payload messages to SecondScreenActivity.java.
  - Smooth animation transitions using overridePendingTransition(...):
    - Slide Animation: slide_in_right.xml and slide_out_left.xml
    - Fade Animation: ade_in.xml and ade_out.xml
  - Reverse transition animations when navigating back.
