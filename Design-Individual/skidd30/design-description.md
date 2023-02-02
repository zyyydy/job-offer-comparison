## Requirements:
1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).
>To realize this requirement, I added `chooseDecision()` to the JobComparisionApp class where the 4 options will be shown to the user (and enabled/disabled when required). 
>
2. When choosing to enter current job details, a user will:
 - Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of (Title, Company, Locatin, Cost of living in the location, Yearly salary, Yearly bonus, Allowed weekly telework days, Retirement benefits, Leave time):
 >To realize this requirement, I have added the CurrentJob class where it will take in the user's input for all these fields. These fields will be retrieved from the JobDescription Interface.
 >
 - Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
 >To realize this requirement, I have a method called `updateOrCancel()` method within the CurrentJob class.
 >
3. When choosing to enter job offers, a user will:
 - Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
 >To realize this requirement, I have class JobOffer that will display all fields that are also seen in the CurrentJob class, because the JobOffer class will also utilize the JobDescription Interface.
 >
 - Be able to either save the job offer details or cancel.
 >To realize this requirement, the JobOffer lcass has a `saveOrCancel()` method to either save the job offer details within the `jobOffers[]` array or cancel and choose to do something else.
 >
 - Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
 >To realize this requirement, the `saveOrCancel()` method within the JobOffer class will give the user the option to either enter another offer, return to the main menu, or go compare the current/saved offer (if saved) to the current job details (if present).
 >
4. When adjusting the comparison settings, the user can assign integer weights to (Yearly salary, Yearly bonus, Allowed weekly telework days, Retirement benefits, Leave time).
If no weights are assigned, all factors are considered equal.
>To realize this requirement, I have added class ComparisonSetting. This class will take input from the user to set attributes `yearlySalary`, `yearlyBonus`, `weeklyTeleDays`, `retireBenefits`, and `leaveTime`. If no weight are assigned at the start of the application, method `setDefaultSettings()` will run from the JobComparisonApp class.
>
5. When choosing to compare job offers, a user will:
 - Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
 >To realize this requirement, the CompareJobOffer class contains method `displayJobs(currJob, jobOffers[])` that will display all job offers and the current jobs. Within the `displayJobs(currJob, jobOffers[])` method the `calculateRank(job : JobDescription)` will be called for each job offer/current job. That method will calcuate the rank for that job and return the value to be displayed by the `displayJobs` method.
 >
 - Select two jobs to compare and trigger the comparison.
 >To realize this requirement, method `compareJobs(JobDescription, JobDescription)` will be called once the user selects 2 differents jobs to compare.
 >
 - Be shown a table comparing the two jobs, displaying, for each job (Title, Company, Location, Yearly salary adjusted for cost of living, Yearly bonus adjusted for cost of living, Allowed weekly telework days, Retirement benefits (as percentage matched), Leave time).
 >To realize this requirement, method `compareJobs(JobDescription, JobDescription)` will be called once the user selects 2 differents jobs to compare. This method will now display all values within each `JobDescription` for the user to see.
 >
 - Be offered to perform another comparison or go back to the main menu.
 >To realize this requirement, the CompareJobOffer class has the `nextTask()` method that will offer the user to perform another comparison or to return to the main menu.
 >
6. When ranking jobs, a job’s score is computed as the weighted sum of (equation).
>To realize this requirement, class CompareJobOffer contains the `calculateRank(job : JobDescription)` method that will be called within the `displayJobs(currJob, jobOffers[])` method when it will iterate through each job to calcuate each rank.
>
7. The user interface must be intuitive and responsive.
>This is not represented in my design, as it will be handled entirely within the GUI implementation.
>
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
>To realize this requirement, there is no indication in my design that requires communication outside of the application or saving data to a database or device.
>