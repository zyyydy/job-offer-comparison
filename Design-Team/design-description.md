# Design Document
**Authors**: Vincent Hu, Sarah Kidd, Wenjuan Li, Ying Zhou
## Requirements
**1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).**

   > To realize this requirement, we added the _Application_ class which can compare job offers. It also connects with the _CurrentJob_, _JobOffer_, and _Comparison_ classes so that users can update current job, add job offers, and adjust comparison settings.

**2. When choosing to enter current job details, a user will:**
**a) Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of the listed information (Title, Company, Locatin, Cost of living in the location, Yearly salary, Yearly bonus, Allowed weekly telework days, Retirement benefits, Leave time).**

   > To realize this requirement, we created a _JobDetail_ class to track current job details and job offer details. The _JobDetail_ class has attributes that will store the user input. The title, company, and other attributes will be stored within the _Job_ class. The `title`, `company`, and `location` attributes are stored as strings, while the `costOfLiving`, `remote`, `salary`, `bonus`, `benefits`, and `leave` attributes are stored as integers.

**b) Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.**

   > This is not represented in our design, as it will be handled entirely within the GUI implementation.

**3. When choosing to enter job offers, a user will:**
**a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.**
**b. Be able to either save the job offer details or cancel.**
**c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).**

   > To realize this requirement, we added the _JobOffer_ class which will inherit from the _JobDetail_ class. This is because the job offer details are the same ones listed above for the current job, according to the requirements. The assumption here is that any new field, like "team size" would need to be added to both the current job and all job offers. If they are not available for both the current job and job offers, they will not be comparable.
   The interface with which the user enters the details, the ability to save or cancel the job offer, and to the ability to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details, will be handled within the GUI implementation.

**4. When adjusting the comparison settings, the user can assign integer weights to: (Yearly salary, Yearly bonus, Allowed weekly telework days, Retirement benefits, Leave time). If no weights are assigned, all factors are considered equal.**

   > To realize this requirement, we added the _Comparison_ class and the ability for users to adjust the weights of the variables using the `updateWeights()` method. If no weights are assigned, the default value will be used.

**5. When choosing to compare job offers, a user will:**
**a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.**

   > To realize this requirement, the _Comparison_ class contains `calculateRank()`. This will be called with `sortJobOffers()` and `displayJobs()` from the _Application_ class. The `calculateRank()` method utilizes the weights from the _Comparison_ class to compute a score for each job. The `sortJobOffers()` will rank all jobs and return the value to be displayed by the `displayJob()` method.

**b. Select two jobs to compare and trigger the comparison.**

   > This is not represented in our design, as it will be handled entirely within the GUI implementation. 

**c. Be shown a table comparing the two jobs, displaying, for each job: (Title, Company, Location, Yearly salary adjusted for cost of living, Yearly bonus adjusted for cost of living, Allowed weekly telework days, Retirement benefits (as percentage matched), Leave time).**

   > To realize this requirement, method `displayJob()` will be called for both jobs that the user selects. This method will now display all values detailed in the _JobDetail_ class for the user to see.

**d. Be offered to perform another comparison or go back to the main menu.**

   > This is not represented in our design, as it will be handled entirely within the GUI implementation. 

**6. When ranking jobs, a job's score is computed as the weighted sum of: AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)**

   > To realize this requirement, the class _Comparison_ contains the `calculateRank()` method that will calculate score for each job.

**7. The user interface must be intuitive and responsive.**

   > This is not represented in our design, as it will be handled entirely within the GUI implementation. 

**8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).**

   > To realize this requirement, there is no indication in our design that requires communication outside of the application or saving data to a database or device.
