package com.devpragmatic.streams;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.devpragmatic.streams.utils.DataInputUtils.getComplextExpectedResultData;
import static com.devpragmatic.streams.utils.DataInputUtils.getInputData;
import static org.junit.Assert.assertEquals;

public class OnMethodUseTest {
    private static final Predicate<String> START_WITH_PREDICATE = str -> str.startsWith("0");
    private static final Predicate<String> HAS_4_PREDICATE = str -> str.contains("4");
    private static final Predicate<String> HAS_6_PREDICATE = str -> str.contains("6");
    private static final Predicate<String> HAS_O_PREDICATE = str -> str.contains("O");
    private static List<String> ARRAYS_OF_UNSORTED_STRINGS;
    private static List<String> ARRAYS_OF_EXPECTED_RESULT_STRINGS;
    private List<String> arraysToSort;

    @BeforeClass
    public static void prepareData() throws IOException {
        ARRAYS_OF_UNSORTED_STRINGS = getInputData();
        ARRAYS_OF_EXPECTED_RESULT_STRINGS = getComplextExpectedResultData();
    }

    @Before
    public void unsortedString() {
        arraysToSort = new ArrayList<>(ARRAYS_OF_UNSORTED_STRINGS);
    }

    @Test
    public void goodUse_shouldTakeLessTime_butThisTakeMoreTime() {
        Stream<String> dict = arraysToSort.stream().distinct();
        Stream<String> filtered = dict.filter(START_WITH_PREDICATE);
        Stream<String> filtered1 = filtered.filter(HAS_4_PREDICATE);
        Stream<String> filtered2 = filtered1.filter(HAS_6_PREDICATE);
        Stream<String> filtered3 = filtered2.filter(HAS_O_PREDICATE);
        Optional<String> filteredAndSorted = filtered3.sorted().findFirst();

        assertEquals(ARRAYS_OF_EXPECTED_RESULT_STRINGS.get(0), filteredAndSorted.get());
    }

    @Test
    public void badUse_shouldTakeMoreTime_butThisTakeLessTime() {
        List<String> dict = arraysToSort.stream().distinct().collect(Collectors.toList());
        List<String> filtered = dict.stream().filter(START_WITH_PREDICATE).collect(Collectors.toList());
        List<String> filtered1 = filtered.stream().filter(HAS_4_PREDICATE).collect(Collectors.toList());
        List<String> filtered2 = filtered1.stream().filter(HAS_6_PREDICATE).collect(Collectors.toList());
        List<String> filtered3 = filtered2.stream().filter(HAS_O_PREDICATE).collect(Collectors.toList());
        Optional<String> filteredAndSorted = filtered3.stream().sorted().findFirst();

        assertEquals(ARRAYS_OF_EXPECTED_RESULT_STRINGS.get(0), filteredAndSorted.get());
    }
}
