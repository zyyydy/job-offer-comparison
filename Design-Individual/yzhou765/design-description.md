***1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet1).***

To realize this requirement, a __System__ class is added and this class consists of four operations: __editCurrentJob(), enterJobOffer(), adjustComparisonSetting(), compareJobOffer()__. They each relate to __CurrentJob, JobOffer, Comparison, ComparisonSetting__ class.

A __System__ may have or may not have one __CurrentJob__. A __System__ can have zero to many __JobOffer__ and therefore, it can have zero to many __Comparison__. A __System__ only consists of one __ComparisonSetting__.

***2. When choosing to ​enter current job details, a​ user will:
    a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
        i. Title
        ii. Company
        iii. Location (entered as city and state)
        iv. Cost of living in the location (expressed as an ​index​)
        v. Possibility to work remotely (expressed as the number of days a week one could work remotely, between 1 and 5)
        vi. Yearly salary
        vii. Yearly bonus
        viii. Retirement benefits (as percentage matched)
        ix. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
    b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.***

***3. When choosing to ​enter job offers, a​ user will:
    a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
    b. Be able to either save the job offer details or cancel.
    c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).***

A supperclass __Job__ is created with _title(string), company(string), location(string), cost(int), workRemote(int), salary(float), bonus(float), retirementBenefit(float), leaveTime(int)_. The __Job__ class can edit job details (__editJob__), and then save the detail (__saveJob__). Or it can erase all the edits and exit (__cancelEdit__). Also it can return to the main menu(__returnMain__). 

It has two subclasses __CurrentJob__ and __JobOffer__. Each subclass inherits all attributes and functions __Job__ class has. In addition, __CurrentJob__ is able to verify if this is the first to use. If yes, it allows to add current job (__addCurrentJob__). In contrast, __JobOffer__ is able to add jobs as many as it can (__addJobOffer__).

***4. When ​adjusting the comparison settings, ​the user can assign integer ​weights ​to:
    a. Possibility to work remotely
    b. Yearly salary
    c. Yearly bonus
    d. Retirement benefits
    e. Leave time
If no weights are assigned, all factors are considered equal.***

A __ComparisonSetting__ class is added with five attributes _weightWorkRemote(int), weightSalary(int), weightBonus(int), weightRetirementBonus(int), weightLeaveTime(int)_. Each attribute has a default value of 1 and they are adjustable (__adjustSetting()).

***5. When choosing to ​compare job offers, a​ user will:
    a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
    b. Select two jobs to compare and trigger the comparison.
    c. Be shown a table comparing the two jobs, displaying, for each job:
        i. Title
        ii. Company
        iii. Location
        iv. Yearly salary adjusted for cost of living
        v. Yearly bonus adjusted for cost of living
        vi. Retirement benefits (as percentage matched)
        vii. Leave time
    d. Be offered to perform another comparison or go back to the main menu.***

A __Comparison__ class is added with the following operations: 
__returnMain__; it will bring back the main panel.
__computeRank__: it will calculate rank based on __CurrentJob__/__JobOffer__ and __ComparisonSetting__.
__displayJob__: it will display _title, company, rank, isCurrent_ of all jobs. 
__select2Jobs__: it allows to pick up two jobs from the entire list.
__display2Jobs__: it will display the _title, company, location, adjustedSalary, adjustedBonus, retirementBonus, leaveTime_. This operation will need __adjustByCost__ to get _adjustedSalary, adjustedBonus_.

The last four operations will use __CurrentJob, JobOffer, RankingAlgorithm, ComparisonSetting__.

***6. When ranking jobs, a job’s score is computed as the ​weighted​ sum of:
    AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
    where:
        AYS = yearly salary adjusted for cost of living 
        AYB = yearly bonus adjusted for cost of living 
        RBP = retirement benefits percentage
        LT = leave time
        RWT = remote work days per week
        The rationale for the RWT subformula is:
            a. value of an employee hour = (AYS / 260) / 8
            b. commute hours per year (assuming a 1-hour/day commute) = 1 * (260 - 52 * RWT)
            c. therefore ​commute-time cost =​ ​(260 - 52 * RWT) * (AYS / 260) / 8
        For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:
            2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)***

__RankingAlgorithm__ class is added and  this is used by the __Comparison__ class to get the rank for each job.


***7. The user interface must be intuitive and responsive.
8. For simplicity, you may assume there is a ​single system​ running the app (no communication or saving between devices is necessary).***

This is not represented in my design, as it will be handled entirely within the GUI implementation.