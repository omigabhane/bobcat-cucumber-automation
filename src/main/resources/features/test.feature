Feature: Login
  In order to edit site
  As a site administrator
  I want to log in

  Scenario: Configure
    When Author opens editor '/content/firstdata/us/en.html'
    And Author configure CTA component present at container having order 0 with data cta.yaml

  Scenario: Create page using template
    Given Author opens site '/content/firstdata/us/en'
    And Author create new Bobcat Test page using the Content Page template
    Then Author opens  '/content/firstdata/us/en/bobcat_test.html'
    And Author can see the current page title is Bobcat Test


@test
  Scenario: delete page using template
    When Author delete /content/firstdata/us/en/bobcat_test.html