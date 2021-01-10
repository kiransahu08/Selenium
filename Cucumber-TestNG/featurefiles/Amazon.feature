Feature: Verify product for amazon

  Scenario: Search product on amazon and add to card
    When Open www.amazon.co.uk
    Then From the hamburger menu in the upper left-hand corner select Electronics & Computers
    Then From the following menu select Laptops
    And Select Display Size 15-16
    And Select CPU Type -Intel Core i5
    Then Select Storage Type SSD
    Then the result will be filtered.
		Then Select a five starred Laptop with the lowest price
		And Once the details page for the laptop is opened
		Then verify it's the selected laptop
		