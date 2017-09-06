package com.mycom.myapp;

import org.springframework.batch.item.ItemProcessor;

import com.mycom.bitBatch.Main;

public class CustomItemProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		Main main = new Main();
		main.main();
		System.out.println("Processing...." + item);
		return item;
	}

}