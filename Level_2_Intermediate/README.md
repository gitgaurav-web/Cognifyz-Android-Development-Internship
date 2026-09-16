# Cognifyz Technologies - Level 2: Intermediate Tasks

**Candidate Name:** Gaurav Kumar  
**Domain:** Android Development Intern  
**Company:** Cognifyz IT Solutions Pvt. Ltd.

---

## ?? Task 3: List Display
- **Objective:** Develop an app that displays a list of items using an adapter.
- **Implementation:**
  - ListDisplayActivity.java: Creates a static array of Android development curriculum modules and binds them using ArrayAdapter<String>.
  - ctivity_list_display.xml: ListView container with divider styling.
  - Interactive item click listener showing selected module via Toast feedback.

---

## ?? Task 4: Basic UI Layout (Multi-Activity)
- **Objective:** Build an app with multiple activities and a clean UI layout in XML.
- **Implementation:**
  - BasicUiLayoutActivity.java: Form input activity capturing Name, Email, and Domain using TextInputEditText.
  - Passes user data to ProfileDetailsActivity.java using explicit Intent extras (putExtra).
  - ProfileDetailsActivity.java: Displays neatly formatted profile card with back navigation.
