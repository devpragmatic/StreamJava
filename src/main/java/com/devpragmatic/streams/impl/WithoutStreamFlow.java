package com.devpragmatic.streams.impl;

import com.devpragmatic.streams.CollectionOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class WithoutStreamFlow implements CollectionOperations {

    private List<String> stream;

    public WithoutStreamFlow(List<String> arrays) {
        stream = arrays;
    }

    @Override
    public WithoutStreamFlow sort() {
        Collections.sort(stream);
        return this;
    }

    @Override
    public CollectionOperations filter(Predicate<String> predicate) {
        stream.removeIf(predicate.negate());
        return this;
    }

    @Override
    public CollectionOperations dict() {
        stream = new ArrayList<>(new HashSet<>(stream));
        return this;
    }

    public List<String> toCollect() {
        return stream;
    }
}
