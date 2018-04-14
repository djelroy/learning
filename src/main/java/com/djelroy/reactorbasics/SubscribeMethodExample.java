package com.djelroy.reactorbasics;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Shows the 5 different ways to call the subscribe() method
 * 
 * @author DJ Elroy
 *
 */
public class SubscribeMethodExample {

	public static void main(String[] args) throws InterruptedException {

		// Does nothing except subscribing
		Flux.range(1, 2).subscribe();

		// Prints 1, 2 and 3 on each line
		Flux.range(1, 3).subscribe(System.out::println);

		// Prints the thrown exception with its message
		Flux.just("India", "Sri Lanka", "Thailand", "Cambodia", "Brazil").map(country -> {
			if (country.equals("Brazil"))
				return country;
			throw new RuntimeException("Country not visited yet");
		}).subscribe(System.out::println, System.err::println);

		// Prints a list of countries with a final text at the end with an error handler
		Flux.just("France", "Canada", "Philippines", "Italy", "Sri Lanka", "Thailand", "Cambodia", "Germany",
				"Malaysia").subscribe(country -> System.out.println("I've lived in or visited " + country),
						System.err::println, () -> System.out.println("I need to add more countries!!!"));

		// Using a custom Subscriber
		CustomSubscriber<Integer> subs = new CustomSubscriber<>();
		Flux<Integer> range = Flux.range(1, 5);

//		range.subscribe(System.out::println, System.err::println, () -> {
//			System.out.println("Done");
//		}, s -> subs.request(10));
		range.subscribe(subs);

	}

	public static class CustomSubscriber<T> extends BaseSubscriber<T> {
		public void hookOnSubscribe(Subscription subscription) {
			System.out.println("Subscribed");
			request(1);
		}

		public void hookOnNext(T value) {
			System.out.println(value);
			request(1);
		}
		
		public void hookOnComplete() {
			System.out.println("Completed");
		}
	}
}
