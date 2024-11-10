CREATE TABLE TBL_EMPLOYEE (
                              EMPLOYEE_ID UUID PRIMARY KEY,
                              EMPLOYEE_FIRST_NAME VARCHAR(50) NOT NULL,
                              EMPLOYEE_LAST_NAME VARCHAR(50) NOT NULL,
                              EMPLOYEE_LOGGED_IN BOOLEAN NOT NULL,
                              EMPLOYEE_TITLE VARCHAR(50) NOT NULL,
                              HIRE_DATE DATE NOT NULL
);



CREATE TABLE TBL_DELI_PRODUCT (
                                  DELI_PRODUCT_ID UUID PRIMARY KEY,
                                  THEORETICAL_WEIGHT FLOAT(2) NOT NULL,
                                  WEIGHT_TO_PRICE BOOLEAN NOT NULL


);
CREATE TABLE TBL_DELI_SALES (
                                SALE_ID UUID PRIMARY KEY,
                                EMPLOYEE_ID UUID NOT NULL,
                                SALE_PRICE FLOAT(2) NOT NULL,
                                SALE_DATE DATE NOT NULL,
                                SALE_WEIGHT FLOAT(2) NOT NULL,
                                SALE_TIME TIMESTAMP NOT NULL,
                                DELI_PRODUCT_ID UUID NOT NULL,
                                WASTE_PER_SALE FLOAT(2) NOT NULL,
                                WASTE_PER_SALE_VALUE FLOAT(2) NOT NULL,
                                DIFFERENCE_WEIGHT FLOAT(2) NOT NULL,
                                SALE_TYPE VARCHAR(50) NOT NULL,
                                QUANTITY INTEGER,
                                FOREIGN KEY (DELI_PRODUCT_ID) REFERENCES TBL_DELI_PRODUCT(DELI_PRODUCT_ID),
                                FOREIGN KEY (EMPLOYEE_ID) REFERENCES TBL_EMPLOYEE(EMPLOYEE_ID)



);


CREATE TABLE TBL_PRODUCT (
                             PRODUCT_ID UUID PRIMARY KEY,
                             PRODUCT_NAME VARCHAR(100) NOT NULL,
                             STANDARD_WEIGHT FLOAT(2) NOT NULL,
                             PRODUCT_DESCRIPTION DATE NOT NULL,
                             PRODUCT_PRICE FLOAT(2) NOT NULL,
                             PRODUCT_IMAGE bytea NOT NULL

);
CREATE TABLE TBL_DELI_FILLING (
                                  DELI_PRODUCT_ID UUID NOT NULL,
                                  PRODUCT_ID UUID NOT NULL,
                                  FOREIGN KEY (DELI_PRODUCT_ID) REFERENCES TBL_DELI_PRODUCT(DELI_PRODUCT_ID),
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

CREATE TABLE TBL_INVENTORY (
                               INVENTORY_ID UUID PRIMARY KEY,
                               INVENTORY_TOTAL_BOXES INTEGER NOT NULL,
                               INVENTORY_VALUE FLOAT(2) NOT NULL,
                               INVENTORY_EXPIRATION_DATE DATE NOT NULL,
                               LOCATION VARCHAR(200) NOT NULL,
                               PRODUCT_ID UUID NOT NULL,
                               FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(PRODUCT_ID)


);

CREATE TABLE TBL_SUPPLIER (
                              SUPPLIER_ID UUID PRIMARY KEY,
                              SUPPLIER_NAME VARCHAR(200) NOT NULL,
                              SUPPLIER_LOCATION VARCHAR(200) NOT NULL

);

CREATE TABLE TBL_PURCHASE_ORDER (
                                    PURCHASE_ORDER_ID UUID PRIMARY KEY,
                                    ORDER_PRICE FLOAT(2) NOT NULL,
                                    SUPPLIER_ID UUID NOT NULL,
                                    ORDER_DATE DATE NOT NULL,
                                    ORDER_STATUS BOOLEAN NOT NULL,
                                    FOREIGN KEY (SUPPLIER_ID) REFERENCES TBL_SUPPLIER(SUPPLIER_ID)


);

CREATE TABLE TBL_PURCHASE_ORDER_DETAIL (
                                           PURCHASE_ORDER_DETAIL_ID UUID PRIMARY KEY,
                                           PRODUCT_ID UUID NOT NULL,
                                           PURCHASE_ORDER_ID UUID NOT NULL,
                                           WEIGHT_PER_BOX FLOAT(2) NOT NULL,
                                           UNIT_PRICE FLOAT(2) NOT NULL,
                                           QUANTITY_OF_BOX INTEGER NOT NULL,
                                           FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT(PRODUCT_ID),
                                           FOREIGN KEY (PURCHASE_ORDER_ID) REFERENCES TBL_PURCHASE_ORDER(PURCHASE_ORDER_ID)



);

CREATE TABLE TBL_INVENTORY_ADJUSTMENT (
                                          ADJUSTMENT_ID UUID PRIMARY KEY,
                                          INVENTORY_ID UUID NOT NULL,
                                          ADJUSTMENT_TYPE VARCHAR(200) NOT NULL,
                                          REASON VARCHAR(1000) NOT NULL,
                                          DATE_OF_ADJUSTMENT DATE NOT NULL,
                                          PURCHASE_ORDER_DETAIL_ID UUID NOT NULL,
                                          FOREIGN KEY (INVENTORY_ID) REFERENCES TBL_INVENTORY(INVENTORY_ID),
                                          FOREIGN KEY (PURCHASE_ORDER_DETAIL_ID) REFERENCES TBL_PURCHASE_ORDER_DETAIL(PURCHASE_ORDER_DETAIL_ID)



);
