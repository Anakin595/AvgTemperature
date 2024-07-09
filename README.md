# AvgTemperature App
App for stream processing of CSV files.

CVS file suppose to contain 3 collumns:

| City name  | date                                | temperature     |
|------------|-------------------------------------|-----------------|
| *cityName* | date format: *yyyy-MM-dd hh:ss.SSS* | number: *00.00* 


## Local setup:

1. Update application.yml with the absolute path to the CSV file. <br/>
    *variable to change: file.csv.location* <br/>
    **example file added under /resources folder**
2. Start AvgTemperature application *(in IntellijIdea go to AvgTemperatureApplication, press green arrow nest to the class name, select 'Run')*
3. Go to chosen web browser: type in the URL *localhost:8080/city* to get all accessible cities

```
GET localhost:8080/city

[
   {
   "name": "Gdańsk"
   },
   {
   "name": "Warszawa"
   },
   {
   "name": "Kraków"
   },
   {
   "name": "Wrocław"
   },
   {
   "name": "Łódź"
   },
   {
   "name": "Poznań"
   }
]
```

4.  Go to chosen web browser: type in the URL *localhost:8080/temperature/{city}* to get annual average temperature for specified {City}
```
localhost:8080/temperature/{Kraków}

[
  {
    "year": 2018,
    "averageTemperature": "14.08"
  },
  {
    "year": 2019,
    "averageTemperature": "15.05"
  },
  {
    "year": 2020,
    "averageTemperature": "15.91"
  },
  {
    "year": 2021,
    "averageTemperature": "14.54"
  },
  {
    "year": 2022,
    "averageTemperature": "15.74"
  },
  {
    "year": 2023,
    "averageTemperature": "16.87"
  }
]

```