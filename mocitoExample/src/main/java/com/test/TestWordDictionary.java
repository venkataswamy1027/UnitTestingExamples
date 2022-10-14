package com.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.test.model.WordDictionary;

@RunWith(MockitoJUnitRunner.class)
public class TestWordDictionary {

	@Mock
	private Map<String, String> wordMap;

	@InjectMocks
	private WordDictionary wordDictionary;

	@Test
	public void test() {
		Mockito.when(wordMap.get("awesome")).thenReturn("very good");
		assertEquals("very good", wordDictionary.getMeaning("awesome"));
	}
}
