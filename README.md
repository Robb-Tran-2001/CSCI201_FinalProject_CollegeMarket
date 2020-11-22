# CSCI201_FinalProject_CollegeMarket

## CSCI 201 Final Project
- Contributors: Eugene Lee, Samuel Sommerer, Robert Tran, Anuj Vasil, Felix Liao

## Requirements
- MySQL Workbench
- Eclipse IDE
- Apache Tomcat

## Running this Web Application

### Creating the MySQL Database
- Start MySQL Server/Workbench
- In left hand pane, switch to “Administration” tab
- Open “Users and Privileges”
- Ensure there exists a user with username:`root` and password:`root` with DBA privileges
- If such a user does not exist, click “Add Account” to create a new user with username and password root
-- Select new user in the list on the left-hand side
-- Under “Details for account [username]@localhost” go to “Administrative roles” tab
-- Make sure Role: “DBA” is checked
-- Username and password can be changed, but ‘CSCI201_FinalProject_CollegeMarket/src/main/resource/application.properties’ file, lines 8 and 9 (spring.datasource.username and spring.datasource.password) must be changed to the new credentials 

### Importing the Project into Eclipse
- Clone this project to your local machine with `git clone https://github.com/Robb-Tran-2001/CSCI201_FinalProject_CollegeMarket.git`
- Open Eclipse IDE, go to File > Import > Maven > Existing Maven Project then click Next.
- Click Browse and search for the CSCI201_FinalProject_CollegeMarket folder that was cloned in step one. Click Select.
- Click Finish and the project should be imported into the Workspace. Package Explorer should now contain the imported project.

### Running the Project in Eclipse

#### Backend
- Navigate to the project in Package Explorer and click on it to show all the packages.
- Go to the package src/main/java, and expand it.
- Click on the subpackage `com.csci201.marketplace`, there should be a file called `MarketplaceApplication.java`
- Run the file as a Java Project by either left clicking the Run button in the top navigation bar, or right click on the file from within the Package Explorer and get to Run As > Java Application, left click it. At this point, the project should be running.

#### Frontend
- After having done the above, in Google Chrome, go to `localhost:8080`, which should take you to the frontend.
- If prompted for authorization, enter “user” (without the quotation marks) in the Username field and enter “pw” (also without the quotation marks) in the Password field.
- At this point, the project should be ready to run.

### How to Navigate the Website
- At first, only guest features are allowed (essentially viewing the website). The “Buy Now” button will not function. 
- Click the login button on the top right of the screen to pop up a window, sign in with an existing account (Username: admin, Password: root). If you want to sign up for a new account, create a new user by clicking the bottom right corner text that says “Don’t have an account?”, and enter your desired username and password.
- Once you’re at this step, you’re already logged in and can use other functionalities. Select the “Create a New Listing” button to put an item up for sale.
- Click “Buy Now” next to an item to buy the item. This sends a buy request to the seller, which the seller has to approve in order for the purchase to be finalized. The item should not show up in the item listings anymore. Additionally, once “Buy Now” is pressed, a push notification will be sent to everyone on the website notifying them that an item purchase has been requested.
- As a seller, click on your name in the top right corner, which takes you to the list of purchase request other users have made. 
- Click approve to accept the request, and the item is sold successfully. The request should disappear once approved.
- Go back to the homepage to buy/sell new items.
- To change password, click on the username in the top right corner, and on the left, there is the option “Change Password”. Enter a new desirable password to successfully change the new login.
- Open a new tab and go to `localhost:8080` again to sign in as a new user or login as a new user.



