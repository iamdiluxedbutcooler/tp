---
layout: page
title: User Guide
---

AgentAssist is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AgentAssist can get your sales tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

### 1. Install Java

Make sure you have **Java 17 or above** installed on your computer. You may skip this step if you already have it installed.

Installing Java:

* Download Java [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html). Follow the instructions on that page to install Java.

### 2. Download the AgentAssist application

Go to this [link](https://github.com/AY2425S1-CS2103T-T14-4/tp/releases) and download the latest version of the `.jar` file.

### 3. Choose a Folder

Find or create a folder on your computer where you want to store the AgentAssist information. <br>
Move the `.jar` file you just downloaded into this folder.

### 4. Open a Command Terminal

Now, open a command terminal:

* On Windows, press the **Windows Key** and type **`Command Prompt`** in the search bar. Click on the **Command Prompt** application to open it.
* On macOS, press **Cmd + Space**, type **`Terminal`** into the search bar, and press **Enter**.
* On Linux, open your Terminal application.

### 5. Run the applicaton

Navigate your terminal to the folder where you saved the AgentAssist application:

* Type **`cd `** (with a space after it), then drag and drop the folder where you placed the `.jar` file into the terminal window. The command should look similar to this: `cd '/Users/name/AgentAssistFolder'`. Press Enter.<br>

Type the following command: **`java -jar agentassist.jar`** and press **Enter**.

* A window similar to the below image should appear in a few seconds. You will see a graphical user interface with sample contact information already added.<br>
  ![Ui](images/Ui.png)

### 6. Using AgentAssist

In the application window, you will see a **command box**. This is where you type commands to manage your contacts.

* Type a command in the command box and press **Enter** to make the app do something.
* Typing `help` and pressing Enter will open the help window.

Here are some example commands you can try:

* `list` : Lists all contacts.
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.
* `delete 3` : Deletes the 3rd contact shown in the current list.
* `clear` : Deletes all contacts.
* `exit` : Exits the app.

Refer to the [Features](#features) section for details of each command.

---

## Features Overview

### Feature 1: Ability to Save Current Data

**Purpose:**  
This feature ensures that any details you add to the app are saved automatically. When you close and reopen the app, all your data will still be there.

**How it Works:**
- **Command Format and Example:** Not applicable, as this process is automatic.

#### Parameters
- **Flags and Parameters:** There are no parameters needed for this feature.

#### What to Expect
- **If Successful:** You can access all the data you've entered previously.
- **If There is an Error:** There's a chance that the data might not be saved due to an error, and you could lose information.

---

### Feature 2: Add New Customer

**Purpose:**  
This feature allows you to enter and save detailed records for new customers.Each customer's record includes their name, contact number, email, occupation, and income. You can also enter the optional fields for credit card tier and remark here. Otherwise, new users are assigned a default value of "N.A".

**How to Use It:**
- **Command Format:**
```
add n/ <NAME> p/ <PHONE> e/ <EMAIL> a/ <ADDRESS> j/ <JOBNAME> i/ <INCOME> [t/ <TAG>] [rn/ <REMARK>]
 ```
- **Example:**
```
add n/ TAN LESHEW p/ 99007766 e/ mrtan@ntu.sg a/ com3 j/ doctor i/ 99999
add n/ TAN LESHEW p/ 99007766 e/ mrtan@ntu.sg a/ com3 j/ doctor i/ 99999 t/ gold rn/ got anger issue
```

#### Parameters {#add-command-parameters}
| Parameter | Expected Format                                  | Explanation                                                                       |
|-----------|--------------------------------------------------|-----------------------------------------------------------------------------------|
| NAME      | Alphanumeric, capitalized                        | Names are in block letters for clarity and consistency.                           |
| PHONE     | 8-digit number, starts with 8 or 9               | Ensures the contact number is valid in Singapore.                                 |
| EMAIL     | Must include "@" and domain, case insensitive    | Verifies that the email address is in a standard format.                          |
| ADDRESS   | Any text, case insensitive                       | Accepts all addresses without case sensitivity. Addresses can have numbers and symbol alike /. |
| JOB       | Any text, case insensitive                       | Accepts all job titles without case sensitivity.                                  |
| INCOME    | Non-negative integers                            | Only positive numbers or zero are valid for income fields.                        |
| TAG       | [optional] String (gold, silver, bronze, reject) | Defines the specific credit card tier to be assigned or updated.                  |
| REMARK    | [optional] Any string                            | Notes are case-insensitive and can include any textual information.               |

#### What to Expect
- **If Successful:** You'll see a message: "New person added: `<NAME>`; Phone: `<PHONE>`; Email: `<EMAIL>`; Address: `<ADDRESS>`; Job: `<JOB>`;  Income: `<INCOME>`; Tag: `<TAG>`; Remark: `<REMARK>`". It's noted that if "Tag" and "Remark" are not added, they will be defined as "N/A."
- **If There is an Error:**
  - "Please verify that your input is in the correct format. Include the following details: n/ `<NAME>` p/ `<PHONE>` e/ `<EMAIL>` a/ `<ADDRESS>` j/ `<JOBNAME>` i/ `<INCOME>` [t/ `<TAG>`] [rn/ `<REMARK>`]."

**Handling Duplicates:**  
If a customer with the same name, email, job, and income is already saved, you'll get a message: "This customer is already saved as a contact."

---

### Feature 3: Remove Old Customer

**Purpose:**  
This feature allows you to remove records of customers who are no longer using your credit card services.

**How to Use It:**
- **Command Format:**
```
del <INDEX>
```
- **Example:**
```
del 69
```

#### Parameters
| Parameter | Expected Format             | Explanation                                                                                                                              |
|-----------|-----------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| INDEX        | Integer (0 to the last INDEX)  | The INDEX must be a valid integer within the registered range (either the original list or any filtered list after using `filter` command). |

#### What to Expect
- **If Successful:** You'll see a message: "Customer `<INDEX>` has been deleted."
- **If There is an Error:**
  - Invalid index: "No customer with `<INDEX>` exists. Please recheck the index."

**Handling Duplicates:**  
Since customer INDEX are unique identifiers:
- No two customers can have the same index due to the uniqueness constraint on customer index.
- Even if a customer record appears duplicated due to data file modifications, the system assigns a unique index upon loading the data, preventing actual duplicates in the database.

---

### Feature 4: Edit the existing customer

**Purpose:**  
This feature enables users to update the details of an existing customer, apart from the remark as of v1.1, in the database. It is designed to accommodate changes in a customer’s information such as contact details, address, job information, or any other relevant data.

**How to Use It:**
- **Command Format:**
```
edit <INDEX> n/ <NAME> p/ <PHONE> e/ <EMAIL> a/ <ADDRESS> j/ <JOB> i/ <INCOME> [t/ <TAG>] [rn/ <NEW REMARK>] [ra/ 
<REMARK TO BE APPENDED ONTO EXISTING ONE>]
```
**Note that `rn/` and `ra/` cannot be used at the same time.**
- **Examples:**
```
edit 69 n/ TAN LESHEW p/ 77337733 e/ mrtan@ntu.sg a/ COM3 j/ doctor i/ 1000000000 ra/ Specialist in eye care

```

#### Parameters
| Parameter  | Expected Format                   | Explanation                                                                                 |
|------------|-----------------------------------|---------------------------------------------------------------------------------------------|
| INDEX        | Integer (0 to the last index) | The index must be a valid integer within the registered range (either the original list or any filtered list after using `filter` command). |
| NAME      | Alphanumeric, capitalized                        | Names are in block letters for clarity and consistency.                           |
| PHONE     | 8-digit number, starts with 8 or 9               | Ensures the contact number is valid in Singapore.                                 |
| EMAIL     | Must include "@" and domain, case insensitive    | Verifies that the email address is in a standard format.                          |
| ADDRESS   | Any text, case insensitive                       | Accepts all addresses without case sensitivity. Addresses can have numbers and symbol alike /. |
| JOB       | Any text, case insensitive                       | Accepts all job titles without case sensitivity.                                  |
| INCOME    | Non-negative integers                            | Only positive numbers or zero are valid for income fields.                        |
| TAG       | [optional] String (gold, silver, bronze, reject) | Defines the specific credit card tier to be assigned or updated.                  |

#### What to Expect
- **If Successful:**
  - Message: "Customer `<INDEX>` has been updated successfully."
- **If There is an Error:**
  - "Failed to update customer `<INDEX>`. Please verify that your input matches the expected formats and all fields are correct."

**Handling Duplicates:**
- No two customers can have the same index due to the uniqueness constraint on customer index.

---

### Feature 5: Find a Customer by Details

**Purpose:**  
This feature allows users to search for customers by specific details such as name, address, email, phone number, job title, or remarks. 

**How to Use It:**  
To perform a search, use the `filter` command followed by a flag (indicating the field you want to search) and the corresponding search term. Searches are **case-insensitive** and use **substring-matching**. See [substring-matching](#substring-matching) to learn more. 

- **Command Format:** 
```
filter <FLAG>/ <SEARCH TERM>
```
- **Examples:** 
  - Filter customers by name: 
  ```
  filter n/ TAN LESHEW
  ```
  - Filter customers by job:
  ```
  e.g. filter j/ doctor
  ```

#### Parameters
| Parameter   | Expected Format                                                                                              | Explanation                                                                                             |
|-------------|--------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|
| FLAG        | Refer to the list of supported flags detailed below.                                                         | Identifies the field to search (e.g., `n/` for name, `j/` for job).|                                                                          |
| SEARCH TERM | Refer to the syntax constraints in the [parameter subsection of the `add` command](#add-command-parameters). | The value to search for in the specified field (e.g., "doctor" for job, "TAN LESHEW" for name).         |

#### Supported flags:
- `n/` for Name
- `p/` for Phone Number
- `e/` for Email
- `a/` for Address
- `j/` for Job
- `r/` for Remarks

#### Substring Matching:
- Substring matching is used for searches, meaning that the search term must match a part of the field in the same order as it appears in the customer record.
- For instance, if a customer’s name is "John Lee", the search term "John", "Lee", or "John Lee" will match, but "Lee John" will not.


#### What to Expect
- **If Successful:**
  - Message: "Here are all the customers that match your search: (List of customers)."
- **If Unsuccessful (No Matches Found):**
  - Message: "No customers match your search criteria."
- **If Multiple Filters Are Used:**
  - Message: "Using multiple filters is not supported yet. Please use only one filter."
- **If There is an Error:** 
  - No Valid Flags Used:
    - Message:
    
      "filter: Searches for all customers whose specified field contains the given
    substring (case-insensitive) and displays the results in a numbered list.
    
      Parameters: `<FLAG>/ <SEARCH TERM>`
      
      Flags: `n/` (name), `p/` (phone), `e/` (email), `a/` (address), `j/` (job), `r/` (remarks)
      
      Example: `filter n/ Alice`
    
      This will find all customers whose names contain 'Alice'."
  - If Search Term Fails to Meet Requirement (i.e. Phone Number longer than 8 digits):
    - The system will display usage hints specific to the first invalid search term.

---

### Feature 6: Save Remarks About Customers

**Purpose:**  
Allows users to save specific notes or remarks about a customer, which can be viewed later to recall notable details.

**How to Use It:**
- **Command Format:**
```
remark <INDEX> r/ <REMARK>
```
- **Example:**
```
remark 55 r/ He is a problematic customer.
```

#### Parameters
| Parameter | Expected Format               | Explanation                                                                                                                                 |
|-----------|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| INDEX     | Integer (0 to the last index) | The index must be a valid integer within the registered range (either the original list or any filtered list after using `filter` command). |
| REMARK    | Any string                    | Remarks are case-insensitive and can include any textual information.                                                                       |

#### What to Expect
- **If Successful:**
  - Message: "Remark has been added to Customer `<INDEX>`."
- **If There is an Error:**
  - Invalid index: "No customer with `<INDEX>` exists. Please input a valid index."

**Handling Duplicates:**
- Although customer index should be unique, in the rare case where duplicates are detected, the following error message will be shown:
  - "Sorry, it appears that multiple customers with index: `<INDEX>` exist. Please use the delete command to remove the duplicated customer index."

---

### Feature 7: Help

**Purpose:**  
This feature provides users with quick access to the user guide for the application, helping them understand how to use various features effectively.

**How to Use It:**
- **Command Format:**
```
help
```
- **Example:**
```
help
```

#### Parameters
- **Flag:** N/A
  - There are no parameters required for this command.

#### What to Expect
- **If Successful:**
  - No immediate message is displayed in the command interface.
  - Opens up a dialog box that provides a link to the user guide markdown file, allowing users to easily access detailed instructions and information.

**Handling Duplicates:**
- N/A as this command does not involve processing or displaying data that could involve duplicates.

---
### Feature 8: Exit

**Purpose:**  
Allows users to exit the application through a simple command, eliminating the need to use the window's close button or external controls.

**How to Use It:**
- **Command Format:**
```
exit
```
- **Example:**
```
exit
```

#### Parameters
- **Flag:** N/A
  - No parameters are needed to execute this command.

#### What to Expect
- **If Successful:**
  - The message "Terminating program…" is displayed.
  - The program will then exit after a short delay, effectively closing the application.

**Handling Duplicates:**
- N/A as this command is unique and does not process data that could involve duplicates.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.
**Q**: How do I change the remarks or credit card tier of an existing customer?<br>
**A**: Use the [`edit`](#feature-4-edit-the-existing-customer) command, and specify the corresponding `t/` and or `rn/` or `ra/` flag to change these two fields.
**Q**: Why am I getting an error when trying to edit the remark of an existing customer?
**A**: Besides making sure that the command syntax is correct, please note that the `rn/` and `ra/` flags cannot be used together, as `rn/` is used to provide a new remark that will override any existing remark. Whilst, `ra/` will append a given remark to any existing remark. 

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                          | Command Format                                                                                                                                                | Example                                                                                                        |
|---------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| **Save Data Automatically**     | *Automatic*                                                                                                                                                   | *No command required*                                                                                          |
| **Add New Customer**            | `add n/ <NAME> p/ <PHONE> e/ <EMAIL> a/ <ADDRESS> j/ <JOB> i/ <INCOME> [t/ <TAG>] [rn/ <REMARK>]`                                                             | `add n/ TAN LESHEW p/ 99007766 e/ mrtan@ntu.sg a/ com3 j/ doctor i/ 99999 t/ gold rn/ got anger issue`         |
| **Remove Old Customer**         | `del <INDEX>`                                                                                                                                                 | `del 69`                                                                                                       |
| **Edit Existing Customer**      | `edit <INDEX> n/ <NAME> p/ <PHONE> e/ <EMAIL> a/ <ADDRESS> j/ <JOB> i/ <INCOME> [t/ <TAG>] [rn/ <NEW REMARK>] [ra/ <REMARK TO BE APPENDED ONTO EXISTING ONE]` | `edit 69 n/ TAN LESHEW p/ 77337733 e/ mrtan@ntu.sg a/ COM3 j/ doctor i/ 1000000000 ra/ Specialist in eye care` |
| **Find a Customer by Details**  | `filter <FLAG>/ <FLAG FIELD>`                                                                                                                                 | `filter n/ TAN LESHEW`                                                                                         |
| **Save Remarks About Customers**| `remark <INDEX> r/ <REMARK>`                                                                                                                                  | `remark 55 r/ He is a problematic customer.`                                                                   |
| **Help**                        | `help`                                                                                                                                                        | `help`                                                                                                         |
| **Exit**                        | `exit`                                                                                                                                                        | `exit`                                                                                                         |
