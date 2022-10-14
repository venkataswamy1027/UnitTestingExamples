package com.test;

import com.test.model.WordDictionary;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class WordDictionaryExceptionTest {

	@Test
	public void nonVoidMethodThrowingExTest() {
		try {
			WordDictionary wordDictionary = mock(WordDictionary.class);
			when(wordDictionary.getMeaning(anyString())).thenThrow(new NullPointerException());
			wordDictionary.getMeaning("awesome");
		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}
	
	@Test
	public void voidMethodThrowingExTest() {
		try {
			WordDictionary wordDictionary = mock(WordDictionary.class);
			doThrow(new IllegalArgumentException("Error Occured")).when(wordDictionary).add(anyString(), anyString());
			wordDictionary.add(anyString(), anyString());
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	@Test
	public void nonVoidMethodThrowingExSpyTest() {
		try {
			WordDictionary wordDictionary=new WordDictionary();
			WordDictionary spyWd = spy(wordDictionary);
			when(spyWd.getMeaning(anyString())).thenThrow(new NullPointerException());
			wordDictionary.getMeaning("awesome");
		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}
}
