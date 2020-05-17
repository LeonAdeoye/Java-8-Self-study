package com.leon.behaviourparam;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessorFunction
{
    String process(BufferedReader bufferedReader) throws IOException;
}
