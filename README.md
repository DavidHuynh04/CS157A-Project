# CS157A Project
CS-157A Banking System Desktop Database Application
Fully functional three tiered desktop application for a Banking Database System that demonstrates knowledge and understanding of Relational Database Management Systems (RDBMS) and SQL learned during CS157-A.
Instructions for setting up project:
1. Start your MySQL Database server
2. Go to src/main/java/com/example/MySqlLogic/SQLConnection.java and replace the database credentials with your own.
3. Create the schema and initialize the data
  a. Use create_database.sql and initialize_data.sql inside MySQL Workbench
  b. OR Run src/main/java/com/example/MySqlLogic/DataGenerator.java
4. Launch the program by running src/main/java/com/example/App.java
5. (Optional) Use (user5,password5) to log into the administrator view or (user1,password1) to log into a customer view.
Dependencies:
JavaFX
JavaFX FXML
JavaFX controls
Maven
MySQL
MySQL Connector/J 9.3
