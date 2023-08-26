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

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        Scanner sc = new Scanner(System.in);

        ArrayList<Double> sales = new ArrayList<>();
        ArrayList<SalesPerson> salesPeople = new ArrayList<>();

        String name;
        String title;
        int people;
        int count;
        Double sale;

        System.out.print("How many sales people do you need to enter in: ");
        people = sc.nextInt();
        sc.nextLine();
        System.out.println();

        do
        {
            sales.clear();
            System.out.print("Please enter sales person name: ");
            name = sc.nextLine();

            System.out.print("Please enter your sales person title: ");
            title = sc.nextLine();

            System.out.print("How many sales values will you "
                    + "enter for this sales person? ");
            count = sc.nextInt();
            sc.nextLine();
            System.out.println();

            while (count != 0)
            {
                sale = enterSales(name);
                sales.add(sale);
                count--;
            }

            SalesPerson sp = new SalesPerson();

            sp.setName(name);
            sp.setTitle(title);
            sp.setSales(sales);

            salesPeople.add(sp);

            System.out.println();

            people--;
        } while (people > 0);

        sc.close();

        createReport(salesPeople);
    }

    private static Double enterSales(String name)
    {
        Double sale;

        System.out.print("Please enter sales figure for " + name + ": ");
        sale = sc.nextDouble();
        sc.nextLine();

        return sale;
    }

    public static void createReport(ArrayList<SalesPerson> salesPeople)
    {
        double companyTotal = 0.0;
        DecimalFormat fmt = new DecimalFormat("$#,#00.00");
        Iterator<SalesPerson> iterPerson = salesPeople.iterator();

        while (iterPerson.hasNext())
        {
            SalesPerson s = iterPerson.next();
            double total = 0.0;
            double sale = 0.0;
            double min = 9999999.9;
            double max = 0.0;
            double avg = 0.0;
            int count = 0;

            Iterator<Double> iterSales = s.iterSales();
            while (iterSales.hasNext())
            {
                sale = iterSales.next();
                total += sale;

                if (sale < min)
                {
                    min = sale;
                }

                if (sale > max)
                {
                    max = sale;
                }

                count++;
            }

            companyTotal += total;
            avg = (total / count);

            System.out.println("Sales person: " + s.getName());
            System.out.println("Title: " + s.getTitle());
            System.out.println("Total Sales: " + fmt.format(total));
            System.out.println("Min Sales: " + fmt.format(min));
            System.out.println("Max Sales: " + fmt.format(max));
            System.out.println("Average Sales: " + fmt.format(avg));
            System.out.println("----------------------------------");
            System.out.println();
        }

        System.out.println("Company total sales: " + fmt.format(companyTotal));
    }

}
