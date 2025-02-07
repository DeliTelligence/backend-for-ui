CREATE TABLE TBL_EMPLOYEE (
                              EMPLOYEE_ID UUID PRIMARY KEY,
                              EMPLOYEE_FIRST_NAME VARCHAR(50) NOT NULL,
                              EMPLOYEE_LAST_NAME VARCHAR(50) NOT NULL,
                              EMPLOYEE_LOGGED_IN BOOLEAN NOT NULL,
                              EMPLOYEE_PASSWORD VARCHAR(200) NOT NULL,
                              EMPLOYEE_TITLE VARCHAR(50) NOT NULL,
                              HIRE_DATE DATE NOT NULL
);




CREATE TABLE TBL_DELI_SALES (
                                SALE_ID UUID PRIMARY KEY,
                                EMPLOYEE_ID UUID NOT NULL,
                                SALE_PRICE FLOAT(2) NOT NULL,
                                SALE_DATE DATE NOT NULL,
                                SALE_WEIGHT FLOAT(2) NOT NULL,
                                SALE_TIME TIMESTAMP NOT NULL,
                                WASTE_PER_SALE_VALUE FLOAT(2) NOT NULL,
                                DIFFERENCE_WEIGHT FLOAT(2) NOT NULL,
                                SALE_TYPE VARCHAR(50) NOT NULL,
                                QUANTITY INTEGER,
                                HAND_MADE BOOLEAN NOT NULL,
                                FOREIGN KEY (EMPLOYEE_ID) REFERENCES TBL_EMPLOYEE(EMPLOYEE_ID)



);

CREATE TABLE TBL_STANDARD_WEIGHT (
                                     STANDARD_WEIGHT_ID UUID PRIMARY KEY,
                                     STANDARD_TYPE VARCHAR(300) NOT NULL
);
CREATE TABLE TBL_INVENTORY (
                               INVENTORY_ID UUID PRIMARY KEY,
                               TOTAL_WEIGHT FLOAT NOT NULL,
                               INVENTORY_VALUE FLOAT(2) NOT NULL,
                               INVENTORY_EXPIRATION_DATE DATE NOT NULL,
                               LOCATION VARCHAR(200) NOT NULL


);



CREATE TABLE TBL_PRODUCT (
                             PRODUCT_ID UUID PRIMARY KEY,
                             INVENTORY_ID UUID,
                             PRODUCT_NAME VARCHAR(100) NOT NULL,
                             PRODUCT_DESCRIPTION VARCHAR(1000) NOT NULL,
                             PRODUCT_PRICE FLOAT(2) NOT NULL,
                             PRODUCT_TYPE VARCHAR(100) NOT NULL,
                             PRODUCT_DELETED BOOLEAN NOT NULL,
                             PRODUCT_IMAGE bytea NOT NULL,
                             FOREIGN KEY (INVENTORY_ID) REFERENCES TBL_INVENTORY(INVENTORY_ID)




);

CREATE TABLE TBL_STANDARD_WEIGHT_PRODUCT (
                                             STANDARD_WEIGHT_PRODUCT_ID UUID NOT NULL,
                                             STANDARD_WEIGHT FLOAT NOT NULL,
                                             STANDARD_WEIGHT_ID UUID NOT NULL,
                                             PRODUCT_ID UUID NOT NULL,
                                             FOREIGN KEY (STANDARD_WEIGHT_ID) REFERENCES TBL_STANDARD_WEIGHT(STANDARD_WEIGHT_ID),
                                             FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(PRODUCT_ID)


);

CREATE TABLE TBL_DELI_PRODUCT (
                                  MADE_DELI_PRODUCT_ID UUID PRIMARY KEY,
                                  DELI_PRODUCT_ID UUID,
                                  COMBINED_WEIGHT FLOAT NOT NULL,
                                  PORTION_TYPE VARCHAR(300) NOT NULL,
                                  SALE_ID UUID,
                                  FOREIGN KEY (DELI_PRODUCT_ID) REFERENCES TBL_PRODUCT(PRODUCT_ID),
                                  FOREIGN KEY (SALE_ID) REFERENCES TBL_DELI_SALES(SALE_ID)
);

CREATE TABLE TBL_INGREDIENT (
                                INGREDIENT_ID UUID PRIMARY KEY,
                                MADE_DELI_PRODUCT_ID UUID NOT NULL,
                                PRODUCT_ID UUID NOT NULL,
                                FOREIGN KEY (MADE_DELI_PRODUCT_ID) REFERENCES TBL_DELI_PRODUCT(MADE_DELI_PRODUCT_ID),
                                FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(PRODUCT_ID)
);



CREATE TABLE TBL_FOOD_ALLERGEN (
                                   ALLERGEN_ID UUID PRIMARY KEY ,
                                   ALLERGEN_DESCRIPTION VARCHAR(1000),
                                   ALLERGEN_NAME VARCHAR(200)



);

CREATE TABLE TBL_FOOD_ALLERGENS_DETAIL (
                                           PRODUCT_ID UUID NOT NULL,
                                           ALLERGEN_ID UUID NOT NULL,
                                           FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(PRODUCT_ID),
                                           FOREIGN KEY (ALLERGEN_ID) REFERENCES TBL_FOOD_ALLERGEN(ALLERGEN_ID)


);


CREATE TABLE TBL_SUPPLIER (
                              SUPPLIER_ID UUID PRIMARY KEY,
                              SUPPLIER_NAME VARCHAR(200) NOT NULL,
                              SUPPLIER_LOCATION VARCHAR(500) NOT NULL,
                              SUPPLIER_NUMBER VARCHAR(200) NOT NULL,
                              SUPPLIER_WEBSITE VARCHAR(500) NOT NULL

);



CREATE TABLE TBL_INVENTORY_ADJUSTMENT (
                                          ADJUSTMENT_ID UUID PRIMARY KEY,
                                          INVENTORY_ID UUID NOT NULL,
                                          PRODUCT_ID UUID NOT NULL,
                                          SUPPLIER_ID UUID NOT NULL,
                                          WEIGHT_ADJUSTMENT FLOAT NOT NULL,
                                          UNIT_COST FLOAT NOT NULL,
                                          ADJUSTMENT_TYPE VARCHAR(200) NOT NULL,
                                          REASON VARCHAR(1000),
                                          DATE_OF_ADJUSTMENT DATE NOT NULL,
                                          FOREIGN KEY (INVENTORY_ID) REFERENCES TBL_INVENTORY(INVENTORY_ID),
                                          FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(PRODUCT_ID),
                                          FOREIGN KEY (SUPPLIER_ID) REFERENCES TBL_SUPPLIER(SUPPLIER_ID)

);
