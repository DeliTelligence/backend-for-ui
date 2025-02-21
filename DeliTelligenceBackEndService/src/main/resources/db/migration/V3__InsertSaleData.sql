CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DO $$
DECLARE
sale_id UUID;
    made_deli_product_id UUID;
    ingredient_id UUID;
    num_sales INT DEFAULT 100;
    employee_id UUID;
    employee_ids UUID[] := ARRAY[
        '01602494-36f3-4710-a967-baf3643958be',
        'a8e04295-b314-4071-bb4b-4dcdc2e6909c'
    ];

    deli_product_ids UUID[] := ARRAY[
        '44ead926-e583-4735-bc4d-721c17f5da27',
        '743aa796-0aac-48f4-adb9-c497be45f575',
        'b64afc5e-911d-4a2a-875d-a37697f7a60d',
        'c8db3e8a-8edd-4b70-a0cd-4bfcf2d8b517',
        '75e42151-5ae3-48c6-a311-d5bfe00ac75a',
        '10bf450c-3ad3-4551-8be1-ead8fbe688ae',
        'b6b77ffb-4697-46cc-bd57-46dbc193ffe3'
    ];


    product_ids UUID[] := ARRAY[
        '00aad3cb-5414-4a52-9133-78a80ce8864c',
        '1ed881b9-72ea-4167-b28c-d69fe9bca338',
        '6a1c0beb-c463-4686-a861-9b974717bd54',
        '88bc5fbf-df0a-4f99-a74e-fa46de273b27',
        '30997272-6eae-4135-89fc-2abb549d33d7',
        'e7a61e09-674e-482a-8911-802d7ef65d18',
        '37325eb9-587c-40d6-a3b9-cd91ecaa5c04',
        'a1ea9ad0-e0ac-45c5-8f0e-8817a7c68e86',
        '1a3cbdc9-3079-4d79-be9d-3672a73bfc4a',
        'a6cec0bb-17c3-4078-9c4a-acf1baa8d44e',
        'ca57bee6-e71e-43eb-a1c6-bb31f6869878',
        '04942d74-e827-4afd-8b15-93a29783364e',
        '6eded5cf-1095-4470-9dd1-512732edd228',
        '9e6b28ac-4c9f-484e-a616-6fee07c14f0a',
        '70127381-6b92-4e64-a118-0b9e9755df58',
        '16cb970e-64ec-40d1-a4e2-360f1bc6619e',
        'f8ff3d22-55cb-4f54-9723-bcc8d02db737',
        'e95e77e0-cce8-4627-bd48-089ded6b8410',
        '7006dd12-5f76-4c13-813f-69f2eca2320b',
        '1bfa8228-e1b8-4afd-8622-e4d73d287223',
        '3cd2167f-d772-4c0c-ad56-3af718ac5de0',
        '57230a8e-0f37-438a-baf6-aa17630cc7ac',
        '434a824f-8ed6-492c-928d-b886d118d9f7',
        '67b024e2-3329-4734-b8b2-6761afc2f38a',
        '7425f91e-0f52-463e-a069-9bdae48e8d82',
        'a6dc0c95-3f05-4a5f-8e2d-db2ba6959b82',
        '1f00b9dc-854a-491d-99f7-4808da72515d',
        '6dafc195-4a54-4540-923e-4473aaa5faad'
    ];

 idx INT;
    product_idx INT;
    total_price FLOAT;
    num_ingredients INT;
    combined_weight FLOAT;
    delitype TEXT;
BEGIN
FOR i IN 1..num_sales LOOP
        -- Initialize total price for each sale
        total_price := 0;
        combined_weight := 0;

        -- Generate new sale ID
        sale_id := uuid_generate_v4();
        employee_id := employee_ids[(random() * array_length(employee_ids, 1))::int + 1];

        -- Pick a random deli product from the **smaller list**
        idx := (random() * (array_length(deli_product_ids, 1) - 1))::int;
        made_deli_product_id := uuid_generate_v4();

        -- Assign a default price for the deli product (Modify as needed)
        total_price := total_price + (random() * 10 + 5);

        -- Determine portion type
        delitype := CASE WHEN idx % 2 = 0 THEN 'SALAD' ELSE 'FILLING' END;
        combined_weight := (random() * 200) + 50; -- Weight between 50g - 250g

        -- Determine number of ingredients (1 to 5)
        num_ingredients := (random() * 4)::int + 1;

        -- Insert ingredients from the **larger list**
FOR k IN 1..num_ingredients LOOP
            product_idx := (random() * (array_length(product_ids, 1) - 1))::int;
            total_price := total_price + (random() * 2 + 1); -- Add ingredient price
            combined_weight := combined_weight + (random() * 50 + 10); -- Increase weight

INSERT INTO TBL_INGREDIENT (INGREDIENT_ID, MADE_DELI_PRODUCT_ID, PRODUCT_ID)
VALUES (uuid_generate_v4(), made_deli_product_id, product_ids[product_idx + 1]);
END LOOP;

        -- Insert into TBL_DELI_PRODUCT
INSERT INTO TBL_DELI_PRODUCT (
    MADE_DELI_PRODUCT_ID, DELI_PRODUCT_ID, COMBINED_WEIGHT,
    PORTION_TYPE, SALE_ID
) VALUES (
             made_deli_product_id, deli_product_ids[idx + 1], combined_weight,
             delitype, sale_id
         );

-- Insert into TBL_DELI_SALES
INSERT INTO TBL_DELI_SALES (
    SALE_ID, EMPLOYEE_ID, SALE_PRICE, SALE_DATE, SALE_WEIGHT, SALE_TIME,
    WASTE_PER_SALE_VALUE, DIFFERENCE_WEIGHT, SALE_TYPE, QUANTITY, HAND_MADE
) VALUES (
             sale_id, employee_id, total_price,
             CURRENT_DATE - (random() * 5)::int,
             combined_weight,
             CURRENT_TIMESTAMP + (random() * (11*60*60))::interval,
             random() * 2, random() * 5,
             'COLD_FOOD',  -- Assign appropriate sale type
             (random() * 10)::int + 1,
             (random() > 0.5)
         );
END LOOP;
END $$;