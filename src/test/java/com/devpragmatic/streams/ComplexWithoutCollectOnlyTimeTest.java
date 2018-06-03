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

import static com.devpragmatic.streams.utils.DataInputUtils.getInputData;
import static org.junit.Assert.assertEquals;

public class ComplexWithoutCollectOnlyTimeTest {
    private static List<String> ARRAYS_OF_UNSORTED_STRINGS;
    private static final Predicate<String> START_WITH_PREDICATE = str -> str.startsWith("0");
    private static final Predicate<String> HAS_4_PREDICATE = str -> str.contains("4");
    private static final Predicate<String> HAS_6_PREDICATE = str -> str.contains("6");
    private static final Predicate<String> HAS_O_PREDICATE = str -> str.contains("O");
    private List<String> arraysToSort;

    @BeforeClass
    public static void prepareData() throws IOException {
        ARRAYS_OF_UNSORTED_STRINGS = getInputData();
    }

    @Before
    public void unsortedString(){
        arraysToSort = new ArrayList<>(ARRAYS_OF_UNSORTED_STRINGS);
    }

    @Test
    public void useSequenticalStreamSort() {
        new StreamSequentical(arraysToSort)
                .dict()
                .filter(START_WITH_PREDICATE)
                .filter(HAS_4_PREDICATE)
                .filter(HAS_6_PREDICATE)
                .filter(HAS_O_PREDICATE)
                .sort();
    }

    @Test
    public void useParallelStreamSort(){
        new StreamParallel(arraysToSort)
                .dict()
                .filter(START_WITH_PREDICATE)
                .filter(HAS_4_PREDICATE)
                .filter(HAS_6_PREDICATE)
                .filter(HAS_O_PREDICATE)
                .sort();
    }

    @Test
    public void useWithoutStreamFlow(){
        new WithoutStreamFlow(arraysToSort)
                .dict()
                .filter(START_WITH_PREDICATE)
                .filter(HAS_4_PREDICATE)
                .filter(HAS_6_PREDICATE)
                .filter(HAS_O_PREDICATE)
                .sort();
    }
}
