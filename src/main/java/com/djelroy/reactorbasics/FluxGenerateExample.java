package com.djelroy.reactorbasics;

import org.apache.commons.lang3.RandomStringUtils;

import reactor.core.publisher.Flux;

public class FluxGenerateExample {

	public static final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {

		// Synchronous and one-by-one emission
		Flux<String> flux = Flux.generate(() -> 0, (state, sink) -> {
			sink.next(String.format("10 x %d = %d", state, 10 * state));
			if (state == 5)
				sink.complete();
			return state + 1;
		}, state -> System.out.println("State's last value  " + state));

		flux.subscribe(System.out::println);

		// Mutable state variant
		Flux<String> fluxMutable = Flux.generate(StringBuffer::new, (state, sink) -> {
			state.append(RandomStringUtils.randomAlphanumeric(5)); sink.next("Buffer: " + state.toString());
			if (state.length() > 100)
				sink.complete();
			return state;
		});
		fluxMutable.subscribe(System.out::println);
	}

}
