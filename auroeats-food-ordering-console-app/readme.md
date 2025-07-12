# AuroEats

AuroEats is a console‑based Java food ordering application. It supports both admin and customer workflows, allowing menu management, cart operations, order placement, and delivery assignment.


---

**Admin Credentials**  
🧑‍💼 **Username:** `admin`  
🔒 **Password:** `password`

---

## Features

* **Admin Panel**

  * Login with username/password
  * **Menu Management**

    * Add new food items (prevents duplicates)
    * Remove items by selecting a number from the displayed list
    * View current menu
  * **Delivery Partner Management**

    * List, add, or remove delivery partners dynamically

* **Customer Menu**

  * Browse menus (Indian, Italian, Chinese, French)
  * Add items to cart, view cart, and remove items
  * Checkout with payment and invoice generation
  * View order history

* **Payment Methods**

  * UPI payment (validated for correct UPI‑ID format)
  * Credit card payment (validated with digit length and Luhn algorithm)

* **Discounts**
  * Flat ₹100 discount automatically applied if the order total exceeds ₹500

## Highlighted Changes

1. **Payment Validation**

   * Introduced `PaymentValidator` utility and custom exceptions (`InvalidUPIException`, `InvalidCardException`).
   * UPI IDs and credit card numbers are now validated before processing.

2. **Indexed Removal of Menu Items**

   * Admin removes food items by choosing an index from the printed menu list instead of typing the full name.

3. **Dynamic Delivery Partners**

   * Admin can add or remove delivery partners at runtime.
   * Orders are assigned to a random partner from the current list.

4. **Duplicate‑Item Prevention**

   * `addFoodToMenu` now checks for existing item names and blocks duplicates.

5. **Flat ₹100 Discount**

   * If the total order value exceeds ₹500, a flat ₹100 discount is applied at checkout.

## Project Structure
## Project Structure

```
com.aurionpro
├── model
│   ├── ApplicationFacade.java
│   ├── Cart.java
│   ├── Customer.java
│   ├── DeliveryPartner.java
│   ├── FoodItem.java
│   ├── IDGenerator.java
│   ├── Invoice.java
│   ├── LineItem.java
│   └── Order.java
├── model.foodtypes
│   ├── IFoodType.java
│   ├── Appetizer.java
│   ├── Starter.java
│   ├── MainCourse.java
│   └── Dessert.java
├── model.menutypes
│   ├── IMenuType.java
│   ├── IndianMenu.java
│   ├── ItalianMenu.java
│   ├── ChineseMenu.java
│   └── FrenchMenu.java
├── model.paymenttypes
│   ├── IPayment.java
│   ├── UPIPayment.java
│   └── CreditCardPayment.java
├── exception
│   ├── InvalidInputException.java
│   ├── InvalidMenuSelectionException.java
│   ├── InvalidPriceException.java
│   ├── InvalidQuantityException.java
│   ├── InvalidUPIException.java
│   └── InvalidCardException.java
├── util
│   ├── ConsoleFormatter.java
│   ├── InputHelper.java
│   └── PaymentValidator.java
└── test
    └── MiniFoodOrderingAppTestDriver.java
```

## How to Run

1. **Compile**

   ```bash
   javac -d out $(find src -name "*.java")
   ```
2. **Execute**

   ```bash
   java -cp out com.aurionpro.test.MiniFoodOrderingAppTestDriver
   ```

Enjoy using AuroEats for a seamless console‑based food ordering experience!
