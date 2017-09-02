package com.mycom.myapp;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {

		System.out.println("Processing..." + item);
		return item;
	}

}