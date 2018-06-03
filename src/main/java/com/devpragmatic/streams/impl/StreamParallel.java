package com.devpragmatic.streams.impl;

import java.util.List;

public class StreamParallel extends StreamSequentical {

    public StreamParallel(List<String> arrays) {
        super(arrays);
        stream = stream.parallel();
    }
}
