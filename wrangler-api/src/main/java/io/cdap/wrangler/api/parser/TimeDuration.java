package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TimeDuration implements Token {
    private final String raw;
    private final long nanos;

    public TimeDuration(String value) {
        this.raw = value;
        this.nanos = parseNanos(value);
    }

    private long parseNanos(String value) {
        String num = value.replaceAll("[^0-9.]", "");
        String unit = value.replaceAll("[0-9.]", "").toLowerCase();
        double val = Double.parseDouble(num);
        switch (unit) {
            case "ns": return (long) val;
            case "ms": return (long) (val * 1_000_000);
            case "s": return (long) (val * 1_000_000_000);
            case "m": return (long) (val * 60 * 1_000_000_000);
            case "h": return (long) (val * 3600 * 1_000_000_000);
            default: throw new IllegalArgumentException("Unknown unit: " + unit);
        }
    }

    public long getNanos() {
        return nanos;
    }

    public String getRaw() {
        return raw;
    }

    @Override
    public Object value() {
        return raw; // Return the raw string (e.g., "5ms")
    }

    @Override
    public TokenType type() {
        return TokenType.TIME_DURATION; // Assumes TokenType.TIME_DURATION is defined
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", TokenType.TIME_DURATION.name());
        json.addProperty("value", raw);
        return json;
    }
}
