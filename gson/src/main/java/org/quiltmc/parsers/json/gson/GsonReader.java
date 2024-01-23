/*
 * Copyright 2023 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.quiltmc.parsers.json.gson;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.quiltmc.parsers.json.FormatViolationException;
import org.quiltmc.parsers.json.MalformedSyntaxException;
import org.quiltmc.parsers.json.ParseException;

import java.io.IOException;
import java.io.Reader;

/**
 * Adapts a quilt-parsers' {@link org.quiltmc.parsers.json.JsonReader} to be used with {@link com.google.gson.Gson}
 */
public class GsonReader extends JsonReader {
    private final org.quiltmc.parsers.json.JsonReader delegate;

    /**
     * Creates a new instance that reads a JSON-encoded stream from {@code in}.
     *
     */
    public GsonReader(org.quiltmc.parsers.json.JsonReader reader) {
        super(Reader.nullReader());
        this.delegate = reader;
    }

    public org.quiltmc.parsers.json.JsonReader getDelegate() {
        return delegate;
    }

    @FunctionalInterface
    private interface DelegateAction {
        void call() throws IOException;
    }

    @FunctionalInterface
    private interface DelegateFunction<T> {
        T call() throws IOException;
    }

    // these next interfaces are not strictly necessary, but they avoid boxing
    // (if Java let primitives be generic parameters like C#, these wouldn't be needed.
    //  Project Panama when)

    @FunctionalInterface
    private interface DelegateBooleanFunction {
        boolean call() throws IOException;
    }

    @FunctionalInterface
    private interface DelegateIntFunction {
        int call() throws IOException;
    }

    @FunctionalInterface
    private interface DelegateLongFunction {
        long call() throws IOException;
    }

    @FunctionalInterface
    private interface DelegateDoubleFunction {
        double call() throws IOException;
    }

    private void rethrowGsonExceptions(DelegateAction action) throws IOException {
        try {
            action.call();
        } catch (MalformedSyntaxException | FormatViolationException e) {
            throw new JsonSyntaxException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private <T> T rethrowGsonExceptions(DelegateFunction<T> func) throws IOException {
        try {
            return func.call();
        } catch (MalformedSyntaxException | FormatViolationException e) {
            throw new JsonSyntaxException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private boolean rethrowGsonExceptions(DelegateBooleanFunction func) throws IOException
    {
        try {
            return func.call();
        } catch (MalformedSyntaxException | FormatViolationException e) {
            throw new JsonSyntaxException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private int rethrowGsonExceptions(DelegateIntFunction func) throws IOException
    {
        try {
            return func.call();
        } catch (MalformedSyntaxException | FormatViolationException e) {
            throw new JsonSyntaxException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private long rethrowGsonExceptions(DelegateLongFunction func) throws IOException
    {
        try {
            return func.call();
        } catch (MalformedSyntaxException | FormatViolationException e) {
            throw new JsonSyntaxException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private double rethrowGsonExceptions(DelegateDoubleFunction func) throws IOException
    {
        try {
            return func.call();
        } catch (MalformedSyntaxException | FormatViolationException e) {
            throw new JsonSyntaxException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    @Override
    public void beginArray() throws IOException {
        rethrowGsonExceptions(delegate::beginArray);
    }

    @Override
    public void endArray() throws IOException {
        rethrowGsonExceptions(delegate::endArray);
    }

    @Override
    public void beginObject() throws IOException {
        rethrowGsonExceptions(delegate::beginObject);
    }

    @Override
    public void endObject() throws IOException {
        rethrowGsonExceptions(delegate::endObject);
    }

    @Override
    public boolean hasNext() throws IOException {
        return rethrowGsonExceptions(delegate::hasNext);
    }

    @Override
    public JsonToken peek() throws IOException {
        var quiltToken = rethrowGsonExceptions(delegate::peek);

        switch (quiltToken) {
            case BEGIN_ARRAY:
                return JsonToken.BEGIN_ARRAY;
            case END_ARRAY:
                return JsonToken.END_ARRAY;
            case BEGIN_OBJECT:
                return JsonToken.BEGIN_OBJECT;
            case END_OBJECT:
                return JsonToken.END_OBJECT;
            case NAME:
                return JsonToken.NAME;
            case STRING:
                return JsonToken.STRING;
            case NUMBER:
                return JsonToken.NUMBER;
            case BOOLEAN:
                return JsonToken.BOOLEAN;
            case NULL:
                return JsonToken.NULL;
            case END_DOCUMENT:
                return JsonToken.END_DOCUMENT;
            default:
                throw new IllegalStateException("Delegate returned unrecognized token " + quiltToken);
        }
    }

    @Override
    public String nextName() throws IOException {
        return rethrowGsonExceptions(delegate::nextName);
    }

    @Override
    public String nextString() throws IOException {
        return rethrowGsonExceptions(delegate::nextString);
    }

    @Override
    public boolean nextBoolean() throws IOException {
        return rethrowGsonExceptions(delegate::nextBoolean);
    }

    @Override
    public void nextNull() throws IOException {
        rethrowGsonExceptions(delegate::nextNull);
    }

    @Override
    public double nextDouble() throws IOException {
        return rethrowGsonExceptions(delegate::nextDouble);
    }

    @Override
    public long nextLong() throws IOException {
        return rethrowGsonExceptions(delegate::nextLong);
    }

    @Override
    public int nextInt() throws IOException {
        return rethrowGsonExceptions(delegate::nextInt);
    }

    @Override
    public void close() throws IOException {
        delegate.close();
        super.close();
    }

    @Override
    public void skipValue() throws IOException {
        rethrowGsonExceptions(delegate::skipValue);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public String getPreviousPath() {
        return delegate.getPreviousPath();
    }

    @Override
    public String getPath() {
        return delegate.getPath();
    }
}
