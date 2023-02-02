# Design Document
**Author**: Wenjuan Li
## 1 Design Considerations
### 1.1 Assumptions
- The application is intended to run on Android Studio and Emulator
- For now we assume the launch of app doesn't require specific authentication/authorization

## 2 Architectural Design
**1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).**

   > To realize this requirement, I added a options String list attribute in MainMenu Class, which would allow user to select different options from Main Menu when selectOption() method is called. In addition, other classes created would allow user to perform actions such as enter current job or job offers, update current job, adjust the comparison settings, compare job offers.

**2. When choosing to enter current job details, a user will:**
**a) Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of listed information mentioned in requirement.**
**b) Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.**

   > To realize the requirement a), I created a Job class which consists all the attributes listed in requirement. User can create current job by calling the addJob() method which inherited from Job Class to CurrentJob class. Besides, user can edit and update the current job by calling updateCurrentJob() method in CurrentJob class.
   To realize the requirement b), different methods can be called to save job, cancel or return to main menu. 
   
**3. When choosing to enter job offers, a user will:**
**a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.**
**b. Be able to either save the job offer details or cancel.**
**c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).**

   > To realize this requirement, considering the fields of offer are same as current job, and save or cancel method can be reused. Instead of repeating the class attributes and methods. I chose to create JobOffer class which would inherit the attributes and methods from Job class. However, here I added addJobOffer() method to allow user to add another offer be more specific, which might be eliminated based on later architecture enhancement.
   Compare the offer method will be handled in a different class which will be covered in following requirements considering the dependencies involved and complexity.

**4. When adjusting the comparison settings, the user can assign integer weights to:
Yearly salary
Yearly bonus
Allowed weekly telework days
Retirement benefits
Leave time
If no weights are assigned, all factors are considered equal.**

   > To realize this requirement, ComparisonSetting Class was created, where user can pass the particular weight to the concerned attributes. The AdjustedJob Class would return the adjusted attributes after weight is applied.
   
**4. When choosing to compare job offers, a user will:
a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.**

   > To realize this requirement, JobComparison Class includes the jobOffer list, rank job score can be retrieved by calling the JobRanking Class. A Boolean attribute currentJobExists will determine if current job is present.
   
**b. Select two jobs to compare and trigger the comparison.**
   > To realize this requirement, jobOfferComparison() method would be called to trigger the comparison. 
   
**c. Be shown a table comparing the two jobs, displaying, for each job:**
    - Title
    - Company
    - Location
    - Yearly salary adjusted for cost of living
    - Yearly bonus adjusted for cost of living
    - Allowed weekly telework days
    - Retirement benefits (as percentage matched)
    - Leave time
    
> The adjusted information can be retrieved from AdjustedJob class, where jobInfoAdjust() method can be called for adjusted job info after weight retrieved from ComparisonSetting and applied.
    
**d. Be offered to perform another comparison or go back to the main menu.**
> To realize this requirement, user would be offered to perform another comparison by calling anotherComparison() method.

**When ranking jobs, a job's score is computed as the weighted sum of:
AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)**
> To realize this requirement, I have created JobRanking Class, where the calculation can be conducted. Each element can be retrieved from AdjustedJob Class via the get methods.

**7. The user interface must be intuitive and responsive.**
> This is not represented in my design, as it will be handled entirely within the GUI implementation.   

**8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).**
> This is not represented in my design, as this is just assumption for implementation.
