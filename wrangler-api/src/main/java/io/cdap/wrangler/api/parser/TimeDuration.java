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

public class TimeDuration implements Token {
    private final long millis;

    public TimeDuration(String value) {
        this.millis = parseMillis(value);
    }

    private long parseMillis(String str) {
        str = str.trim().toLowerCase();
        double num = Double.parseDouble(str.replaceAll("[^0-9.]", ""));
        if (str.endsWith("ms")) return (long) num;
        if (str.endsWith("s")) return (long) (num * 1000);
        if (str.endsWith("m")) return (long) (num * 60 * 1000);
        if (str.endsWith("h")) return (long) (num * 3600 * 1000);
        throw new IllegalArgumentException("Unknown time unit: " + str);
    }

    public long getMillis() {
        return this.millis;
    }

    @Override
    public Object value() {
        return millis;
    }

    @Override
    public TokenType type() {
        return TokenType.TIME_DURATION;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("type", TokenType.TIME_DURATION.name());
        object.addProperty("value", millis);
        return object;
    }
}
