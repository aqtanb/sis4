Task description.
Students are required to implement a simple Android application that retrieves data from any
publicly available backend API and displays it on the screen. The goal is to demonstrate
understanding of asynchronous network calls and basic UI state management.
Requirements:
● Use Retrofit for making HTTP requests.
● Log all requests and responses using OkHttp logging interceptor, so that network
activity is visible in Logcat.
● Execute network calls with Kotlin Coroutines.
● Display the loaded data on the screen (for example, a list of items, a single text
block, or any meaningful representation). The UI design is not critical, but the data
should be displayed clearly.
● Handle different UI states:
○ Show a loading indicator (e.g., ProgressBar) while data is being fetched.
○ Show an error message if the request fails.
○ Show the content when the data loads successfully.
If you use a concept not covered in class (e.g., ViewModel, LiveData, Jetpack
Compose, etc.) and cannot explain it during review, points will be deducted for each
unexplained element.
Each student must answer one theoretical question related to networking or coroutines.
Possible topics include:
- Difference between suspend functions and callback-based networking
- How Dispatchers.IO works
- Why network calls must not be executed on the main thread