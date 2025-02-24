# Fleur User Guide 🌸

![Screenshot of product](Ui.png)

Fleur is a friendly **task manager chatbot** that helps you organize 
your **ToDos, Deadlines, and Events**. It allows you to manage your tasks 
efficiently via a simple **command-line interface** or a **graphical user interface (GUI)**.

## Commands

| **Action**        | **Command** | **Description** |
|-------------------|----------|----------------|
| **Add ToDo**      | `todo` | Adds a new ToDo task to your list. |
| **Add Deadline**  | `deadline` | Adds a new Deadline task with a due date. |
| **Add Event**     | `event` | Adds a new Event task with a start and end date. |
| **Mark as Done**  | `mark` | Marks a task as completed. |
| **Unmark Task**   | `unmark` | Marks a completed task as not done. |
| **Delete Task**   | `delete` | Removes a task from your list. |
| **List Tasks**    | `list` | Displays all tasks currently in your list. |
| **Find Task**     | `find` | Searches for tasks containing a specific keyword. |
| **Edit Task**     | `edit` | Modifies an existing task’s description. |
| **Save and Exit** | `bye` | Saves all tasks and exits the program. |

## Command Details

Below are the detailed descriptions, examples, and expected outputs for each command:

---

### Adding a ToDo Task
**Usage:** `todo <description>`

**Example:** `todo buy groceries`

**Expected Output:**

```
Bah, oui! I 'ave added zis todo task to your list: 
[T][ ] buy groceries 
Now you 'ave 1 task(s) in your list.
```
### Adding a Deadline Task
**Usage:** `deadline <description> /by <date>`

**Example:** `deadline submit report /by 22/05/2025`

**Expected Output:**

```
Bah, oui! I 'ave added zis deadline task to your list: 
[D][ ] submit report (by: Mar 22 2025) 
Now you 'ave 2 task(s) in your list.
```
### Adding an Event Task
**Usage:** `event <description> /from <start_date> /to <end_date>`

**Example:** `event midterms /from 05/03/2025 /to 06/03/2025`

**Expected Output:**

```
Bah, oui! I 'ave added zis event task to your list: 
[E][ ] midterms (from: Mar 5 2025 to: Mar 6 2025) 
Now you 'ave 3 task(s) in your list.
```
### Marking, Unmarking or Deleting a Task
**Usage:** 

`mark <task_number>`

`unmark <task_number>`

`delete <task_number>`

**Example:** `mark 1`

**Expected Output:**

```
Enchanté! I 'ave marked zis task as done: 
[T][X] buy groceries
```
**Example:** `unmark 1`

**Expected Output:**

```
Zut! I 'ave marked zis task as not done: 
[T][ ] buy groceries
```
**Example:** `delete 2`

**Expected Output:**

```
D'accord, I 'ave removed zis task from your list: 
[D][ ] submit report (by: Mar 22 2025) 
Now you 'ave 2 task(s) in your list.
```
### Listing Tasks
**Usage:** `list`

**Expected Output:**

```
'Ere are ze task(s) in your list:
[T][X] buy groceries
[E][ ] midterms (from: Mar 5 2025 to: Mar 6 2025) 
```
### Finding Tasks
**Usage:** `find <keyword>`

**Example:** `find midterms`

**Expected Output:**

```
'Ere are ze task(s) in your list:
[E][ ] midterms (from: Mar 5 2025 to: Mar 6 2025) 
```
### Editing a Task
**Usage:** `edit <task_number> <new_description>`

**Example:** `edit 1 buy vegetables`

**Expected Output:**

```
D'accord, your task has been edited: 
[T][ ] buy vegetables
```
### Exiting Program
**Usage:** `bye`

**Expected Output:**

```
Au revoir, 'ope to see you again soon!
```