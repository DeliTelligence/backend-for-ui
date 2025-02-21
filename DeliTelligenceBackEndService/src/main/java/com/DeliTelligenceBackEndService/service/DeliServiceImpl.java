package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.*;

import com.DeliTelligenceBackEndService.entitymodel.mapper.DeliSaleMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.DeliProductRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.DeliSaleRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.EmployeeRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleInputDto;
import com.DeliTelligenceBackEndService.enumformodel.PortionType;
import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import com.DeliTelligenceBackEndService.enumformodel.SaleType;
import com.DeliTelligenceBackEndService.enumformodel.StandardType;
import com.DeliTelligenceBackEndService.exception.ProductNotWeighedException;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DeliServiceImpl {

    private final DeliSaleRepository deliSaleRepository;
    private final DeliProductRepository deliProductRepository;
    private final DeliSaleMapper deliSaleMapper;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final EmployeeRepository employeeRepository;


    public DeliServiceImpl(DeliSaleRepository deliSaleRepository, DeliProductRepository deliProductRepository, DeliSaleMapper deliSaleMapper, EmployeeService employeeService, ProductService productService, EmployeeRepository employeeRepository) {
        this.deliSaleRepository = deliSaleRepository;
        this.deliProductRepository = deliProductRepository;
        this.deliSaleMapper = deliSaleMapper;
        this.employeeService = employeeService;
        this.productService = productService;
        this.employeeRepository = employeeRepository;
    }

    public String CreateSale(DeliSaleInputDto inputDto) {
        DeliSale deliSale = deliSaleMapper.toDeliSale(inputDto, employeeService);
        if (deliSale.getSaleWeight() == 0){
            throw new ProductNotWeighedException(deliSale.getDeliProduct().getDeliProduct().getProductName(), deliSale.getSaleWeight());
        }
        DeliSale deliSaleToSave = deliSaleRepository.save(deliSale);


        Employee employee = employeeService.getEmployeeByID(deliSaleToSave.getEmployee().getId());
        if (employee.getTotalTransactions() == 0) {
            employee.setTotalTransactions(1);
        }
        else {
            employee.setTotalTransactions(employee.getTotalTransactions() + 1);
        }

        if (employee.getTotalWastePercentage() == 0){
            employee.setTotalWastePercentage(deliSaleToSave.getDifferenceWeight());
        }
        else {
            employee.setTotalWastePercentage(employee.getTotalWastePercentage() + deliSaleToSave.getDifferenceWeight());
        }
        employeeRepository.save(employee);

        return "Done";
    }

    public List<DeliSale> getAllSalesByDate(LocalDate date) {

        return deliSaleRepository.findBySaleDate(date);
    }

    public List<DeliSale> getAllSalesByDatesBetween(LocalDate date1, LocalDate date2) {

        return deliSaleRepository.findBySaleDateBetween(date1, date2);
    }

   public String createTestSales(){
        int recordCounterDeliSale = 10;
        List<Product> madeFoodHot = productService.getProductsByTypeReal(ProductType.MADE_FOOD_HOT);
        List<Product> madeColdFood = productService.getProductsByTypeReal(ProductType.MADE_FOOD_COLD);
        List<Product> coldFoods = productService.getProductsByTypeReal(ProductType.COLD_FOOD);
        List<Product> hotFoods = productService.getProductsByTypeReal(ProductType.HOT_FOOD);
        List<Product> mainFillings = productService.getProductsByTypeReal(ProductType.MAIN_FILLING_FOOD);
        List<Product> breakfastFoods = productService.getProductsByTypeReal(ProductType.BREAKFAST_FOOD);
        List<Product> saladFoods = productService.getProductsByTypeReal(ProductType.SALAD);
        List<Product> breadFoods = productService.getProductsByTypeReal(ProductType.BREAD);
        List<Product> hotFoodToGo = productService.getProductsByTypeReal(ProductType.TO_GO_BAG);

        List<Employee> employees = employeeService.getAllEmployeesReal();

       List<Product> allDeliProducts = new ArrayList<>();
       allDeliProducts.addAll(hotFoodToGo);
       allDeliProducts.addAll(madeFoodHot);
       allDeliProducts.addAll(madeColdFood);
       allDeliProducts.addAll(saladFoods);

       List<Product> fillingsForMadeProduct = new ArrayList<>();
       fillingsForMadeProduct.addAll(mainFillings);
       fillingsForMadeProduct.addAll(coldFoods);

       List<Product> fillingsForSalad = new ArrayList<>();
       fillingsForSalad.addAll(fillingsForMadeProduct);
       fillingsForSalad.addAll(hotFoods);

       List<Product> foodForToGo = new ArrayList<>();
       foodForToGo.addAll(breakfastFoods);
       foodForToGo.addAll(hotFoods);
       foodForToGo.addAll(mainFillings);
       foodForToGo.addAll(coldFoods);

       List<SaleType> saleOptions = new ArrayList<>();
       saleOptions.add(SaleType.COLD_FOOD);
       saleOptions.add(SaleType.HOT_FOOD);


       for (int i = 0; i < recordCounterDeliSale; i++) {
            float combinedWeight = 0f;
            float salePrice = 0f;
            int quantity = 0;
            DeliSale deliSale = new DeliSale();
            DeliProduct deliProduct = new DeliProduct();

            int randomIndexEmployee = (int) (Math.random() * employees.size());
            Employee employee = employees.get(randomIndexEmployee);

            LocalDateTime randomDateTime = getRandomDateTime();
            LocalDate saleDate = randomDateTime.toLocalDate(); // Extract LocalDate
            Instant saleTime = randomDateTime.toInstant(ZoneOffset.UTC); // Convert to Instant

            // Set the sale time to the DeliSale object
            deliSale.setEmployee(employee);
            deliSale.setSaleTime(saleTime);
            deliSale.setSaleDate(saleDate);

            int randomIndexDeliProduct = (int) (Math.random() * allDeliProducts.size());
            Product deliProductInsert = allDeliProducts.get(randomIndexDeliProduct);
            deliProduct.setDeliProduct(deliProductInsert);
            quantity += 1;

            salePrice += deliProductInsert.getProductPrice();

            int randomSaleOption = (int) (Math.random() * saleOptions.size());


           if (deliProductInsert.getProductType() == ProductType.MADE_FOOD_HOT || deliProductInsert.getProductType() == ProductType.MADE_FOOD_COLD)
           {
               deliProduct.setPortionType(PortionType.FILLING);
               combinedWeight += (float) getStandardWeightOfProduct(deliProductInsert, StandardType.FILLING);

               SaleType saleType = saleOptions.get(randomSaleOption);
               deliSale.setSaleType(saleType);

               deliSale.setHandMade(true);

           }
            else if (deliProductInsert.getProductType() == ProductType.SALAD){
                deliProduct.setPortionType(PortionType.SALAD);
               combinedWeight += (float) getStandardWeightOfProduct(deliProductInsert, StandardType.SALAD);

               deliSale.setSaleType(SaleType.SALAD);

               deliSale.setHandMade(true);


           }
            else if (deliProductInsert.getProductType() == ProductType.TO_GO_BAG){
                deliProduct.setPortionType(PortionType.QUANTITY);
               combinedWeight += (float) getStandardWeightOfProduct(deliProductInsert, StandardType.TO_GO);

               SaleType saleType = saleOptions.get(randomSaleOption);
               deliSale.setSaleType(saleType);

               deliSale.setHandMade(false);

           }

            List<Ingredient> ingredients = new ArrayList<>();

            int amountOfIngredients = (int) (Math.random() * 5) + 1;

            if (deliProductInsert.getProductType() == ProductType.MADE_FOOD_HOT || deliProductInsert.getProductType() == ProductType.MADE_FOOD_COLD){
                Ingredient ingredient = new Ingredient();
                int randomMainIngredient = (int) (Math.random() * mainFillings.size());
                Product mainFilling = mainFillings.get(randomMainIngredient);

                combinedWeight += (float) getStandardWeightOfProduct(mainFilling, StandardType.FILLING);

                salePrice += mainFilling.getProductPrice();

                ingredient.setProduct(mainFilling);
                ingredient.setDeliProduct(deliProduct);

                amountOfIngredients -= 1;
                ingredients.add(ingredient);

                quantity += 1;
            }

            int randomIngredient = 0;

            for (int j = 0; j < amountOfIngredients; j++) {
                Ingredient ingredient = new Ingredient();
                ingredient.setDeliProduct(deliProduct);

                if (deliProductInsert.getProductType() == ProductType.MADE_FOOD_HOT || deliProductInsert.getProductType() == ProductType.MADE_FOOD_COLD){
                     randomIngredient = (int) (Math.random() * fillingsForMadeProduct.size());
                     Product product = fillingsForMadeProduct.get(randomIngredient);

                     combinedWeight += (float) getStandardWeightOfProduct(product, StandardType.FILLING);

                     salePrice += product.getProductPrice();

                     ingredient.setProduct(product);
                     ingredients.add(ingredient);

                     quantity += 1;
                }
                if (deliProductInsert.getProductType() == ProductType.SALAD){
                    randomIngredient = (int) (Math.random() * fillingsForSalad.size());
                    Product product = fillingsForSalad.get(randomIngredient);

                    combinedWeight += (float) getStandardWeightOfProduct(product, StandardType.SALAD);

                    salePrice += product.getProductPrice();

                    ingredient.setProduct(product);
                    ingredients.add(ingredient);

                    quantity += 1;

                }
                if (deliProductInsert.getProductType() == ProductType.TO_GO_BAG){
                    randomIngredient = (int) (Math.random() * foodForToGo.size());
                    Product product = foodForToGo.get(randomIngredient);

                    combinedWeight += (float) getStandardWeightOfProduct(product, StandardType.TO_GO);

                    salePrice += product.getProductPrice();

                    ingredient.setProduct(product);
                    ingredients.add(ingredient);

                    quantity += 1;

                }

            }
            deliProduct.setIngredients(ingredients);
            deliProduct.setCombinedWeight(combinedWeight);

            deliSale.setSalePrice(salePrice);
            deliSale.setQuantity(quantity);
//            deliSale.setDeliProduct(deliProduct);

            float randomFactor = (float) ((Math.random() * 0.40) - 0.20);

            float saleWeight = combinedWeight + (combinedWeight * randomFactor);

            deliSale.setSaleWeight(saleWeight);

            float differenceWeight = saleWeight - combinedWeight;
            deliSale.setDifferenceWeight(differenceWeight);

            float wastePerSale = (differenceWeight * salePrice) / 1000;
            deliSale.setWastePerSaleValue(wastePerSale);

//            deliProduct.setSale(deliSale);
            DeliSale deliSaleToSave = deliSaleRepository.save(deliSale);

            deliProduct.setSale(deliSaleToSave);
            deliProductRepository.save(deliProduct);

            int countRecord = employee.getTotalTransactions() + 1;
            employee.setTotalTransactions(countRecord);

            float employeeWaste = employee.getTotalWastePercentage() + differenceWeight;
            employee.setTotalWastePercentage(employeeWaste);
            employeeRepository.save(employee);


        }
       return "Sales Data Inserted";

   }

    private LocalDateTime getRandomDateTime() {
        Random random = new Random();

        // Random day between Monday and Sunday
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        int daysToAdd = random.nextInt(7); // Will pick a day from Monday to Sunday
        LocalDate randomDate = startOfWeek.plusDays(daysToAdd);

        // Random time between 6 AM and 5 PM
        LocalTime startTime = LocalTime.of(6, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        int startMinutes = startTime.toSecondOfDay() / 60;
        int endMinutes = endTime.toSecondOfDay() / 60;
        int randomMinutes = startMinutes + random.nextInt(endMinutes - startMinutes + 1);
        LocalTime randomTime = LocalTime.of(randomMinutes / 60, randomMinutes % 60);

        return LocalDateTime.of(randomDate, randomTime);
    }

    public double getStandardWeightOfProduct(Product product, StandardType standardType) {
        for (StandardWeightProduct standardWeightProduct : product.getStandardWeightProducts()) {
            if (standardWeightProduct.getStandardWeight().getStandardType() == standardType){
                return standardWeightProduct.getStandardWeightValue();
            }
        }
        return 0.0;
    }

    public List<DeliSale> getAllSales(){
        return deliSaleRepository.findAll();
    }



}
