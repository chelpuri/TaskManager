# Task Manager App

## JSON Entities

### Task
    {
	    "id": 31,
	    "title": "I am a task!",
	    "description": "This is the task description",
	    "deadline": "dd/mm/yyyy",
      "notes": [
        {
          "title": "random note title",
          "body": "I am the note body"
        },
        {
          "title": "random note title",
          "body": "I am the note body"
        },
        {
          "title": "random note title",
          "body": "I am the note body"
        }
      ],
      "completed": false
    }

## API Endpoints 

### `POST /tasks` 
Create a new task  

### `GET /tasks`
Get all tasks
Available filters - 
- `/articles?completed=true/false`

### `GET /tasks/{task_id}`
Get the details of a particular task including notes

### `PATCH /tasks/{task_id}`
Edit a task - Add / Remove notes from the task. Mark a task completed.

### `PATCH /tasks/{task_id}`

### `DELETE /tasks/{task_id}`
Delete a particular task
