1. The main menu is not represented in my design as it will be handled within the GUI implementation. The features: (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers, will be handled by the fields and methods within the Job and Comparison classes as described below.

2. To realize the *enter current job* requirement, I created a Job class to track current job details. The Job class has fields that will store the user input. The title, company, etc. will be stored in fields within the Job class. The title, company, and location fields are stored as strings, while the costOfLiving, remote, salary, bonus, benefits, and leave fields are stored as integers. The interface with which the user enters the details, and the ability to save or cancel the current job details will be handled within the GUI implementation as called from the Main class.

3. To realize the *enter job offer* requirement, I added a boolean "current" indicator to my Job class because the job offer details "are the same ones listed above for the current job". The assumption here is that any new field, like "team size" would need to be added to both the current job and all job offers. If they are not available for both the current job and job offers, they will not be comparable.
The interface with which the user enters the details, the ability to save or cancel the job offer, and to the ability to (1) enter another offer, (2) return to the main menu, or (3) compare the
offer with the current job details, will be handled within the GUI implementation called from the Main class.

4. To realize the *adjust comparison settings* requirement, I created a Comparison class to store the weights assigned by the user. All of the weights are stored as integers as specified by the requirements. The Comparison class uses the updateSettings method to store user inputs within the fields.

5. To realize the *display and compare* requirements, I added a readJobs method to the Main class to list all jobs. The filtered display, such as showing only Title and Company vs showing all attributes, will be handled within the GUI implementation.
To realize the *rankings* requirement, I added a score method in the Comparison class. The readJobs method will also use private helper methods getAllJobs and sortJobs. These methods will act upon the aggregation of Jobs. The ability to select two of the jobs and display their details, offer another comparison, or go back to the main menu, will be handled within the GUI implementation.

6. The *job score* requirement will be handled with the score method in the Comparison class. The score method takes a Job as the parameter and uses the Job getters along with its own weight fields to calculate each job's score.

7. The intuitive and responsive user interface is not represented in my design, as it will be handled by the GUI implementation. The GUI class is not represented, as per the requirements.

8. Not a requirement.
