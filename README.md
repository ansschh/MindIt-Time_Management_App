# Mind It - Time Management App

**Mind It** is an NLP-powered Android time management app that simplifies task organization by transcribing voice input into tasks and events. These are seamlessly added to your Google Calendar, reducing the manual effort of keeping track of deadlines, meetings, and daily tasks.

## Features

- **NLP-Powered Voice Transcription**: Convert voice input into tasks, deadlines, and meetings.
- **Google Calendar Integration**: Automatically fetch transcribed tasks and events into your Google Calendar.
- **Google Meet Integration**: Schedule and link meetings directly from the app.
- **Task Prioritization**: Organize tasks by priority (small or significant), set goals, and track progress.
- **Reminders & Notifications**: Real-time notifications for tasks, meetings, and deadlines.
- **Simple User Interface**: Intuitive design for easy task, deadline, and meeting management.

## Technology Stack

- **Java**: Android development
- **Firebase**: Real-time data storage and user management
- **Firestore**: Task metadata storage
- **Google Cloud Speech-to-Text API**: For converting voice input to text
- **Google Calendar API**: For syncing tasks with Google Calendar
- **Google Meet API**: For scheduling and linking meetings
- **OpenAI GPT-3/4 API**: For task classification and NLP-based processing

## Setup and Installation

### Prerequisites

- [Android Studio](https://developer.android.com/studio) installed on your machine
- [Google Cloud API Keys](https://cloud.google.com/) for Speech-to-Text, Calendar, and Meet
- Firebase setup for real-time storage

### Clone the Repository

```bash
git clone https://github.com/ansschh/MindIt-Time_Management_App.git
cd MindIt-Time_Management_App
```

### Android Setup
1. Open the project in Android Studio.
2. Sync the Gradle project and ensure all dependencies are installed.
3. Add your Firebase project configuration file (`google-services.json`) to the `app/` directory.
4. Add the following API keys to your project's `gradle.properties` or as environment variables:

```env
GOOGLE_CALENDAR_API_KEY=your-google-calendar-api-key
GOOGLE_CLOUD_SPEECH_API_KEY=your-google-cloud-speech-api-key
FIREBASE_API_KEY=your-firebase-api-key
OPENAI_API_KEY=your-openai-api-key
```

5. Connect your Android device or start an emulator.
6. Build and run the app.

## Google API Setup

1. Enable the following APIs in your Google Cloud Console:

    - Google Calendar API
    - Google Cloud Speech-to-Text API
    - Google Meet API

2. Obtain the API keys and add them to your project as described in the previous section.

## Firebase Setup

1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Create a new Firebase project.
3. Enable Firestore and Firebase Real-Time Database for task storage.
4. Add your `google-services.json` file to the Android project.

## Usage

1. **Add Tasks via Voice Input**: Use the microphone button to record tasks. The app will transcribe the input and convert it into tasks or meetings.
2. **Automatic Calendar Updates**: Once tasks are created, they are synced with your Google Calendar.
3. **Meeting Scheduling**: Add meetings through voice or text input, and they will be synced with Google Meet and Calendar.
4. **Task Management**: Organize your tasks by priority and set deadlines.
5. **Track Progress**: Visualize task progress and completion within the app.

## Contributing

We welcome contributions to improve the app. Please follow these steps to contribute:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a pull request.
