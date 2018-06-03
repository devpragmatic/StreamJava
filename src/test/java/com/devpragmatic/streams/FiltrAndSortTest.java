package com.devpragmatic.streams;

import com.devpragmatic.streams.impl.StreamParallel;
import com.devpragmatic.streams.impl.StreamSequentical;
import com.devpragmatic.streams.impl.WithoutStreamFlow;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.devpragmatic.streams.utils.DataInputUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FiltrAndSortTest {

    private static final Predicate<String> PREDICATE = str -> str.startsWith("0");
    private static List<String> ARRAYS_OF_UNSORTED_STRINGS;
    private static List<String> ARRAYS_OF_EXPECTED_RESULT_STRINGS;
    private List<String> arraysToSort;

    @BeforeClass
    public static void prepareData() throws IOException {
        ARRAYS_OF_UNSORTED_STRINGS = getInputData();
        ARRAYS_OF_EXPECTED_RESULT_STRINGS = getFilteredData();
    }

    @Before
    public void unsortedString(){
        arraysToSort = new ArrayList<>(ARRAYS_OF_UNSORTED_STRINGS);
    }

    @Test
    public void useSequenticalStreamSort_shouldBeFilteredAndSorted() {
        List<String> result = new StreamSequentical(arraysToSort).filter(PREDICATE).sort().toCollect();

        assertEquals(ARRAYS_OF_EXPECTED_RESULT_STRINGS, result);
    }

    @Test
    public void useParallelStreamSort_shouldBeFilteredAndSorted(){
        List<String> result = new StreamParallel(arraysToSort).filter(PREDICATE).sort().toCollect();

        assertEquals(ARRAYS_OF_EXPECTED_RESULT_STRINGS, result);
    }

    @Test
    public void useWithoutStreamFlow_shouldBeFilteredAndSorted(){
        List<String> result = new WithoutStreamFlow(arraysToSort).filter(PREDICATE).sort().toCollect();

        assertEquals(ARRAYS_OF_EXPECTED_RESULT_STRINGS, result);
    }
}
