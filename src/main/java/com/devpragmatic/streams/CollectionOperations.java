package com.devpragmatic.streams;

import java.util.List;
import java.util.function.Predicate;

public interface CollectionOperations {
    CollectionOperations sort();

    CollectionOperations filter(Predicate<String> predicate);

    CollectionOperations dict();

    List<String> toCollect();
}
