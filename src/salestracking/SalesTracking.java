/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package salestracking;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author nechi
 */
public class SalesTracking
{
    // Create Scanner for input
    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        // Create temporary sales array
        ArrayList<Double> sales = new ArrayList<>();
        
        // Create array to hold all sales people
        ArrayList<SalesPerson> salesPeople = new ArrayList<>();

        // initialize values
        String name;
        String title;
        int people = 0;
        int count = 0;
        Double sale;

        // Information for how many sales people
        do
        {
            System.out.print("How many sales people do you need to enter in: ");

            // Check if input is an integer
            if (sc.hasNextInt())
            {
                people = sc.nextInt();
                
                // Check if input is less than 0 or greater than 10
                if (people <= 0)
                {
                    System.out.println("Please enter in a positive integer!");
                    System.out.println();
                    sc.nextLine();
                    continue;
                }
                else if (people > 10)
                {
                    System.out.println("You can only enter "
                            + "in up to 10 sales at a time!");
                    System.out.println();
                    people = 0;
                    sc.nextLine();
                    continue;
                }
                sc.nextLine();
                System.out.println();
                break;
            } else if (sc.hasNext())
            {
                System.out.println("Please enter in a positive integer!");
                System.out.println();
                people = 0;
                sc.nextLine();
                continue;
            } else
            {
                System.out.println("Please enter in a positive integer!");
                System.out.println();
                people = 0;
                sc.nextLine();
                continue;
            }
        } while (people <= 0 || !(sc.hasNextInt()) || people > 10);

        // Take information for sales person
        do
        {
            sales.clear();
            System.out.print("Please enter sales person name: ");
            name = sc.nextLine();

            // Check if input is a string in the isString method
            if (!(isString(name)))
            {
                continue;
            }

            System.out.print("Please enter your sales person title: ");
            title = sc.nextLine();

            // Check if input is a string in the isString method
            if (!(isString(title)))
            {
                continue;
            }

            // Take information for how many sales values per sales person
            do
            {
                System.out.print("How many sales values will you "
                        + "enter for this sales person? ");

                // Check if input is an integer
                if (sc.hasNextInt())
                {
                    count = sc.nextInt();
                    
                    // Check if input is less than 0 or greate than 10
                    if (count <= 0)
                    {
                        System.out.println("Please enter in a positive integer!");
                        System.out.println();
                        sc.nextLine();
                        continue;
                    } else if (count > 10)
                    {
                        System.out.println("You can only enter "
                                + "in up to 10 sales at a time!");
                        System.out.println();
                        count = 0;
                        sc.nextLine();
                        continue;
                    }
                    break;
                } else if (sc.hasNext())
                {
                    System.out.println("Please enter in a positive integer!");
                    System.out.println();
                    count = 0;
                    sc.nextLine();
                    continue;
                } else
                {
                    System.out.println("Please enter in a positive integer!");
                    System.out.println();
                    count = 0;
                    sc.nextLine();
                    continue;
                }
            } while (count <= 0 || !(sc.hasNextInt()) || count > 10);

            sc.nextLine();
            System.out.println();

            // Backup verification for how many sales to enter
            while (count != 0)
            {
                // Add sales values through enterSales method
                sale = enterSales(name);
                
                // Add sale to temp array
                sales.add(sale);
                count--;
            }

            // Create new SalesPerson Instance
            SalesPerson sp = new SalesPerson();

            sp.setName(name);
            sp.setTitle(title);
            sp.setSales(sales);

            // Add SalesPerson to salesPeople array
            salesPeople.add(sp);

            System.out.println();

            people--;
        } while (people > 0);

        sc.close();

        // Print sales for each sales person and company
        createReport(salesPeople);
    }

    // Enter sales for each SalesPerson
    private static Double enterSales(String name)
    {
        Double sale = 0.0;

        // Take information for each sales figure
        do
        {
            System.out.print("Please enter sales figure for " + name + ": ");

            // Check if input is a double
            if (sc.hasNextDouble())
            {
                sale = sc.nextDouble();
                
                // Check if sale is a negative number
                if (sale <= 0.0)
                {
                    System.out.println("Please enter in a positive sale!");
                    System.out.println();
                    sc.nextLine();
                    continue;
                }
                sc.nextLine();
                System.out.println();
                break;
            } else if (sc.hasNext())
            {
                System.out.println("Please enter in a sale!");
                System.out.println();
                sale = 0.0;
                sc.nextLine();
                continue;
            } else
            {
                System.out.println("Please enter in a positive sale!");
                System.out.println();
                sale = 0.0;
                sc.nextLine();
                continue;
            }
        } while ( sale <= 0.0 || !(sc.hasNextDouble()) );

        return sale;
    }

    // Create summary for company
    public static void createReport(ArrayList<SalesPerson> salesPeople)
    {
        // Create Company Sales Total
        double companyTotal = 0.0;
        
        // Formatting for currency
        DecimalFormat fmt = new DecimalFormat("$#,#00.00");
        
        // Create iterator of each SalesPerson from salesPeople array
        Iterator<SalesPerson> iterPerson = salesPeople.iterator();

        // While there is another sales person
        while (iterPerson.hasNext())
        {
            // Initiate variables
            SalesPerson s = iterPerson.next();
            double total = 0.0;
            double sale = 0.0;
            double min = 9999999.9;
            double max = 0.0;
            double avg = 0.0;
            int count = 0;

            /* Create iterator from sales in each 
               SalesPerson Object using the iterSales() method */
            Iterator<Double> iterSales = s.iterSales();
            
            // Loop while there are still sales figures
            while (iterSales.hasNext())
            {
                sale = iterSales.next();
                
                // Add sale to each SalesPerson sales total
                total += sale;

                // Set minimum sale
                if (sale < min)
                {
                    min = sale;
                }

                // Set maximum sale
                if (sale > max)
                {
                    max = sale;
                }

                count++;
            }

            // Add to company total from each SalesPerson sales
            companyTotal += total;
            
            // Average for each SalesPerson sales
            avg = (total / count);

            // Report to write to console about company summary
            System.out.println("Sales person: " + s.getName());
            System.out.println("Title: " + s.getTitle());
            System.out.println("Total Sales: " + fmt.format(total));
            System.out.println("Min Sales: " + fmt.format(min));
            System.out.println("Max Sales: " + fmt.format(max));
            System.out.println("Average Sales: " + fmt.format(avg));
            System.out.println("----------------------------------");
            System.out.println();
        }

        System.out.println("Company total sales: " 
                + fmt.format(companyTotal));
    }

    // Check if information is a string
    public static boolean isString(String input)
    {
        // Check if empty
        if (input.isEmpty())
        {
            System.out.println("Please enter in information!");
            System.out.println();
            return false;
        }

        for (char c : input.toCharArray())
        {
            if (!Character.isLetter(c))
            {
                System.out.println("Please only enter in letters!");
                System.out.println();
                return false;
            }
        }

        return true;
    }
}
