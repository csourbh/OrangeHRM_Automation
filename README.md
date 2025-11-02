# 🧪 Orange HRM Automation Framework

## 📋 Project Overview
A comprehensive automation testing framework for **Orange HRM** using **Selenium WebDriver**, **TestNG**, and the **Page Object Model (POM)** design pattern.  
This framework supports **data-driven**, **modular**, and **maintainable** automated testing with detailed HTML reporting.

---

## 🎯 Test Scenarios Covered

### 🔐 Scenario 1: Login Module
**Objective:** Automate login and logout with multiple datasets.

- ✅ 5 datasets (valid + invalid) from Excel  
- ✅ Data-driven testing via Apache POI  
- ✅ Login & logout automation  
- ✅ Assertions for valid/invalid credentials  
- ✅ Screenshot capture for every step  
- ✅ Extent HTML Report generation  

### 👨‍💼 Scenario 2: Admin Module (POM)
**Objective:** Implement Page Object Model for Admin module features.

- ✅ POM design pattern  
- ✅ OOP principles applied  
- ✅ 4 test cases for Admin module  
- ✅ Search functionality testing  
- ✅ Dropdown and navigation validation  

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|-------------|----------|----------|
| Java | 11 | Programming language |
| Selenium WebDriver | 4.15.0 | Browser automation |
| TestNG | 7.8.0 | Testing framework |
| Maven | 3.6+ | Build automation |
| Apache POI | 5.2.4 | Excel data handling |
| Extent Reports | 5.1.1 | HTML reporting |
| WebDriverManager | 5.6.0 | Driver management |

---

## 📁 Project Structure

```plaintext
orange-hrm-automation/
├── src/test/java/
│   ├── com/orangehrm/tests/
│   │   ├── BaseTest.java
│   │   ├── LoginTest.java
│   │   └── admin/
│   │       └── AdminTest.java
│   ├── com/orangehrm/pages/
│   │   ├── BasePage.java
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   └── AdminPage.java
│   └── com/orangehrm/utils/
│       ├── ConfigReader.java
│       ├── ExcelReader.java
│       ├── ScreenshotUtil.java
│       └── ExtentReportManager.java
├── src/test/resources/
│   ├── testdata/
│   │   ├── LoginTestData.xlsx
│   │   └── AdminTestData.xlsx
│   └── config/
│       └── config.properties
├── screenshots/ (auto-generated)
├── extent-reports/ (auto-generated)
├── test-output/ (auto-generated)
└── pom.xml
