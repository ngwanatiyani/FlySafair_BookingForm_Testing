# 📝 FlySafair Booking Test Report

## 📅 Date: February 05, 2025

### 🐞 Test Summary

- **Total Tests Run**: 5
- **Tests Passed**: 0
- **Tests Failed**: 5


### 🚨 Test Failure Details

#### Test Case: `testBooking`

- **Status**: ❌ Failed
- **Reason**: The test failed due to dynamic element IDs on the FlySafair website. Each time the page is reloaded, the elements are assigned new IDs, causing the WebDriver to be unable to locate the required elements. **Point of correction: I should have utilize xPath or css selectors to retrieve web elements**
