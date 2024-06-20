# Drivers

This document intends to represent the Drivers obtained from the Stakeholders.

For ease of understanding, these are arranged in the table below.
This is made up of an "Identification" column followed by a "Description" column.



| ID                        | Description                                                  |
| ------------------------- | ------------------------------------------------------------ |
| DRVR_01                   | A new service was necessary and desired to provide visually attractive but also nutritious sandwiches: Gorgeous Sandwich. |
| DRVR_02                   | The prototype presentation must include the build and the execution at the command line, with instructions available in the repository |
| DRVR_03                   | Consider the usage of secure by design principles and threads to mitigate |
| DRVR_04                   | The prototype to be developed must be accessible using a web browser and it should be available in the next four weeks |
| **Architectural Drivers** |                                                              |
| DRVR_05                   | **Sandwich management:** Each sandwich has a designation, a selling price, and multiple descriptions, although one per authorized language. The language is to be detected by the application. The allowed languages must be changed in a few seconds, but without impacting what was previously accepted. However, descriptions should never be removed |
| DRVR_06                   | **Shop management:** A shop has a designation, an address, a person in charge, and opening hours that may differ depending on the days of the week. The person in charge has a name and an email. |
| DRVR_07                   | **Order management:** A client can register sandwiches and their quantities for delivery on a specific day and shop. The total price is informed by the prototype.Please note that a sandwich can never be sold below zero, despite the promotions applied. |
| DRVR_08                   | **Customer management:** A customer has a name, a tax identification number, an address, an email and authentication data|
| DRVR_09                   | **Promotions management:** Global and local promotions can be specified. While global promotions apply to all stores, local promotion does not. Both types specify percentages that apply as a discount to the unit price of specific products and have a specifoed period for their application.|
| DRVR_10                   | Promotions are reflected in the price according to different possibilities: only the most favourable or local and global promotions are applied cumulatively. Switching from one possibility to another should be possible within seconds, without affecting what is finished.|
| DRVR_11                   | The prototype, only insertions and listings are intended, with the specified modification possibilities.|
| DRVR_12                   | Users with different roles interact with the system prototype. |
| DRVR_13                   | Authentication is needed for the prototype. It must ensure that 99% of unauthorized login attempts are detected.  |
| DRVR_14                   | Solution that prevents brute-force authentication attempts and comes as close as possible to the specified value |
| DRVR_15                   | At least one metric is mandatory to explore, an experimental metric to indicate the maintainability level of the application. The SonargraphExplorer tool is able to calculate it. The team has carried out some experiments with the tool and aims to reach a value of at least 70% . Other applications can be considered for other maintainability metrics         |
| DRVR_16                   | Testing on various quality dimensions is mandatory for the prototype, including the business rules captured in domain layers and what can ensure the correct functioning of the application and its API |
| DRVR_17                   | Only open-source tools and technologies are allowed, except those clearly mentioned in this document.                        |
|DRVR_18                    | Monolith with loose coupling between components must be developed|
|DRVR_19                    | Security issues in applications previously developed by the company highlight the need for careful design of what can have security implications. Thus, the team needs to think about some secure by design principles and threads to mitigate. Proper tools should be used.|