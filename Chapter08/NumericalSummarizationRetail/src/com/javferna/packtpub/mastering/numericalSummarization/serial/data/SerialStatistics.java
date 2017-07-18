package com.javferna.packtpub.mastering.numericalSummarization.serial.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.javferna.packtpub.mastering.numericalSummarization.common.Invoice;
import com.javferna.packtpub.mastering.numericalSummarization.common.Record;

public class SerialStatistics {

	public static void customersFromUnitedKingdom(List<Record> records) {
		System.out.println("****************************************");
		System.out.println("Customers from UnitedKingdom");
		Map<String, List<Record>> map = records.stream()
				.filter(r -> r.getCountry().equals("United Kingdom"))
				.collect(Collectors.groupingBy(Record::getCustomer));

		map.forEach((k, l) -> System.out.println(k + ": " + l.size()));
		System.out.println("****************************************");
	}

	public static void quantityFromUnitedKingdom(List<Record> records) {

		System.out.println("****************************************");
		System.out.println("Age info for Deposit suscribers");
		DoubleSummaryStatistics statistics = records.stream()
				.filter(r -> r.getCountry().equals("United Kingdom"))
				.collect(Collectors.summarizingDouble(Record::getQuantity));


		System.out.println("Min: " + statistics.getMin());
		System.out.println("Max: " + statistics.getMax());
		System.out.println("Average: " + statistics.getAverage());
		System.out.println("****************************************");
	}

	
	public static void countriesForProduct(List<Record> records) {

		System.out.println("****************************************");
		System.out.println("Countries for product 85123A");

		records.stream().filter(r -> r.getStockCode().equals("85123A"))
				.map(r -> r.getCountry())
				.distinct()
				.sorted()
				.forEach(System.out::println);
		System.out.println("****************************************");
	}

	public static void quantityForProduct(List<Record> records) {

		System.out.println("****************************************");
		System.out.println("Quantity for Product");

		IntStream stream = records.stream()
				.filter(r ->  r.getStockCode().equals("85123A"))
				.mapToInt(r -> r.getQuantity());

		System.out
				.println("Max quantity: " + stream.max().getAsInt());
		System.out
				.println("Min quantity: " + stream.min().getAsInt());
		System.out.println("****************************************");
	}

	
	public static void quantityForProductOk(List<Record> records) {

		System.out.println("****************************************");
		System.out.println("Quantity for Product Ok");
		int value = records.stream()
				.filter(r ->  r.getStockCode().equals("85123A"))
				.mapToInt(r -> r.getQuantity())
				.max()
				.getAsInt();

		System.out.println("Max quantity: " + value);

		value = records.stream()
				.filter(r ->  r.getStockCode().equals("85123A"))
				.mapToInt(r -> r.getQuantity())
				.min()
				.getAsInt();

		System.out.println("Min quantity: " + value);
		System.out.println("****************************************");
	}

	
	public static void multipleFilterData(List<Record> records) {

		System.out.println("****************************************");
		System.out.println("Multiple Filter");

		Stream<Record> stream1 = records.stream()
				.filter(r -> r.getQuantity() > 50);
		Stream<Record> stream2 = records.stream()
				.filter(r -> r.getUnitPrice() > 10);

		Stream<Record> complete = Stream.concat(stream1, stream2);

		Long value = complete.map(r -> r.getStockCode()).distinct().count();

		System.out.println("Number of products: " + value);
		System.out.println("****************************************");
	}

	public static void multipleFilterDataPredicate (List<Record> records) {
		
		System.out.println("****************************************");
		System.out.println("Multiple filter with Predicate");

		Predicate<Record> p1 = r -> r.getQuantity() > 50;
		Predicate<Record> p2 = r -> r.getUnitPrice() > 10;


		Predicate<Record> pred = Stream.of(p1, p2)
					.reduce(Predicate::or).get();

		long value = records.stream().filter(pred).count();

		System.out.println("Number of people: " + value);
		System.out.println("****************************************");
	}

	
	
	public static void getBiggestInvoiceAmmounts(List<Record> records) {

		System.out.println("****************************************");
		System.out.println("Biggest Invoice Ammounts");
		Date inicio, fin;
		
		inicio = new Date();
		Map<String, List<Record>> map = records.stream().collect(Collectors.groupingBy(r -> r.getId()));
		fin = new Date();
		System.out.println("Fase 1: "+(fin.getTime()-inicio.getTime()));
	
		List<Invoice> invoices= new ArrayList();
		map.values().stream().forEach(list  -> {
			Invoice invoice = new Invoice();
			invoice.setId(list.get(0).getId());
			double ammount=list.stream().mapToDouble(r -> r.getUnitPrice()* r.getQuantity()).sum();
			invoice.setAmmount(ammount);
			invoice.setCustomerId(list.get(0).getCustomer());
			
			invoices.add(invoice);
		});
		
		
		invoices.stream()
			.sorted(Comparator.comparingDouble(Invoice::getAmmount).reversed())
			.limit(10)
			.forEach(
					i -> System.out.println("Customer: "
							+ i.getCustomerId() + "; Ammount: "
							+ i.getAmmount()));
		System.out.println("****************************************");
	}


	public static void productsBetween1and10(List<Record> records) {

		System.out.println("****************************************");
		System.out.println("Products between 1 and 10");
		int count=records.stream()
			.filter(r -> (r.getUnitPrice() >=1 ) && (r.getUnitPrice() <=10))
			.map(i -> i.getStockCode())
			.distinct()
			.mapToInt(a -> 1)
			.reduce(0, Integer::sum);
		System.out.println("Products between 1 and 10: "+count);
		System.out.println("****************************************");
	}	
}
