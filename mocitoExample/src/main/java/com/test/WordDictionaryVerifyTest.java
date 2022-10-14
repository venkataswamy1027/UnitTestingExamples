package com.test;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.test.model.WordDictionary;

public class WordDictionaryVerifyTest {

	@Test
	public void verify1Test() {
		WordDictionary wd = mock(WordDictionary.class);
		wd.add("awesome", "very good");

		verify(wd).add("awesome", "very good");
		verify(wd, times(1)).add("awesome", "very good");
	}
	
	@Test
	public void verify2Test() {
		WordDictionary wd = mock(WordDictionary.class);
		wd.add("awesome", "very good");
		wd.add("ant", "insect");
		wd.add("book", "thing");

		verify(wd, atLeast(1)).add("awesome", "very good");
	}
}
