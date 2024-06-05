# RangeCalculator

RangeCalculator is a Java utility that calculates the number of unique days between multiple date ranges, optionally excluding weekends. This utility handles overlapping date ranges to ensure days are counted only once.

## Features

- Calculate the number of unique days between multiple date ranges.
- Optionally include or exclude weekends in the calculation.
- Handle overlapping date ranges.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher

### Installing

1. Clone the repository:

    ```sh
    git clone https://github.com/yourusername/RangeCalculator.git
    cd RangeCalculator
    ```

2. Build the project using Maven:

    ```sh
    mvn clean install
    ```

### Running the Application

To run the application, use the following command:

```sh
java -cp target/RangeCalculator-1.0-SNAPSHOT.jar util.dateranges.RangeCalculator <json_file_path> [--include-weekends]
```

- Replace `<json_file_path>` with the path to your JSON file.
- Use the `--include-weekends` flag if you want to include weekends in the count.

### Example

1. Create a JSON file (e.g., `periods.json`) with the following content:

    ```json
    [
        {"start_date": "2024-06-01", "end_date": "2024-06-10"},
        {"start_date": "2024-06-05", "end_date": "2024-06-15"},
        {"start_date": "2024-06-20", "end_date": "2024-06-25"}
    ]
    ```

2. Run the application:

    ```sh
    java -cp target/RangeCalculator-1.0-SNAPSHOT.jar util.dateranges.RangeCalculator periods.json
    ```

    Output:

    ```sh
    Assuming the date format is yyyy-MM-dd
    Number of unique days: 14
    ```

## Running the Tests

To run the tests, use the following command:

```sh
mvn test
```

### Test Cases

The project includes test cases to verify the functionality of the `RangeCalculator` class. The tests are located in `src/test/java/util/dateranges/RangeCalculatorTest.java`.

## Project Structure

```
RangeCalculator/
│
├── pom.xml                      # Maven configuration file
├── README.md                    # Project documentation
└── src
    ├── main
    │   └── java
    │       └── util
    │           └── dateranges
    │               └── RangeCalculator.java  # Main application class
    └── test
        └── java
            └── util
                └── dateranges
                    └── RangeCalculatorTest.java  # Test cases
```

## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [org.json](https://github.com/stleary/JSON-java) - JSON parsing library
- [JUnit](https://junit.org/junit4/) - Testing framework

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

