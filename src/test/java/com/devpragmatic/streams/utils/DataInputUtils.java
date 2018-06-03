package com.devpragmatic.streams.utils;

import com.devpragmatic.streams.SortTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataInputUtils {
    private static final String UNSORTED_STRINGS_FILE_NAME = "/inputData.txt";
    private static final String SORTED_STRINGS_FILE_NAME = "/sortedStrings.txt";
    private static final String FILTERED_START_WITH_0_STRINGS_FILE_NAME = "/filtredStartWith0Strings.txt";
    private static final String COMPLEX_EXPECTED_RESULT_STRINGS_FILE_NAME = "/complexExpectedResult.txt";

    private DataInputUtils() {
    }

    public static List<String> getInputData() throws IOException {
        return readLines(UNSORTED_STRINGS_FILE_NAME);
    }

    public static List<String> getFilteredData() throws IOException {
        return readLines(FILTERED_START_WITH_0_STRINGS_FILE_NAME);
    }

    public static List<String> getSortedData() throws IOException {
        return readLines(SORTED_STRINGS_FILE_NAME);
    }

    public static List<String> getComplextExpectedResultData() throws IOException {
        return readLines(COMPLEX_EXPECTED_RESULT_STRINGS_FILE_NAME);
    }

    private static List<String> readLines(String fileName) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(SortTest.class.getResource(fileName).getFile())))) {
            for(String line; (line = br.readLine()) != null; ) {
                list.add(line);
            }
        }
        return list;
    }
}
