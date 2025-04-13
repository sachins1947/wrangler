/*
 * Copyright © 2025 <Your Name or Company>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.cdap.wrangler.api.annotations.PublicEvolving;

@PublicEvolving
public class ByteSize implements Token {
    private final long bytes;

    public ByteSize(String value) {
        this.bytes = parseBytes(value);
    }

    private long parseBytes(String str) {
        str = str.trim().toUpperCase();
        double num = Double.parseDouble(str.replaceAll("[^0-9.]", ""));
        if (str.endsWith("KB")) return (long)(num * 1024);
        if (str.endsWith("MB")) return (long)(num * 1024 * 1024);
        if (str.endsWith("GB")) return (long)(num * 1024 * 1024 * 1024);
        if (str.endsWith("B")) return (long)(num);
        throw new IllegalArgumentException("Unknown byte size unit: " + str);
    }

    public long getBytes() {
        return this.bytes;
    }

    @Override
    public Object value() {
        return bytes;
    }

    @Override
    public TokenType type() {
       return TokenType.BYTE_SIZE;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("type", TokenType.BYTE_SIZE.name());
        object.addProperty("value", bytes);
        return object;
    }
}

