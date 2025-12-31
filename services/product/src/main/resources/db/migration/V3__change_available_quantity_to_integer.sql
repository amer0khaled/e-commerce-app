ALTER TABLE product
    ALTER COLUMN available_quantity TYPE INTEGER
        USING available_quantity::INTEGER;
