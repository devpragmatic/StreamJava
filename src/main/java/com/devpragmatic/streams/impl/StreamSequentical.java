package com.devpragmatic.streams.impl;

import com.devpragmatic.streams.CollectionOperations;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSequentical implements CollectionOperations {

    Stream<String> stream;

    public StreamSequentical(List<String> arrays) {
        stream = arrays.stream();
    }

    @Override
    public StreamSequentical sort() {
        stream = stream.sorted();
        return this;
    }

    @Override
    public CollectionOperations filter(Predicate<String> predicate) {
        stream = stream.filter(predicate);
        return this;
    }

    @Override
    public CollectionOperations dict() {
        stream = stream.distinct();
        return this;
    }

    @Override
    public List<String> toCollect() {
        return stream.collect(Collectors.toList());
    }
}
