# Purchase system-DP

In this project first we get the items from DB, then you can choose your items and after it, you can see your bill and price.

## Getting Started

First of all, you have to create tables in your DB on your computer, for this, you have to run the DB.java file.
After it, you can run Main.java for seeing the main program.

### Prerequisites

You need to have H2 Database Engine on your computer.


## How to work with program

After you run the Main.java, you can see the items in console, then you can choose your items in this way:

“item id” amount

After you finish your ordering you have to type “0” in the console to end the taking order process.

Then you can see your orders in the console and the total calculated price.
If you see “Inserted records into the table...” message it means that your order inserted to DB successfully otherwise you see "Can't insert your record! :(" message.

## DB Architecture

We three table, “ITEMS” table for record items and their prices, “ORDERS_BILL” table for record the orders and their total prices, “ORDERS_DETAIL” for record the items in any order, the Primary key in “ORDERS_BILL” table is Foreign key in “ORDERS_DETAIL”.

## Note

If you insert an id twice or more, you will see the total amounts of that item in your bill.
## Authors

* **Amir hosein Safari** 


