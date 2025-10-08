ATMSystem - Java Swing + MySQL (Ready-to-run)
============================================

What's included:
- src/ (Java source files)
- lib/ (PLACEHOLDER: put MySQL Connector/J jar here)
- atm_system.sql (SQL to create database + sample user)
- README.txt (this file)

IMPORTANT: You must download the MySQL Connector/J (JAR) and place it into the lib/ folder.
Download from: https://dev.mysql.com/downloads/connector/j/

Steps to run (Windows):
1. Install Java (JDK 11+) and MySQL Server.
2. Start MySQL server and login to mysql client.
   mysql -u root -p
   (enter password: saurabh123@patil)

3. Run the SQL script to create DB and sample user:
   mysql -u root -p < atm_system.sql
   (or open the script in MySQL Workbench and run)

4. Place the MySQL Connector/J jar (e.g., mysql-connector-j-8.0.x.jar) into the lib/ folder.

5. Compile Java sources:
   Open command prompt in the ATMSystem folder and run (example):
   javac -cp "lib/*;src" src/*.java

6. Run the application:
   java -cp "lib/*;src" LoginFrame

   Note for Linux/Mac: use ':' instead of ';' in classpath.

Default demo login:
   Card Number: 12345
   PIN: 1234

Notes:
- DB credentials are pre-configured in DBConnection.java (username: root, password: saurabh123@patil).
  If your MySQL uses different credentials, edit src/DBConnection.java accordingly.
- If you get a ClassNotFoundException for com.mysql.cj.jdbc.Driver, ensure the connector jar is in lib/ and included in classpath.
- For VS Code: open folder, configure Java project settings, add the jar as a library in project settings or keep in lib and use the above compilation commands.

Enjoy! If you want, I can additionally provide step-by-step screenshots for VS Code setup.
