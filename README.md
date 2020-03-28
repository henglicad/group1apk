The TRU Eng Planner app is made to help TRU Software Engineering students plan their semesters out and stay on track for graduation. The app is easy to use and features an attractive dark mode to make it easier on the users’ eyes.

      App Features
- Quick View to see TRU’s ideal software engineering schedule
- Make A Plan automatic valid semester scheduler based on year, semester, and preferred number of courses per semester
- All courses markable as taken or not taken so that the schedule reflects each student’s current status
- Easy viewing of course details, including prerequisites, post requisites, and semesters offered
- Course database downloadable into a text file on the phone
- Protected feature to upload a new valid database in case of program changes
- Manual schedule changes supported

"Build, Install & Use.docx" is the reference document that can be followed for instructions on how to build, install, and use the TRU Eng Planner application.

      Test Plan
- Conducted code reviews to ensure there are no logic traps done at pull request
- Ran alpha test periodically
- Created unit, component, and integration tests on Android Studio
- Generated reports to see verify adequate test coverage
- Beta testing before each release


      App Structure:

The source code of the TRU Eng Planner app is separated into two parts, the java and xml files that contain the source code of the application and the tests for those individual files as well as the integration and unit tests. The Java source code files are segmented further as shown in the File Organization figure.  The resources folder has xml files that are separated, based on their purpose and function, into four separate folders:

| Folder | Description |
| --- | --- |
| drawable | Contains files for appearance of the buttons  |
| layout | Layout files for screens or adapters to use |
|  menu   |  Contains files that define structure of the menu   |
|  values   |  Contains files for colors, dimensions, strings and styles   |

The folder ‘java/com/group1/EngPlan’ contains the java files with the source code which contains a further four folders:

| Folder | Description |
| --- | --- |
| Adapters | Contains the adapter files  |
| Backend | Contains the java files which handle the backend operations |
|  MakeAPlan   |  Contains files that implement this feature   |
|  ManualScheduling    |  Contains the java files that handle this feature   |

The remaining java files are responsible for the other functionalities of the App. This  includes the functionalities of the main screen, handling the course details, handling the buttons on the main screen and deploying the splash screen of the app.  The file structure of the app is shown in the File Organization figure.

