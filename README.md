App for stream processing of CSV files.

CVS file suppose to contain 3 collumns:
|City name|date|temperature|


To setup the app for running do the following steps:

1. Update application.yml with the absolute path to the CSV file
2. Start Springboot application
3. Call GET localhost:8080/city to get all accessible cities
4. Call GET localhost:8080/temperature/{city} to get annual average temperature for specified {City}
