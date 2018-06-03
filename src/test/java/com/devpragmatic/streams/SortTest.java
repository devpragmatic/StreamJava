package com.devpragmatic.streams;

import com.devpragmatic.streams.impl.StreamParallel;
import com.devpragmatic.streams.impl.StreamSequentical;
import com.devpragmatic.streams.impl.WithoutStreamFlow;
import com.devpragmatic.streams.utils.DataInputUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortTest {
    private static List<String> ARRAYS_OF_UNSORTED_STRINGS;
    private static List<String> ARRAYS_OF_SORTED_STRINGS;
    private List<String> arraysToSort;

    @BeforeClass
    public static void prepareData() throws IOException {
        ARRAYS_OF_UNSORTED_STRINGS = DataInputUtils.getInputData();
        ARRAYS_OF_SORTED_STRINGS = DataInputUtils.getSortedData();
    }

    @Before
    public void unsortedString() {
        arraysToSort = new ArrayList<>(ARRAYS_OF_UNSORTED_STRINGS);
    }

    @Test
    public void useSequenticalStreamSort_shouldBeSorted() {
        List<String> result = new StreamSequentical(arraysToSort).sort().toCollect();

        assertEquals(ARRAYS_OF_SORTED_STRINGS, result);
    }

    @Test
    public void useParallelStreamSort_shouldBeSorted() {
        List<String> result = new StreamParallel(arraysToSort).sort().toCollect();

        assertEquals(ARRAYS_OF_SORTED_STRINGS, result);
    }

    @Test
    public void useWithoutStreamFlow_shouldBeSorted() {
        List<String> result = new WithoutStreamFlow(arraysToSort).sort().toCollect();

        assertEquals(ARRAYS_OF_SORTED_STRINGS, result);
    }


}
